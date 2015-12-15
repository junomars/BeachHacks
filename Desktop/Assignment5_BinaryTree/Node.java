package Assignment5;
/**
 * The class Node is specifically designed for the binaryTree class. This class will store
 * a data and have two child nodes that will point to different nodes.
 * @author Daniel Son
 *
 */
public class Node 
{
	int data, height;;
	Node leftNode, rightNode;

	/**
	 * Constructor that initializes global variables of the class Node
	 */
	public Node()
	{
		leftNode = null;
		rightNode = null;
		data = 0;
		height = 0;
	}

	/**
	 * Constructor that initializes global variables based on the parameters given.
	 * @param data
	 */
	public Node(int data)
	{
		this.data = data;
		leftNode = null;
		rightNode = null;
		height = 0;
	}

}

