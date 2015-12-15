package Assignment4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main class is the main function that will be executed. This will display the menu for the users.
 * @author Daniel Son
 *
 */
public class Main {

	private static boolean repeat;
	private static LinkedList list;
	private static boolean emptyList;
	
	/**
	 * The main starts the program and calls the function Menu. booleans are within this function to gauge if user wants to repeat program
	 * @param args allows arguments to be passed
	 */
	public static void main(String[] args)
	{
		repeat = true;
		emptyList = true;
		//loop until user wants to exit
		do
		{
			menu();
			emptyList = true;
		}while(repeat);
		

	}
	
	/**
	 * The menu function will prompt the user with a menu and allow user to input their choice. Based on their choice, certain functions will occur
	 */
	public static void menu()
	{
		Scanner in = new Scanner(System.in);
		
		try
		{
			//prompt user with menu
			System.out.println(" ___________________________________________ ");
			System.out.println("|_______________Main Menu___________________|");
			System.out.println("|                                           |");
			System.out.println("| [1]  Create an empty Linked List          |");
			System.out.println("| [2]  Insert Node at specific position     |");
			System.out.println("| [3]  Remove Node at specific position     |");
			System.out.println("| [4]  Display size of Linked List          |");
			System.out.println("| [5]  Display info stored at specific Node |");
			System.out.println("| [6]  Show Linked List data                |");
			System.out.println("| [7]  Process Roman numeral Expressions    |");
			System.out.println("|                                           |");
			System.out.println("| [8]  Quit Program                         |");
			System.out.println("|___________________________________________|");
			System.out.print("Please select an option: ");
			int choice = in.nextInt();
			

			//decision structure based on user choice
			switch(choice)
			{
				//case 1 will create an empty linked list
				case 1:
				{
					System.out.println("===========================================");
					list = new LinkedList();
					System.out.println("Empty Linked List created.");
					emptyList = false;
					menu();
					break;
				}
				//case 2 will insert node based on specific position
				case 2:
				{
					if(!emptyList)
					{
						getChoiceTwo(in);
					}
					else
					{
						System.out.println("===========================================");
						System.out.println("You did not create an empty Linked List.");
					}
	
					menu();
					break;
				}
				//Case 3 will remove node based on specific position
				case 3:
				{
					System.out.println("===========================================");
				
					if(!emptyList)
					{
						getChoiceThree(in);
					}
					else
					{
						System.out.println("You did not create an empty Linked List.");
					}
					
					menu();
					break;
				}
				//Case 4 will display the size of the linked list
				case 4:
				{
					
					if(!emptyList)
					{
						System.out.println("===========================================");
						System.out.println("Size of Linked List is " + (list.getSize()-1));
					}
					else
					{
						System.out.println("You did not create an empty Linked List.");
					}
					
					menu();
					break;
				}
				//case 5 will display the info of a node based on a specific position provided by user
				case 5:
				{
					System.out.println("===========================================");
					
					if(!emptyList)
					{
						System.out.println("What is the position of the info would you like to see? [1 - " + (list.getSize()-1) + "]");
						System.out.print("Position: ");
						int position = in.nextInt();
						if(position > (list.getSize()-1))
						{
							System.out.println("Position must be within range [1 - " + (list.getSize()-1) + "]");
						}
						else if(position < 1)
						{
							System.out.println("Position must be within range [1 - " + (list.getSize()-1) + "]");
						}
						else
						{
							System.out.println("");
							System.out.println("At position " + position + ", the data is: " + list.getData(position));
	
						}
					}
					else
					{
						System.out.println("You did not create an empty Linked List.");
					}
	
	
					menu();
					break;
				}
				//case 6 will display the whole linked list data 
				case 6:
				{
					System.out.println("===========================================");
					
					if(!emptyList)
					{
						if(list.getSize() == 1)
						{
							System.out.println("There is nothing in your Linked List.");
						}
						else
						{
							for(int ii = 1; ii <= list.getSize();ii++)
							{
								System.out.print(list.getData(ii));
							}
							System.out.println("");
						}
	
					}
					else
					{
						System.out.println("You did not create an empty Linked List.");
					}
					
					
					menu();
					break;
				}
				//case 7 will begin the roman numeral program
				case 7:
				{
					Roman roman = new Roman();
					roman.romanNumeralMain();
					menu();
					break;
				}
				//case 8 will allow user to exit program
				case 8:
				{
					System.out.println("===========================================");
					System.out.println("Goodbye.");
					repeat = false;
					break;
				}
				//any other choice will prompt user incorrect and then recall menu
				default:
				{
					System.out.println("Input is not correct.");
					menu();
					break;
				}
			
			}
		}
		//catches if user input during menu decision is anything but a integer
		catch(InputMismatchException mismatch)
		{
			System.out.println("Error: Please enter a valid selection from menu.");
			menu();
		}

	}
	
