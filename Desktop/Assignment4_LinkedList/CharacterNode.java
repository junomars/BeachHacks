package Assignment4;
/**
 * This Class CharacterNode will create nodes based on parameters 
 * It will do the functions of returning the Info, assigning info, getting the next node, assigning the next node
 * @author Daniel Son
 *
 */
public class CharacterNode
{
	private char info;
	private CharacterNode nextNode;

	/**
	 * Initializes a Node of character based on the data
	 * @param info A character that will be stored in the node
	 */
	public CharacterNode(char info)
	{
		this.nextNode = null;
		this.info = info;
	}
	
	/**
	 * Initilaizes a node of character based on two parameters being a node pointing towards the previous node 
	 * and the info being placed in the node
	 * @param input A node that will be pointed to previous node
	 * @param info The character that will be stored in the node
	 */
	public CharacterNode(CharacterNode input, char info)
	{
		this.info = info;
		this.nextNode = input;
	}
	
	/**
	 * Returns the current character data
	 * @return the current character
	 */
	public char getInfo()
	{
		return info;
	}
	
	/**
	 * assigns the data into the data of the node
	 * @param input the character being stored
	 */
	public void assignInfo(char input)
	{
		info = input;
	}
	
	/**
	 * returns the node next to the current node
	 * @return the node next to the current node
	 */
	public CharacterNode getNext()
	{
		return nextNode;
	}
	
	/**
	 * assigns the next node with another node
	 * @param input a character node
	 */
	public void assignNext(CharacterNode input)
	{
		nextNode = input;
	}
}
