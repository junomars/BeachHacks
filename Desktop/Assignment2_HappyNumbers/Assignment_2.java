package Assignment2_HappyNumbers;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.String;

public class Assignment_2 
{
	/**
	 * Main function calls getInput then checks to see if user wants to compute happy number again.
	 * @param args allows to pass arguments.
	 */
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("This program will figure out if a number is a happy number.");
		
		getInput();
		
		boolean done = false;
		
		do
		{
			System.out.println("");
			System.out.println("Would you like to compute another happy number[y/n]?");
			char decision = input.next().charAt(0);
			
			if(decision == 'y')
			{
				System.out.println("");
				getInput();
			}
			else if(decision == 'n')
			{
				System.out.println("Goodbye.");
				done = true;
			}
			else
			{
				System.out.println("Please enter a valid [y] or [n].");
			}
			
		}while(!done);

		input.close();
	}

	/**
	 * The function getInput prompts user to enter a positive integer.
	 * Once user input's number, function see's if number is already a happy number (being 1).
	 * If number is not already a happy number (being 1), function then calls object, happyNumber, and calls function of findHappyNumber.
	 */
	public static void getInput()
	{
		try
		{
			//create objects
			@SuppressWarnings("resource")
			Scanner getInput = new Scanner(System.in);
			Happy_Number happyNumber = new Happy_Number();
			
			//prompt user to enter positive integer
			System.out.println("Please enter a positive integer.");
			System.out.print("Input: ");		

			int numberInput = getInput.nextInt();
			
			//check if number is positive number
			while(numberInput <= 0)
			{
				System.out.println("Please enter a valid positive integer.");
				System.out.print("Input: ");
				numberInput = getInput.nextInt();
			}
			
			System.out.print("Output: " + numberInput);
			
			int counter = 0;
			
			//checking if input is already a happy number (1)
			if(numberInput == 1)
			{
				System.out.println(" - Happy");
			}
			else
			{
				happyNumber.findHappyNumber(numberInput, counter);
			}
		}
		catch(InputMismatchException exception)
		{
			System.out.println("");
			System.out.println("Input is not a integer. Please try again.");
			System.out.println("");
			getInput();
		}
	}
}
