package randomart;

public class Expression {
	
	Expression(String expressionString) throws ParseException {
		ast = AST.parse(AST.tokenize(expressionString));
	}  
	
	public double evaluate(double x, double y) {
		return ast.evalNode(x, y);
	}

	private AST ast;
}
