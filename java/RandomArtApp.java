package randomart;

import java.util.Random;
import java.util.Scanner;

public class RandomArtApp {
	Picture picture;
	int minDepth;
	int maxDepth;
	Random randomizer;
	Scanner scanner;
	
	RandomArtApp(int width, int height) {
		picture = new Picture(width, height, "Random Art");
		randomizer = new Random();
		minDepth = 1;
		maxDepth = 1;
		scanner = new Scanner(System.in);
	}

	private void setDepthRange() {
		while (true) {
			try {
				// TO DO
				// Prompt the user to enter two numbers for min and max depth.
				// Check that they are in the right order (i.e. min <= max).
				// If they are, use them to set the minDepth and maxDepth instance variables.
				// Otherwise, print an error.
				
			} catch (NumberFormatException e) {
				System.out.println("\n*** Please enter integers!");
			}
		}
	}

	private void grayscaleManual() {	
		while (true) {
			try {
				System.out.print("\nEnter an expression: ");
				String userInput = scanner.nextLine();
				Expression expression = new Expression(userInput);
				System.out.println("\nDrawing grayscale picture.\n");
				// TO DO
				// Use the expression object and the methods of the Picture class
				// to draw a grayscale picture based on the expression the user entered.
				
				
				break;
			} catch (ParseException exception) {
				System.out.println("\n*** Invalid expression - " + exception.getMessage());
			}
		}
	}
	
	private void grayscaleRandom() {
		int depth = randomizer.nextInt(maxDepth+1-minDepth) + minDepth;
		System.out.println("\nDrawing grayscale picture.");
		System.out.println("Expression:");
		
		// TO DO
		// Generate a random expression string of the given depth, using
		// the transformational grammar technique.
		// (Hint: Write a separate function to handle this.)
		
		// The line below is a placeholder, you should change/replace it!
		String exprString = "x"; 
		
		System.out.println(exprString + "\n");
		
		try {
			Expression expression = new Expression(exprString);
			// TO DO
			// Use the expression object and the methods of the Picture class
			// to draw a grayscale picture based on exprString
			
			
		} catch (ParseException e) {
			System.out.println("\n*** Invalid expression - " + e.getMessage() + "\n");
		}
	}
	
	private void colorRandom() {
		Expression[] expressions = new Expression[3];
		System.out.println("\nDrawing color picture.");
		
		try {
			for (int i = 0; i < 3; i++) {
				int depth = randomizer.nextInt(maxDepth+1-minDepth) + minDepth;
				// TO DO
				// (a) Generate a random expression string of the given depth, using
				// the transformational grammar technique.
				// (Hint: Again use a separate function to handle this.)
				
				
				// (b) Create a new Expression object from that string, and 
				// store it in expressions[i]
				
				// This line is a placeholder - you should replace/change it!
				expressions[i] = new Expression("x");
			}
		} catch (ParseException e) {
			System.out.println("\n*** Invalid expression - " + e.getMessage() + "\n");
		}
		System.out.println("Expressions:");
		// TO DO
		// (a) Print the three expression strings, labeling them "red", "green", "blue"
		
		
		System.out.println();
		// (b) Use the three expressions object in the array and the methods of
		// the Picture class to draw a COLOR picture.
		
		
		
	}

	public void run() {
		while (true) {
			System.out.printf("Current depth range: %d to %d\n", minDepth, maxDepth);
			System.out.println("(E) Enter grayscale expression by hand");
			System.out.println("(G) Random grayscale picture");
			System.out.println("(C) Random color picture");
			System.out.println("(D) Set depth range");
			System.out.println("(Q) quit");
			
			String choice;
			do {
				System.out.print("Select from the above menu: ");
				choice = scanner.nextLine().toUpperCase();
			}
			while (choice.length() != 1 || "EGCDQ".indexOf(choice.charAt(0)) == -1);
			
			switch (choice) {
				case "E":
					grayscaleManual();
					break;
				case "G":
					grayscaleRandom();
					break;
				case "C":
					colorRandom();
					break;
				case "D":
					setDepthRange();
					break;
				case "Q":
					picture.close();
					return;
				default:
					throw new Error("Unkown menu choice, this should not happen");
			}
		}
	}
	

	public static void main(String[] args) {
		RandomArtApp app = new RandomArtApp(600, 600);
		app.run();
	}
}
