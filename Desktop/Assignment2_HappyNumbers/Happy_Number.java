package Assignment2_HappyNumbers;

import java.lang.String;

public class Happy_Number 
{

	private static int[] numberHolder;

	/**
	 * Constructor initializes global array variable to set size to hold numbers.
	 */
	public Happy_Number()
	{
		numberHolder = new int[20];
		for(int ii = 0; ii < numberHolder.length; ii++)
		{
			numberHolder[ii] = 0;
		}
	}
	
	/**
	 * The function findHappyNumber takes user input number, splits it into separate individual digits.
	 * Function then takes each individual digits and squares them, then adds to each other to get a new sum.
	 * @param numberInput A positive integer that user has given/new number generated from findHappyNumber function.
	 * @param counter A counter that keeps track of index of the global array variable, numberHolder.
	 */
	public void findHappyNumber(int numberInput, int counter)
	{
		//converts number into string
		String stringInput = Integer.toString(numberInput);
		
		//creates an array to hold individual digits of number
		int[] arrayNumber = new int[stringInput.length()];
		
		//math number to divide
		int numberDivided = numberInput;
		
		//giving arrayNumber individual digits
		for(int ii = stringInput.length() - 1; ii >= 0; ii--)
		{
			arrayNumber[ii] = numberDivided % 10;
			numberDivided = numberDivided / 10;
		}
		
		
		int sumOfNumber = 0;		
		
		//sum of the squares of the digits
		for(int jj = 0; jj < arrayNumber.length; jj++)
		{
			sumOfNumber = sumOfNumber + (arrayNumber[jj] * arrayNumber[jj]);
		}
		
		//prints output of sum
		System.out.print(", " + sumOfNumber);
		
		boolean found = false;
		
		//checking if number is repeated in sequence
		for(int qq = 0; qq < numberHolder.length; qq++)
		{
			if(numberHolder[qq] == sumOfNumber)
			{
				found = true;
			}
		}
		
		//stores number into array
		numberHolder[counter] = sumOfNumber;
		counter++;
		

		
		if(sumOfNumber == 1)
		{
			System.out.println(" - Happy");
		}
		else if(found == true)
		{
			System.out.println(" - Not Happy");
		}
		else
		{
			findHappyNumber(sumOfNumber, counter);
		}
	}
	
}
