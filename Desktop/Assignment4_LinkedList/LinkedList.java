package Assignment4;
/**
 * This Class linkedlist is replicated of the generic linked list. 
 * This class will have the functionality of adding data into the linked list, adding data into the linked list based on
 * an index. getting the data from the current linked list. removes the current data from the linked list. gets the
 * size of the linked list
 * @author Daniel Son
 *
 */
public class LinkedList 
{
	private CharacterNode head;
	private int count;
	
	/**
	 * initializing the global variables to default
	 */
	public LinkedList()
	{
		head = new CharacterNode(' ');
		count = 1;
	}
	
	/**
	 * Adds the data into the linked list. It will add to the top of the index
	 * @param input the data being stored 
	 */
	public void add(char input)
	{
		CharacterNode temp = new CharacterNode(input);
		CharacterNode current = head;
		
		while(current.getNext() != null)
		{
			current = current.getNext();
		}
		
		current.assignNext(temp);
		count++;
		
	}
	
	/**
	 * adds the data into the linked list based on the parameter of the index.
	 * @param input the data being stored
	 * @param index the spot where the data will be added to
	 */
	public void add(char input, int index)
	{
		CharacterNode temp = new CharacterNode(input);
		CharacterNode current = head;
		
		for(int ii = 1; ii < index && current.getNext() != null; ii++)
		{
			current = current.getNext();
		}
		
		temp.assignNext(current.getNext());
		current.assignNext(temp);
		count++;
	}
	
	/**
	 * returns the data back to the user based on the index
	 * @param index the spot where the user wants the data returned
	 * @return
	 */
	@SuppressWarnings("null")
	public char getData(int index)
	{
		if(index <= 0)
		{
			System.out.println("index must be 1 or greater.");
			return (Character)null;
		}
		else if(index > getSize())
		{
			System.out.println("Index must be within range.");
			return (Character)null;
		}
		
		CharacterNode current = head.getNext();
		
		for(int ii = 1; ii < index; ii++)
		{
			if(current.getNext() == null)
			{
				return ' ';
			}
			
			current = current.getNext();
		}
		return current.getInfo();
	}
	
	/**
	 * removes the data based on the spot the user provides
	 * @param index the spot where the data will be removed
	 */
	public void removeData(int index)
	{
		if(index < 1 || index > getSize())
		{
			System.out.println("Index is out of range.");
		}
		else
		{
			CharacterNode current = head;
			
			for(int ii = 1; ii < index; ii++)
			{
				if(current.getNext() == null)
				{
					//do nothing
				}
				else
				{
					current = current.getNext();
				}
			}
			current.assignNext(current.getNext().getNext());
			count--;
		}
		
	}
	
	/**
	 * Will return how many data is in the linked list
	 * @return the size of the linked list
	 */
	public int getSize()
	{
		return count;
	}
	
	
}
