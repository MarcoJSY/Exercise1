// Made by: Jed Marco S. Mendizabal, in submission as part of the ECC Machine Problem Exercise 1
// Class file for Exec1.java
import java.util.Scanner; // To handle user input
import java.util.Random; // To generate random ASCII characters
import java.util.InputMismatchException; // For catching invalid inputs
import java.lang.IllegalArgumentException; // For catching invalid arguments

class Functionalities {

	private int rows; // private rows variable
	private int columns; // private columns variable 
	private String[][] table; // private String type table array
	private Scanner myInput = new Scanner(System.in); // private Scanner object
	private Random random = new Random(); // private Random object


	// Generate method
	public void generate() {
		boolean validInput = false; // Flag to control input validation

		
		// While loop for input validation
		while (!validInput) {
			try {
				// Rows input
				System.out.print("Number of rows: ");
				rows = myInput.nextInt();
				if (rows <= 0) {
					throw new IllegalArgumentException("Number of rows must be a positive integer.");
				}

				// Columns input
				System.out.print("Number of columns: ");
				columns = myInput.nextInt();
				if (columns <= 0) {
					throw new IllegalArgumentException("Number of columns must be a positive integer.");
				}

				validInput = true;
				System.out.println();

			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter positive integers only.");
				myInput.next();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}			
		}

		// Table generation
		System.out.println("Generated Array: ");
		System.out.print("");
		table = new String[rows][columns];

		/*

			I used StringBuilder() for efficient string manipulation because it is mutable,
			unlike the traditional manual concatenation of strings, which creates a new 
			String object every time a modification is made. 

			In the case of manual concatenation, each time a character is appended, 
			the original String is discarded and a new one is created, leading to 
			higher memory usage and slower performance, especially in loops.

			StringBuilder, on the other hand, uses a single buffer that expands as needed, 
			allowing multiple operations on the same object without creating additional 
			unnecessary objects.

		*/

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				StringBuilder cellContent = new StringBuilder();

				/*

					This inner loop generates the random ASCII characters between 33 and 126.
					Because it corresponds to the printable ASCII characters according to my 
					study from '!' to '~'. The random.nextInt(94) generates a value from 0 to 93.
					The addition of 33 shifts the range to 33 to 126. After that append the 
					generated character to the StringBuilder object.

				*/

				for (int k = 0; k < 3; k++) {
					char randomChar = (char) (random.nextInt(94) + 33); 
					cellContent.append(randomChar);
				}

				table[i][j] = cellContent.toString(); 

			}
		}

		// Display of requested character search

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("");		
	}

	// Search method
	public void search() {
		// User prompt to enter characters to search
		System.out.println("1. Search");
		System.out.println("Enter the character/s to search: ");

		//Read the user input containing the requested characters to search
		String searchChars = myInput.next();
		int totalOccurrences = 0; // Total occurrences of all requested characters counter
		System.out.println();

		// For loop to iterate over each character in the requested search characters
		for (int s = 0; s < searchChars.length(); s++) {
			char currentSearchChar = searchChars.charAt(s); // Getting the current character to search
			int charOccurrences = 0; // current character occurrences counter

			// Inform user about the character to be searched
			System.out.println("Searching for: '" + currentSearchChar + "'");

			// Iterate over each cell in the generated 2D table array
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					String cell = table[i][j]; // Retrieving the content of the current cell

					// Iterate over each character in the cell content					
					for (int k = 0; k < cell.length(); k++) {
						// Check if the current character matches the requested search character
						if (cell.charAt(k) == currentSearchChar) {
							charOccurrences++; // Increment the character occurrence counter
							// Print the location of the matched character
							System.out.println("Found '" + currentSearchChar + "' at index: (" + (i + 1) + ", " + (j + 1) + ")");						
						}
					}					
				}
			}
			System.out.println();

			// Update the total occurrences counter by adding the character occurrences counter
			totalOccurrences += charOccurrences;

			// Print the results of the current character
			if(charOccurrences == 0) {
				System.out.println("Requested character '" + currentSearchChar + "' not found.");
			} else {
				System.out.println("Number of occurrences for '" + currentSearchChar + "': " + charOccurrences);				
			}
			System.out.println();
		}

		// Print the results of the total number of occurrences for all the characters
		System.out.println("Total number of occurrences for all characters: " + totalOccurrences);
		System.out.println();
	}

	// Edit method
	public void edit() {
		// User prompt to edit characters
		System.out.println("2. Edit");

		try{
			// User prompt to enter the row index to edit (1-based index)
			System.out.print("The row index to edit (starting from 1): ");

			// Input validation to check if the user input is an integer
			if(!myInput.hasNextInt()) {
				System.out.println("Error: Invalid input. Row index must be an integer.");
				System.out.println();
				myInput.next(); // Clear the invalid input
				return; // Exit the method to return to the menu
			}
			// Row input, and read the row index and adjust for 0-based index
			int editRow = myInput.nextInt() - 1;

			// Validate the row index
			if(editRow < 0 || editRow >= rows) {
				System.out.println("Error: Invalid row index. Please enter a valid row.");
				System.out.println();
				return; // Exit the method to return to the menu
			}

			// User prompt to enter the column index to edit (1-based index)
			System.out.print("The column index to edit (starting from 1): ");

			// Input validation to check if the user input is an integer
			if(!myInput.hasNextInt()) {
				System.out.println("Error: Invalid input. Column index must be an integer.");
				System.out.println();
				myInput.next();
				return;
			}
			// Column input, and read the column index and adjust for 0-based index
			int editCol = myInput.nextInt() - 1;

			// Validate the column index
			if(editCol < 0 || editCol >= columns) {
				System.out.println("Error: Invalid column index. Please enter a valid column.");
				System.out.println();
				return; // Exit the method to return to the menu
			}

			// Prompt the user to enter a new string value and it must be 3 characters
			System.out.print("Enter the new string (3 characters): ");
			String newValue = myInput.next();

			// Validate the length of the new string
			if(newValue.length() == 3) {
				// Update the table with the new value
				table[editRow][editCol] = newValue;
				System.out.println("Value updated successfully");
				System.out.println();
			} else {
				System.out.println("Invalid input. The string must be exactly 3 characters.");
			}
			
		} catch(Exception e) {
			// Handle unexpected errors 
			System.out.println("Error: An unexpected error occurred. Returning to the menu.");
			myInput.nextLine();
		}		
	}

	// Print method
	public void print() {
		// Notifying the user that the table will be printed
		System.out.println("3. Print");
		System.out.println("Printing the table again...");
		System.out.println();

		// Looping through each row of the table
		for (int i = 0; i < rows; i++) {
			// Looping through each column of the current row
			for (int j = 0; j < columns; j++) {
				// Print the value at the current cell followed by a space
				System.out.print(table[i][j] + " ");
			}
			// Move to the next line after printing all columns in the current row
			System.out.println();
		}
		// Added an extra blank line for better readability
		System.out.println();	
	}

	// Reset method
	public void reset() {
		// Notifying the user that the table is being reset
		System.out.println("Resetting the table...");

		// Calling the generate method to reinitialize the table with new random values
		generate();
	}

	// Exit method
	public void exit() {
		// Display farewell message after using the generator to the user
		System.out.println("Thank you for using the random ASCII character generator!");

		// Close the Scanner object to release resources/memory
		myInput.close();

		// Terminate the program with status code 0 (success)
		System.exit(0);
	}
}