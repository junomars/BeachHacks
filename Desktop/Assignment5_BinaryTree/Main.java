package Assignment5;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class is the main class that will run for Assignment 5. It will prompt the user with a menu.
 * User then has the choice of 1 through 7 based on the menu.
 * @author Daniel Son
 *
 */
public class Main {

	private static BinaryTree tree;
	private static boolean again, firstRun;
	
	/**
	 * The main function that the program will read and execute
	 * @param args allows to pass arguments
	 */
	public static void main(String[] args) 
	{
		//initialize object
		tree = new BinaryTree();
		firstRun = true;
		//infinite loop to menu unless user wants to quit
		again = true;
		do
		{
			menu();	
		}while(again);
		
	}

	/**
	 * This function is called in main and will display a menu where then the user can choose what will happen
	 */
	@SuppressWarnings("static-access")
	public static void menu()
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		try
		{
			//prompt user with menu
			System.out.println(" ________________________________ ");
			System.out.println("|__________Main Menu_____________|");
			System.out.println("|                                |");
			System.out.println("| [1]  Add item to tree          |");
			System.out.println("| [2]  Delete item from tree     |");
			System.out.println("| [3]  Find Item                 |");
			System.out.println("| [4]  Balance Tree              |");
			System.out.println("| [5]  List contents of tree     |");
			System.out.println("| [6]  Display Statistics        |");
			System.out.println("|                                |");
			System.out.println("| [7]  Quit Program              |");
			System.out.println("|________________________________|");
			System.out.print("Please select an option: ");
			int choice = in.nextInt();
			
			switch(choice)
			{
			//adds item to tree
			case 1:
			{
				boolean insertMore = true;
				
				do
				{
					System.out.print("Please enter an integer to add: ");
					int data = in.nextInt();
					
					tree.insert(data);
					
					System.out.println("Would you like to insert another integer? [y] [n]");
					System.out.print("Choice: ");
					char decisions = in.next().charAt(0);
					
					if(decisions == 'n')
					{
						insertMore = false;
					}
					
				}while(insertMore);
				
				firstRun = false;
				menu();
				break;
				
			}
			//delete items from tree
			case 2:
			{
				if(firstRun != true)
				{
					System.out.print("Please enter an integer to remove: ");
					int data = in.nextInt();
					
					boolean deleted = tree.delete(data);
					
					if(deleted == false)
					{
						System.out.println("Error: The number: " + data + " is not within the tree.");
					}
					else
					{
						System.out.println("Successfully deleted.");
					}
				}
				else
				{
					System.out.println("There is nothing to delete. Insert data in menu selection 1");
				}
				
				break;
			}
			//finds item in tree
			case 3:
			{
				if(firstRun != true)
				{
					System.out.print("Please enter an integer to find: ");
					int numToFind = in.nextInt();
					
					int level = tree.findItem(numToFind);
					
					if(level == -1)
					{
						System.out.println("Error: Integer is not in data structure.");
					}
					else
					{
						System.out.println("The number: [" + numToFind + "] was found on level " + level);
					}
				}
				else
				{
					System.out.println("There is nothing to find. Insert data in menu selection 1");
				}

				break;
			}
			//balances tree
			case 4:
			{
				if(firstRun != true)
				{
					boolean balanced = tree.checkBalanced(tree.getRoot());
					
					if(balanced == false)
					{
						System.out.println("Balancing tree...");
						
						tree = tree.balanceTree();
					}
					else
					{
						System.out.println("Tree is already balanced.");
					}
				}
				else
				{
					System.out.println("There is nothing to balance. Insert data in menu selection 1");
				}

					
				break;
			}
			//lists content in tree
			case 5:
			{		
				if(firstRun != true)
				{
					System.out.print("PreOrder: ");
					tree.displayDataPreOrder(tree.getRoot());
					System.out.println("");
					
					System.out.print("InOrder: ");
					tree.displayDataInOrder(tree.getRoot());
					System.out.println("");
					
					System.out.print("PostOrder: ");
					tree.displayDataPostOrder(tree.getRoot());
					System.out.println("");
				}
				else
				{
					System.out.println("There is nothing in the tree. Insert data in menu selection 1");
				}

				break;
			}
			//displays statistics 
			case 6:
			{
				if(firstRun != true)
				{
					System.out.println("Root Value: " + tree.getRoot().data);
					System.out.println("Depth of tree: " + tree.getHeight(tree.getRoot()));
					System.out.println("Number of items in tree: " + tree.getTreeQuantity());
				}
				else
				{
					System.out.println("There is no statistics because nothing in tree. Insert data in menu selection 1");
				}

				break;
			}
			//quits program
			case 7:
			{
				again = false;
				System.out.println("Exiting program...");
				System.out.println("Goodbye");
				break;
			}
			//checks input
			default:
			{
				System.out.println("Please enter a correct selection.");
				break;
			}
			}
		}
		catch(InputMismatchException mismatch)
		{
			System.out.println("Error: Please enter a valid number.");
			menu();
		}
	}
}
