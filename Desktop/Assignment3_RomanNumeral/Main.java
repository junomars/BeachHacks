package assignment3;

import java.lang.String;
import java.util.*;

/**
 * 
 * @author Daniel Son
 *
 */
public class Main
{
	/**
	 * Main function prompts user then evokes prompt User. After function finish, main then
	 * checks to see if user wants to run again
	 * @param args allows to pass arguments
	 */
    public static void main(String[] args)
    {

    	boolean again = true;
    	
    	//prompt user once
    	System.out.println("This program will allow you to do roman numeral mathematics.");
    	
    	//do-while loop to repeat program on user's choice
    	do
    	{
    		promptUser();
    		//checks to see if user wants to rerun program
    		again = checkToRunAgain();
    		
    	}while(again);
    	
    	System.out.println("Goodbye.");
        
    	
    }
    /**
     * prompts user then allows user to input values of string
     * validates string to see if it is a roman numeral
     * prompts user and allows user to input an operator
     * prompts user to input another value of string
     * validates string to see if it is a roman numeral
     * function then calculates both roman numerals with an operator
     * displays final calculation in roman numeral.
     */
	public static void promptUser()
    {
       	
            @SuppressWarnings("resource")
            //creating objects
			Scanner in = new Scanner(System.in);
            RomanNumeral numeral = new RomanNumeral();


            System.out.print("Please enter the first Roman numeral: ");
            String input1 = in.next();
            boolean valid = numeral.validateRomanNumeral(input1);
            while(valid == false)
            {
            	System.out.print("Re-enter a valid first Roman numeral: ");
            	input1 = in.next();
            	valid = numeral.validateRomanNumeral(input1);
            }

           	int decimalValue1 = numeral.stackRomanNumeral(input1);
            
            //checks if first numeral has an error or not
            if(decimalValue1 != 0)
            {
            	System.out.println("");
            	
	            System.out.print("Please enter an operator [+] [-] [/] [*]: ");
	            char operator = checkOperator(in.next().charAt(0));           
	
	            System.out.println("");
	
	            System.out.print("Please enter the second Roman numeral: ");
	            String input2 = in.next();
	            boolean valid2 = numeral.validateRomanNumeral(input2);
	            while(valid2 == false)
	            {
	            	System.out.print("Re-enter a valid second Roman numeral: ");
	            	input2 = in.next();
	            	valid2 = numeral.validateRomanNumeral(input2);
	            }
		        
	            int decimalValue2 = numeral.stackRomanNumeral(input2);
	         		
			    //checks if second numeral has an error or not
			    if(decimalValue2 != 0)
			    {
			    	System.out.println("");
					
					System.out.println(decimalValue1 + " " + operator + " " + decimalValue2);
					System.out.println(input1.toUpperCase() + " " + operator + " " + input2.toUpperCase());
					
					int total = calculateAlgebra(decimalValue1, decimalValue2, operator);
					            
					//constraint of size of calculation
					if(total > 3999)
					{
						System.out.println("The calculation of the two Roman numerals cannot exceed 3999");
					        	
					}
					else if(total <= 0)
					{
						System.out.println("Does not exist in Roman numeral.");
					}
					else
					{
						System.out.println(total);
						numeral.convertToRoman(total);
					}
			    }            
           }
	            
    }//end of method
	/**
	 * Function asks user if they want to run the program again
	 * Gets user's input
	 * @return a true or false boolean of user's choice
	 */
	public static boolean checkToRunAgain()
	{
	    //creating object	
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.print("Would you like to run the program again? [y][n]: ");
		char input = in.next().charAt(0);
		
		boolean check = true;
		boolean choice = true;
		
		//checks to see user's choice on yes or no
		while(check)
		{
			if(input == 'y' || input == 'Y')
			{
				choice = true;
				check = false;
			}
			else if(input == 'n' || input == 'N')
			{
				choice = false;
				check = false;
			}
			else
			{
				System.out.println("Please Enter a valid [y] or [n]");
				input = in.next().charAt(0);
			}
		}
		
		return choice;
	}
    /**
     * Gets user's two inputs of Roman numeral
     * depending on operator, the Roman numeral's will calculate
     * @param num1 first input of Roman numeral
     * @param num2 second input of Roman numeral
     * @param operator user's choice of operator
     * @return returns calculation of both Roman numerals with the operator.
     */
    public static int calculateAlgebra(int num1, int num2, char operator)
    {
    	int total = 0;
    	
    	//based on operator, calculations will happen
    	switch(operator)
    	{
    		case '+':
    		{
    			total = num1 + num2;
    			break;
    		}
    		case '-':
    		{
    			total = num1 - num2;
    			break;
    		}
    		case '*':
    		{
    			total = num1 * num2;
    			break;
    		}
    		case '/':
    		{
    			total = num1 / num2;
    			break;
    		}
    		default:
    		{
    			System.out.println("Error.");
    			break;
    		}
    	}
    	
    	
    	return total;
    }
    /**
     * Checks to see if user input of operator is a valid operator in this program
     * @param input user's input of operator
     * @return user's input of operator
     */
    public static char checkOperator(char input)
    {
    	//creating object
    	@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
    	
    	boolean checkOperator = true;
    	
    	//checks if valid operator was input from user
        while(checkOperator)
        {
        	switch(input)
        	{
            	case '+':
            	{
            		checkOperator = false;
            		break;
            	}
            	case '-':
            	{
            		checkOperator = false;
            		break;
            	}
            	case '*':
            	{
            		checkOperator = false;
            		break;
            	}
            	case '/':
            	{
            		checkOperator = false;
            		break;
            	}
            	default:
            	{
            		System.out.println("Please enter a correct operator [+] [-] [*] [/]");
            		System.out.print("Operator choice: ");
            		input = in.next().charAt(0);
            		
            	}
        	}
        }
        
        return input;
    }


}
