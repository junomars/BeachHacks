package Assignment6;

/**
 * This class BinaryHeap creates a binary tree that is sorted by a max or min heap. It is implemented using an array.
 * The index 0 of the array is not used. 
 * @author DanielSon
 *
 */
public class BinaryHeap 
{
	private static int[] dataArray;
	private static int index;
	/**
	 * BinaryHeap Constructor that initializes variables.
	 * array size is set to 32 based on specifications on assignment 6 homework.
	 */
	public BinaryHeap()
	{
		//default size based on assignment 6
		dataArray = new int[32];
		//leaving index 0 of array empty.
		index = 1;
	}
	
	/**
	 * The method insert will take a data and insert it into the array
	 * @param data An integer that is passed into the method that will be stored in an array
	 */
	public void insert(int data)
	{
		dataArray[index] = data;
		index++;
	}
	
	/**
	 * This method will check if there is any same numbers in the data array. If there is, the method will return a value of true.
	 * @param data An integer that will be checked to see if it is repeated in the array
	 * @return a boolean of true or false based on if there are any duplicates
	 */
	public boolean checkDuplicates(int data)
	{
		boolean duplicate = false;
		for(int ii = 1; ii <= 31; ii++)
		{
			if(data == dataArray[ii])
			{
				duplicate = true;
			}
		}
		
		return duplicate;
	}

	/**
	 * This method will swap two data using two index points in the array
	 * @param index1 An integer that points to one data index in the array
	 * @param index2 An integer that points to one data index in the array
	 */
	private static void swapData(int index1, int index2)
	{
		int temp = dataArray[index1];
		dataArray[index1] = dataArray[index2];
		dataArray[index2] = temp;
	}
	
	/**
	 * This function will begin the sorting algorithm of a Max Heap Sort
	 */
	public void startMaxHeapSort()
	{
		for(int ii = 0; ii <= 16; ii++)
		{
			sortMaxHeap();
		}
	}
	
	/**
	 * This function will begin the sorting algorithm of a Min Heap Sort
	 */
	public void startMinHeapSort()
	{
		for(int ii = 0; ii <= 16; ii++)
		{
			sortMinHeap();
		}
	}
	
	/**
	 * This function will sort the binary tree in a min heap.
	 */
	private void sortMinHeap()
	{
		for(int ii = 1; ii < 16; ii++)
		{
			int parent = dataArray[ii];
			int leftChildIndex = ii * 2;
			int leftChild = dataArray[leftChildIndex];
			int rightChildIndex = ii * 2 + 1;
			int rightChild = dataArray[rightChildIndex];
			
			if(leftChild < parent)
			{
				swapData(leftChildIndex, ii);
				displayTree();
				System.out.println("=============================================================================================");
			}
			if(rightChild < parent)
			{
				swapData(rightChildIndex, ii);
				displayTree();
				System.out.println("=============================================================================================");
			}

		}
	}

	
	/**
	 * This function will sort the binary tree in a max heap
	 */
	private void sortMaxHeap()
	{	
			for(int ii = 1; ii < 16; ii++)
			{
				int parent = dataArray[ii];
				int leftChildIndex = ii * 2;
				int leftChild = dataArray[leftChildIndex];
				int rightChildIndex = ii * 2 + 1;
				int rightChild = dataArray[rightChildIndex];
				
				if(leftChild > parent)
				{
					swapData(ii, leftChildIndex);
					displayTree();
					System.out.println("=============================================================================================");
				}
				if(rightChild > parent)
				{
					swapData(ii, rightChildIndex);
					displayTree();
					System.out.println("=============================================================================================");
				}

			}

	}
	
	/**
	 * This function will display the dataArray in order from index 1 to 31
	 */
	public void displayData()
	{
		for(int ii = 1; ii <= 31; ii++)
		{
			System.out.print(" " + dataArray[ii]);
		}
	}
	
	/**
	 * This function will display the tree in a tree format
	 */
	public void displayTree()
	{
		System.out.println("                                              " + dataArray[1]);
		System.out.println("                                              |");
		System.out.println("                       " + dataArray[2] + "---------------------^-----------------------" + dataArray[3]);
		System.out.println("                       |                                               |");
		System.out.println("          " + dataArray[4] +  "-----------^----------" +  dataArray[5] + "                      " + dataArray[6] + "-----------^----------" +  dataArray[7]);
		System.out.println("          |                       |                       |                       |");
		System.out.println("    " + dataArray[8] + "----^----" + dataArray[9] +  "           " + dataArray[10] + "----^----" + dataArray[11] + "           " + dataArray[12] + "----^----" + dataArray[13] + "           " + dataArray[14] + "----^----" + dataArray[15]);
		System.out.println("    |           |           |           |           |           |           |           |");
		System.out.println(dataArray[16] + "--^--" + dataArray[17] +"   " + dataArray[18] + "--^--" + dataArray[19] + "   " + dataArray[20] + "--^--" + dataArray[21] + "   " + dataArray[22] + "--^--" + dataArray[23] + "   " + dataArray[24] + "--^--" + dataArray[25] + "   " + dataArray[26] + "--^--" + dataArray[27] + "   " + dataArray[28] + "--^--" + dataArray[29] + "   " + dataArray[30] + "--^--" + dataArray[31]);
		
	}
}
