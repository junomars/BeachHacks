package Assignment4;

import java.lang.String;
import java.util.*;

/**
 * This class is the main class for getting two Roman numeral and an operator.
 * It then computes the math and returns and displays the total calculation in roman numeral.
 * @author Daniel Son
 *
 */
public class Roman 
{
	
		/**
		 * function prompts user then evokes prompt User. After function finish, main then
		 * checks to see if user wants to run again
		 */
	    public void romanNumeralMain()
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
		public void promptUser()
	    {
	       	
	            @SuppressWarnings("resource")
	            //creating objects
				Scanner in = new Scanner(System.in);
	            RomanNumeral numeral = new RomanNumeral();
	            LinkedList list = new LinkedList();
	            CharacterStack stack1 = new CharacterStack();
	            CharacterStack stack2 = new CharacterStack();

	            
	            System.out.print("Please Enter two Roman numerals with an operator: ");
	            String input = in.nextLine();
	            
	            
	            //adding each character of user input into individual Nodes of linked list
	            for(int ii = 0; ii < input.length(); ii++)
	            {
	            	//checks if there is spaces
	            	if(!(input.charAt(ii) == ' '))
	            	{
		            	list.add(input.toUpperCase().charAt(ii));
	            	}
	            }
	            
	            boolean stop = false;
	            int counter = list.getSize()-1;
	            char operator = ' ';
	            
	            
	            
	            //gets last roman numeral
	            while(!stop)
	            {
	            	if(list.getData(counter) != '+' && list.getData(counter) != '-' && list.getData(counter) != '*' && list.getData(counter) != '/')
	            	{
	            		stack1.push(list.getData(counter));
	            	}
	            	else
	            	{
	            		operator = list.getData(counter);
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
	            
	            boolean valid = numeral.validateRomanNumeral(stack2);
            	
	            boolean valid2 = numeral.validateRomanNumeral(stack1);

	            if(valid == false)
	            {
	            	System.out.println("First Roman numeral is wrong.");
	            }
	            else if(valid2 == false)
	            {
	            	System.out.println("Second Roman numeral is wrong.");
	            }
	            else
	            {

	            	int switcher = 1;
		            
		            int decimalValue1 = numeral.stackRomanNumeral(list, switcher);
		            switcher++;
	            	int decimalValue2 = numeral.stackRomanNumeral(list, switcher);


		            if(decimalValue1 != 0 && decimalValue2 != 0)
		            {
							System.out.println("Calculation: " + decimalValue1 + " " + operator + " " + decimalValue2);
							
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
								System.out.println("Decimal total: " + total);
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
		public boolean checkToRunAgain()
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
	    public int calculateAlgebra(int num1, int num2, char operator)
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
	    public char checkOperator(char input)
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
