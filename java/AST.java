package randomart;

import java.util.HashMap;
import java.util.LinkedList;


public class AST {	
	private interface Fn {
		double eval(double... args);
	}
	
	private static Fn cosfn = (double... args) -> Math.cos(args[0]);
	private static Fn sinfn = (double... args) -> Math.sin(args[0]);
	private static Fn avgfn = (double... args) -> ((args[0] + args[1])/2);
	private static Fn multfn = (double... args) -> (args[0] * args[1]);
	private static Fn pival = (double... args) -> Math.PI;
	private static Fn xval = (double... args) -> (args[0]);
	private static Fn yval = (double... args) -> (args[1]);
	private static HashMap<String, Fn> fnmap = makeFnMap(new String[]{"cos", "sin", "pi", "x", "y"},
												new Fn[]{cosfn, sinfn, pival, xval, yval});
	
	private static LinkedList<String> tokens;
	
	// every node is an operation with up to two children
	private Fn op;
	private AST left;
	private AST right;
	
	// constructors
	AST(Fn op, AST left, AST right) { 
		this.op = op;
		this.left = left;
		this.right = right;
	}
	AST(Fn op, AST left) { this(op, left, null); }
	AST(Fn op) { this(op, null, null); }
	
	// evaluating an expression
	public double evalNode(double x, double y) {
		if (left == null)
			return op.eval(x, y);
		else if (right == null) 
			return op.eval(left.evalNode(x, y));
		else
			return op.eval(left.evalNode(x, y), right.evalNode(x,  y));
	}
	
	static AST parse(LinkedList<String> startTokens) throws ParseException {
		tokens = startTokens;
		return E();
	}
	
	static private AST checkStar(AST current) throws ParseException {
		if (!tokens.isEmpty() && tokens.get(0).equals("*")) {
			tokens.pop();
			return new AST(multfn, current, E());
		}
		return current;
	}
	
	static private void expect(String expected) throws ParseException {
		String t;
		if (tokens.isEmpty())
			throw new ParseException("Expected " + expected + " but reached end of input");		
		if (!(t = tokens.pop()).equals(expected))
			throw new ParseException("Expected " + expected + " but found " + t);
	}
	
	static private AST E() throws ParseException {
		if (tokens.isEmpty())
			throw new ParseException("Expected expression, but reached end of input");
		
		String tok = tokens.pop();
		
		// E -> x | x * E
		// E -> y | y * E
		if (tok.matches("pi|x|y")) {
			AST node = new AST(fnmap.get(tok));
			return checkStar(node);
		}

		// E -> sin ( E ) | sin ( E ) * E
		// E -> cos ( E ) | cos ( E ) * E
		if (tok.matches("cos|sin")) {
			expect("(");
			AST node = new AST(fnmap.get(tok), E());
			expect(")");
			return checkStar(node);
		}
		
		//	E -> ( E + E ) / 2 | E -> ( E + E ) / 2 * E
		if (tok.equals("(")) {
			AST exprA = E();
			expect("+");
			AST exprB = E();
			expect(")");
			expect("/");
			expect("2");
			AST node = new AST(avgfn, exprA, exprB);
			return checkStar(node);
		}
		
		throw new ParseException("Syntax error: '" + tok + "'");
	}
	
	static LinkedList<String> tokenize(String s) {
		LinkedList<String> result = new LinkedList<String>();
		String current = "";
		boolean alphaSeen = false;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c))
				continue;
			if (Character.isAlphabetic(c)) {
				alphaSeen = true;
				current += c;
			} else {
				if (alphaSeen) {
					result.add(current);
					current = "";
					alphaSeen = false;
				}
				result.add("" + c);
			}
		}
		if (!current.isEmpty())
			result.add(current);
		return result;
	}
	
	private static HashMap<String, Fn> makeFnMap(String[] names, Fn[] functions) {
		HashMap<String, Fn> m = new HashMap<String, Fn>();
		for (int i = 0; i < names.length; i++) {
			m.put(names[i], functions[i]);
		}
		return m;
	}
}