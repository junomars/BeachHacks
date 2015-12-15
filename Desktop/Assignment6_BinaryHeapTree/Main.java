package Assignment6;
import java.util.Random;
import java.util.Scanner;

/**
 * This Class is the main class that will be executed.
 * @author DanielSon
 *
 */
public class Main 
{
	/**
	 * The method main is the beginning of the program and will run the whole program itself
	 * @param args allows arguments to be passed
	 */
	public static void main(String[] args) 
	{
		Random random = new Random();
		BinaryHeap heapTree = new BinaryHeap();
		
		for(int ii = 1; ii <= 31; ii++)
		{
			int num = randomGenerator(random);
			
			boolean duplicate = heapTree.checkDuplicates(num);
			
			//loop to make sure tree does not have duplicates
			while(duplicate == true)
			{
				num = randomGenerator(random);
				duplicate = heapTree.checkDuplicates(num);
			}
			
			heapTree.insert(num);
		}
		//displays tree before sorting into a max heap tree
		heapTree.displayTree();
		System.out.println("=============================================================================================");
		System.out.println("Start Max Heap Sort Tree.");
		getKeyToContinue();
		
		//sorts tree as a max heap
		heapTree.startMaxHeapSort();

		//prompts user that sorting has finished and displays data
		System.out.println("Heap Tree is finished sorting in a Max Heap tree. Tree shown above. Tree is shown below with data.");
		System.out.println("");
		
		//displays data
		System.out.print("Sorted data: ");
		heapTree.displayData();
		System.out.println("");
		
		//gets key to continue 
		getKeyToContinue();
		System.out.println("");
		System.out.println("=============================================================================================");

		//displays tree before sorting into a min heap tree
		heapTree.displayTree();
		System.out.println("Start Min Heap Sort Tree.");
		getKeyToContinue();
		
		//sorts tree as a min heap
		heapTree.startMinHeapSort();
		
		//prompts user that sorting has finished and displays data
		System.out.println("Heap Tree is finished sorting in a Min Heap tree. Tree shown above. Tree is shown below with data.");
		System.out.println("");
		
		//displays data
		System.out.print("Sorted data: ");
		heapTree.displayData();
		System.out.println("");
		
	}
	
	/**
	 * The method getKeyToContinue pauses the program and runs when user enters any key
	 */
	public static void getKeyToContinue()
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Press a key to continue...");
		in.nextLine();
		
	}
	
	/**
	 * The method randomGenerator produces a random number between 10 through 99
	 * @param random An object that takes property of the Java class Random
	 * @return a random number that is generated between 10 through 99
	 */
	public static int randomGenerator(Random random)
	{
		return random.nextInt(89) + 10;
	}

}
