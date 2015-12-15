package Assignment4;

import java.lang.String;
/**
 * This class will allow you to either create a stack of roman numeral
 * You can also validate a roman numeral
 * Compute a roman numeral
 * get a roman numeral value
 * and convert to a roman numeral
 * @author Daniel Son
 *
 */
public class RomanNumeral
{
	private int[] size = new int[2];
	private int counter = -1;
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
    public int stackRomanNumeral(LinkedList list, int switcher)
    {
        boolean stop = false;
        int counter = list.getSize()-1;

        CharacterStack stack1 = new CharacterStack();
        CharacterStack stack2 = new CharacterStack();
        
        //gets last roman numeral
        while(!stop)
        {
        	if(list.getData(counter) != '+' && list.getData(counter) != '-' && list.getData(counter) != '*' && list.getData(counter) != '/')
        	{
        		stack1.push(list.getData(counter));
        	}
        	else
        	{
        		stop = true;
        	}
        	counter--;
        }
        
        stop = false;
       
        while(!stop)
        {
        	if(list.getData(counter) != '+' && list.getData(counter) != '-' && list.getData(counter) != '*' && list.getData(counter) != '/')
        	{
        		stack2.push(list.getData(counter));
        	}
        	counter--;
        	if(counter <= 0)
        	{
        		stop = true;
        	}
        }
        
        CharacterStack input;
        
        if(switcher == 1)
        {
        	input = stack2;
        }
        else
        {
        	input = stack1;
        }
        
    	RomanNumeral numeral = new RomanNumeral();

        int total = 0;
        int lastNumber = 0;
        boolean error = false;
        

        char[] charArray = new char[size[this.counter-1]];
        counter++;
        for(int ii = charArray.length-1; ii >= 0; ii--)
        {
        	charArray[ii] = input.pop();
        }
        
        //goes through each stack and computes value
        for(int ii = 0; ii < charArray.length; ii++)
        {
        	//converts character into a value and assigns in Roman value
            int romanValue = numeral.getRomanValue(charArray[ii]);
            		
            		//numeral.getRomanValue(input.pop());
            
        	
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
    public boolean validateRomanNumeral(CharacterStack stack)
    {
    	char[] input = new char[stack.getSize()];
    	counter++;
    	size[counter] = stack.getSize();
    	
    	for(int ii = 0; ii < input.length; ii++)
    	{
    		input[ii] = stack.pop();
    	}
    	
        Verifier verify = new Verifier(input);

        int stringLength = input.length;
     
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
		            	System.out.println("There is an invalid roman character consecutively entered");
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
    	//References
    	String thousands[]={"","M","MM","MMM"};
    	String hundreds[]={"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
    	String tens[]={"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
    	String ones[]={"","I","II","III","IV","V","VI","VII","VIII","IX"};
    	/*Finding the digits in the thousand, hundred, ten and units place*/
    	int thous = decimal / 1000;
    	int hunds = (decimal / 100) % 10;
    	int ten = (decimal / 10) % 10;
    	int one = decimal % 10;
    	System.out.println("Roman Equivalent = "+thousands[thous]+hundreds[hunds]+tens[ten]+ones[one]);
    	}
 
}
