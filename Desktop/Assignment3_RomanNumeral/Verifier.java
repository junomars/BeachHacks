package assignment3;
import java.lang.Math;
/**
 * 
 * @author Daniel Son
 *
 */
public class Verifier 
{
	private char[] inputChar;
	private int[] decimalValues = {1000, 500, 100, 50, 10, 5, 1};
	private char[] romanValues = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
	/**
	 * Constructor without parameter is not needed
	 */
	public Verifier()
	{
		//constructor
	}
	/**
	 * Initializes variables based on the parameters given
	 * @param input User input of Roman numeral
	 */
	public Verifier(String input)
	{
        inputChar = new char[input.length()];
        
		//separating individual characters into an array
        for(int ii = 0; ii < input.length(); ii++)
        {
        	inputChar[ii] = input.toUpperCase().charAt(ii);
        }    
	}
	/**
	 * This function will check each individual characters in user input to see if user input is valid
	 * @param input user input of Roman numeral
	 * @return a boolean of true or false if all individual characters are valid
	 */
	public boolean checkIndividualChar(String input)
	{
		CharacterQueue numeralQueue = new CharacterQueue();
		
		//assigning characters into queue
        for(int ii = 0; ii < input.length(); ii++)
        {
            numeralQueue.enqueue(input.toUpperCase().charAt(ii));
        }
        
        boolean validInput = true;

        //checking individual characters to see if there is invalid characters
        while(!numeralQueue.isEmpty())
        {
        	char checkInput = numeralQueue.dequeue();
        	
        	for(int ii = 0; ii < romanValues.length; ii++)
        	{

        		if(validInput != false)
        		{
        			if(checkInput != romanValues[ii])
	        		{
        				boolean validateAll = false;
        				
	        			for(int jj= 0; jj < romanValues.length;jj++)
	        			{
	        				if(checkInput == romanValues[jj])
	        				{
	        					validateAll = true;
	        				}
	        			}
	        			if(validateAll == false)
	        			{
	        				validInput = false;
	        			}
	        		}
	        		else
	        		{
	        			validInput = true;
	        			ii = romanValues.length;
	        		}
        		}
	        			
	        		        			
        	}
        }
        
        return validInput;
	}
	/**
	 * Checks to see if user input of Roman numeral does not consist of more than three consecutive similiar characters
	 * @param input user input of Roman numeral
	 * @return a boolean based on if user input has three or more consecutive roman values
	 */
	public boolean checkThreeOrMoreNumerals(String input)
	{
		CharacterQueue numeralQueue = new CharacterQueue();
		
        //assigning characters into queue
        for(int ii = 0; ii < input.length(); ii++)
        {
            numeralQueue.enqueue(input.toUpperCase().charAt(ii));
        }
        
    	int repeaterCheck = 0;
        char checkCharacter;
        int counting = 1;
        boolean isThereRepeats = false;
    	
        numeralQueue.dequeue();
        //checks for more than 3 Roman numerals combined
        while(!(numeralQueue.isEmpty()))
        {
        	checkCharacter = numeralQueue.dequeue();
        	        	
        		if(checkCharacter == inputChar[counting - 1])
        		{
        			repeaterCheck++;
        		}
        		else
        		{
        			repeaterCheck = 0;
        		}
        		
        		if(repeaterCheck >= 3)
        		{
        			isThereRepeats = true;
        		}
        		
            	if(counting != input.length())
            	{
            		counting++;
            	}
        }
        return isThereRepeats;
	}
	/**
	 * This functions checks to see if there are duplicates that are valid powers of ten
	 * @param input user input of roman numeral
	 * @return boolean based on if there are valid duplicates of the tens power values
	 */
	public boolean checkDuplicatesValidTens(String input)
	{
		CharacterQueue numeralQueue = new CharacterQueue();
		
        //assigning characters into queue
        for(int ii = 0; ii < input.length(); ii++)
        {
            numeralQueue.enqueue(input.toUpperCase().charAt(ii));
        }

        RomanNumeral roman = new RomanNumeral();
        
        int count = 1;
        boolean validRepeats = false;
        //checking if duplicates are valid for powers ten
        while(!numeralQueue.isEmpty())
        {
        	char checkChar = numeralQueue.dequeue();
        	
        	if(checkChar == inputChar[count - 1])
        	{
        		if(checkChar != 'I')
        		{
        			for(int ii = 0; ii < decimalValues.length;ii++)
        			{
                		int checkTenPower = roman.getRomanValue(checkChar) % decimalValues[ii];
                		
                		if(validRepeats != true)
                		{
                			if(checkTenPower != 0)
	                		{
	                			validRepeats = false;
	                		}
	                		else
	                		{
	                			validRepeats = true;
	                		}
                		}
        			}
        		}
        		else if(checkChar == 'I')
        		{
        			validRepeats = true;
        		}

        	}
        	if(count != input.length())
        	{
        		count++;
        	}
        }
        
        return validRepeats;
	}
	/**
	 * Checks to see if a value left of a bigger numeral value is valid.
	 * Checks to see if value on the left is less than or equal to 10 times the value that is bigger
	 * @param input user input of Roman numeral
	 * @return boolean based on if the numeral left of bigger numeral is valid
	 */
	public boolean checkPowerLeftOfNumeral(String input)
	{
		CharacterQueue numeralQueue = new CharacterQueue();
		
        //assigning characters into queue
        for(int ii = 0; ii < input.length(); ii++)
        {
            numeralQueue.enqueue(input.toUpperCase().charAt(ii));
        }
        
        RomanNumeral roman = new RomanNumeral();
        
        int counter = 1;
        
        boolean invalidPower = false;
        
        while(!numeralQueue.isEmpty())
        {
        	int checkValue = roman.getRomanValue(numeralQueue.dequeue());
        	//double tenTimes = checkValue * 10;
        	int divide = roman.getRomanValue(inputChar[counter]) / checkValue;
        	
        	if(roman.getRomanValue(inputChar[counter]) > checkValue)
        	{
        		if(divide > 10)
	        	{
	        		invalidPower = true;
	        	}
        	}
	        
        	if(counter != inputChar.length - 1)
        	{
        		counter++;
        	}

        }
        
        return invalidPower;
	}
	/**
	 * Checks to see if value in front of the bigger numeral is a valid number
	 * It is valid if it is a power of 10.
	 * @param input user input of Roman numeral
	 * @return boolean based on if value in front of bigger numeral is valid
	 */
	public boolean checkPowerFrontOfNumeral(String input)
	{
		CharacterQueue numeralQueue = new CharacterQueue();
		
        //assigning characters into queue
        for(int ii = 0; ii < input.length(); ii++)
        {
            numeralQueue.enqueue(input.toUpperCase().charAt(ii));
        }
        
        RomanNumeral roman = new RomanNumeral();
        
		boolean invalidSmallPower = false;
		
		int counter = 1;
		
		while(!numeralQueue.isEmpty())
		{
			int checkValue = roman.getRomanValue(numeralQueue.dequeue());
			double logTen = Math.log10(checkValue);
			if(roman.getRomanValue(inputChar[counter]) > checkValue)
			{
				if(logTen == (int)logTen)
				{
					invalidSmallPower = false;
				}
				else
				{
					invalidSmallPower = true;
				}
			}
	        
        	if(counter != inputChar.length - 1)
        	{
        		counter++;
        	}
		}
		
        
		return invalidSmallPower;
	}
}
