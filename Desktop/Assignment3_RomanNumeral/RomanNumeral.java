package assignment3;

import java.lang.String;
/**
 * 
 * @author Daniel Son
 *
 */
public class RomanNumeral
{
	private int[] decimalValues = {1000, 500, 100, 50, 10, 5, 1};
	private char[] romanValues = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
	/**
	 * constructor does nothing. Not needed
	 */
	public RomanNumeral()
	{
		//nothing
	}
	/**
	 * get's user input of string and gets individual characters and puts them in a Stack
	 * computes each value of the string
	 * @param input user input of string 
	 * @return total value of the Roman numeral that user input
	 */
    public int stackRomanNumeral(String input)
    {
    	
    	RomanNumeral numeral = new RomanNumeral();
    	
        int wordLength = input.length();
        CharacterStack individualChar = new CharacterStack();

        //assign each character into a stack
        for (int ii = 0; ii < wordLength; ii++)
        {
            individualChar.push(input.toUpperCase().charAt(ii));
        }

        int total = 0;
        int lastNumber = 0;
        boolean error = false;
        
        //goes through each stack and computes value
        for(int ii = wordLength -1; ii >= 0; ii--)
        {
        	//converts character into a value and assigns in Roman value
            int romanValue = numeral.getRomanValue(individualChar.pop());
            
            if(romanValue == 0)
            {
            	System.out.println("Error in roman numeral syntax.");
            	System.out.println("");
            	error = true;
            }
            else
            {
                total = numeral.computeRomanNumeral(romanValue, lastNumber, total);
                lastNumber = romanValue;
            }

        }

        //if error is detected in Roman numeral, will return a value of zero
        if(error == true)
        {
        	total = 0;
        }
        return total;
    }
    /**
     * Checks to see if user input is a valid Roman Numeral
     * @param input User input of roman numeral
     * @return a true or false value if validation is true or not
     */
    public boolean validateRomanNumeral(String input)
    {
        Verifier verify = new Verifier(input);

        int stringLength = input.length();
     
        boolean validInput = verify.checkIndividualChar(input);      

        if(validInput == false)
        {
        	System.out.print("Error: ");
        	System.out.println("There is a character in your input that is not part of a Roman Numeral.");
        	System.out.println("");
        	return false;
        }
        else
        {
        	//System.out.println("Check individualChar validation successful.");
        	
        	if(stringLength == 1)
        	{
        		return true;
        	}
        	else
        	{
        		boolean isThereRepeats = verify.checkThreeOrMoreNumerals(input);
        		
		    	if(isThereRepeats == true)
		    	{
		        	System.out.print("Error: ");
		    		System.out.println("There are more than three consecutive Roman characters.");
		        	System.out.println("");
		    		return false;
		    	}
		    	else
		    	{
		    		//System.out.println("Check for three or more numerals validation successful");
		    		
		    		boolean validRepeats = verify.checkDuplicatesValidTens(input);
		            
		            if(validRepeats == false)
		            {
		            	System.out.print("Error: ");
		            	System.out.println("There is are invalid roman character consecutively entered");
		            	System.out.println("");
		            	return false;
		            }
		            else
		            {
		            	//System.out.println("Check for valid tens power duplicates validation successful");
		            	
		            	boolean invalidSmallPower = verify.checkPowerLeftOfNumeral(input);
		            	
		            	if(invalidSmallPower == true)
		            	{
			            	System.out.print("Error: ");
			            	System.out.println("There is an invalid roman character left a larger roman character");
			            	System.out.println("");
			            	return false;
		            	}
		            	else
		            	{
		            		//System.out.println("Check for valid powers left of larger numbers successful.");
		            		
		            		boolean invalidPower = verify.checkPowerFrontOfNumeral(input);
		            		
		            		if(invalidPower == true)
		            		{
		            			System.out.print("Error: ");
				            	System.out.println("There is an invalid roman character front of a larger roman character");
				            	System.out.println("");
				            	return false;
		            		}
		            		else
		            		{
			            		//System.out.println("Check for valid powers front of larger numbers successful");
			            		return true;
		            		}
	
		            	}
		            }   
		    	}
        	}
	        	
        }
    }
       /**
        * Computes the Roman numeral that user input
        * @param total the total value of the roman numeral
        * @param lastRomanValue the previous character of the roman numeral
        * @param lastDecimal the previous value of the roman numeral
        * @return
        */
    public int computeRomanNumeral(int total, int lastRomanValue, int lastDecimal)
    {
    	//computes and add's values
        if (lastRomanValue > total)
        {
            return lastDecimal - total;
        } else
        {
            return lastDecimal + total;
        }
    }
    /**
     * Based on the character, this function will return the value of the character
     * @param character individual character from user's string of Roman numeral
     * @return value based on the character
     */
    public int getRomanValue(char character)
    {
        //determines values based on character
        switch(character)
        {
            case 'M': return 1000;
            case 'D': return 500;
            case 'C': return 100;
            case 'L': return 50;
            case 'X': return 10;
            case 'V': return 5;
            case 'I': return 1;
            default:
            {
                System.out.println("Illegal character '" + character + "' in Roman numeral.");
                return 0;
            }
        }
    }
    /**
     * Converts the calculation of Arabic number back to Roman numeral
     * @param decimal the total calculation of the Arabic number
     */
    public void convertToRoman(int decimal)
    {
    	//create object
    	CharacterQueue queue = new CharacterQueue(romanValues.length);
    	
    	int counter = 0;
    	int divideValue;
    	int divideNumber = decimal;
    	
    	//for loop iterating through each value to check
    	for(int ii = 0; ii < romanValues.length; ii++)
    	{
    		divideValue = divideNumber / decimalValues[counter];
    		
    		//checking if number is divisible by 1000, 500, 100, 50, 10, 5, 1
    		if(divideValue == 0)
    		{
    			divideNumber = divideNumber % decimalValues[counter];
    			counter++;
    		}
    		else if(divideValue >= 4)
    		{
    			char temp = queue.dequeue();
    			queue.dequeue();
    			queue.enqueue(temp);
    			queue.enqueue(romanValues[counter]);
    			queue.enqueue(romanValues[counter - 2]);
    		}
    		else
    		{
    			//putting Roman characters into a queue
    			for(int jj = 0; jj < divideValue; jj++)
    			{
    				queue.enqueue(romanValues[counter]);
    			}
    			divideNumber = divideNumber % decimalValues[counter];
    			counter++;
    		}	
    	}
    	//printing out queue variables
    	while(!queue.isEmpty())
    	{
    		System.out.print(queue.dequeue());
    	}
    	
    	System.out.println("");
    }
}
