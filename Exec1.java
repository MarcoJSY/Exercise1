// Made by: Jed Marco S. Mendizabal, in submission as part of the ECC Machine Problem Exercise 1
// Main file
import java.util.Scanner; // Imported Scanner class used for capturing user input from the console
import java.util.Random; // Imported Random class used for generating random numbers
import java.util.InputMismatchException; // Import to handle invalid input in menu
public class Exec1 {
	public static void main(String[] args) {
		Scanner myInput = new Scanner(System.in); // scanner variable initialization
		Random random = new Random(); // random variable initialization
		boolean isDone = false;	// main loop
		int choice = -1; // choice variable	
		Functionalities funcs = new Functionalities(); // object "funcs" initialization

		System.out.println("Java program 2-Dimensional Array random ASCII character generator");

		// Phase 1 - Generator
		/*

			Using while loop because the condition variable isDone is not automatically determined as 
			it will need to depend on the user when they decided to terminate the program through the Exit function.
			Meaning that the user can use the generator after they have finished using an operation.
			And I need the whole program 

		*/
		while(!isDone) {

			// Generate Table array
			funcs.generate();
	
			// Phase 2 - Modifier
			boolean innerLoop = true; // inner loop
			/* 
				Using boolean variable innerLoop into a while loop because same concept with the 
				main loop as it will act 
			*/
			while(innerLoop) {
				/*
					I inserted a try catch statement that will catch the error if the input is invalid. 
					Which then will reset back to the generate function.

				*/
				try {
					System.out.println("Welcome to random ASCII character generator!");
					System.out.println("1. Search");
					System.out.println("2. Edit");
					System.out.println("3. Print");
					System.out.println("4. Reset");
					System.out.println("5. Exit");
					System.out.print("Enter Menu choice (1-5): ");
					choice = myInput.nextInt();					
					/* 

						I used switch instead of If-Else statements because of readability, performance 
						and more importantly having multiple branches based on the same variable (int choice) 
						I opted that using a switch is more readable, concise and in some cases faster. 

					*/
					switch(choice) {
					case 1:					
						funcs.search();
						break;

					case 2:						
						funcs.edit();
						break;

					case 3:						
						funcs.print();					
						break;

					case 4:						
						funcs.reset();
						break;
					case 5:						
						funcs.exit();
						innerLoop = false;
						isDone = true;
						break;
					default:
						System.out.println("The choices are only 1-5. Please try again.");
						System.out.println();
						break;
					}
				} catch(InputMismatchException e) {
					// Error handling for Invalid Input (non-integer, special characters)
					System.out.println("Invalid input. Please enter an integer value between 1 and 5.");
					System.out.println();
					myInput.next();					
				} 
			}
		}
		myInput.close();
	}
}