	/**
	 * GetChoiceTwo is a function if the user choice is 2 from the menu.
	 *  This will allow user to either enter a string or individual 
	 *  character that will be added to a linked list.
	 * @param in Scanner properties that allow program to take in user's input
	 */
	public static void getChoiceTwo(Scanner in)
	{
		System.out.println("===========================================");
		System.out.println(" __________________________________________");
		System.out.println("|                                          |");
		System.out.println("|  [1]  Split each character of String     |");
		System.out.println("|  [2]  Enter individual Characters        |");
		System.out.println("|  [3]  Exit                               |");
		System.out.println("|__________________________________________|");
		System.out.print("Please choose an option to input: ");
		int decisions = in.nextInt();
		
		switch(decisions)
		{
			//choice 1 allows user to input string
			case 1:
			{
				System.out.print("Please enter a string: ");
				String input = in.next();
				
				for(int ii = 0; ii < input.length(); ii++)
				{
					list.add(input.charAt(ii));
				}
				break;
			}
			//choice 2 will allow user to input individual characters at specific positions
			case 2:
			{
				boolean again = true;
				
				do
				{

					
					System.out.println("What character would you like to input?");
					System.out.print("Character: ");
					
					char input = in.next().charAt(0);
					
					System.out.println("");
					System.out.println("What position would you like to insert it? [1 - " + list.getSize() +"]");
					System.out.print("Position: ");
					int input2 = in.nextInt();
					
					//decision structure to see if valid index
					if(input2 > list.getSize())
					{
						System.out.println("Position must be within range [1 - " + list.getSize() + "]");
					}
					else if(input2 < 1)
					{
						System.out.println("Position must be within range [1 - " + list.getSize() + "]");
					}
					else
					{
						System.out.println("");
						list.add(input,input2);
					}
					
					System.out.print("Would you like to input another? [y][n]: ");
					char decision = in.next().charAt(0);
					boolean check = true;
					
					do
					{
						if(decision == 'y')
						{
							again = true;
							check = false;
						}
						else if(decision == 'n')
						{
							again = false;
							check = false;
						}
						else
						{
							System.out.print("Please enter a valid [y][n]: ");
							decision = in.next().charAt(0);
						}
					}while(check);
				}while(again);
				
				break;

			}
			//case 3 will exit submenu
			case 3:
			{
				System.out.println("Returning to main menu...");
				break;
			}
			default:
			{
				System.out.println("Invalid choice. Please try again.");
				getChoiceTwo(in);
				break;
			}
		}

	}

	/**
	 * The function getChoiceThree is executed when user choice is 3 in menu. This will prompt user to enter an index to remove from linked list.
	 * @param in Scanner properties that allow program to take in user's input
	 */
	public static void getChoiceThree(Scanner in)
	{
		System.out.println("What is the position of the info would you like to remove? [1 - " + (list.getSize()-1) + "]");
		System.out.print("Position: ");
		int positionRemove = in.nextInt();
		
		//decision structure to see if index is a valid index
		if(positionRemove > (list.getSize()-1))
		{
			System.out.println("Position must be within range [1 - " + (list.getSize()-1) + "]");
		}
		else if(positionRemove < 1)
		{
			System.out.println("Position must be within range [1 - " + (list.getSize()-1) + "]");
		}
		else
		{
			System.out.println("");
			list.removeData(positionRemove);
		}
	}
	

}
