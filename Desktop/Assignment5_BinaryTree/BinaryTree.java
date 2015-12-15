package Assignment5;
/**
 * The binaryTree class will create a binary tree with the functions of insert, remove, switching childs,
 * getting height, displayings the data in Inorder, Postorder, Preorder. finding data Node and checking if tree is balanced
 * This tree is a self balancing binary search tree.
 * @author Daniel Son
 *
 */
public class BinaryTree 
{

	private static Node root;
	private static int index, numOfItems, counter;
	private static int[] storageArray;

	/**
	 * Constructor initializing variables 
	 */
	public BinaryTree()
	{
		root = null;
		numOfItems = 0;
		counter = 0;
	}
	/**
	 * Constructor initializing variables based on parameters
	 * @param node node set to being root
	 * @param num taking value of number of items in tree
	 */
	public BinaryTree(Node node, int num)
	{
		root = node;
		numOfItems = num;
	}
	/**
	 * Gets the quantity of the tree
	 * @return an Integer that represents the number of data in tree
	 */
	public static int getTreeQuantity()
	{
		return numOfItems;
	}
	/**
	 * Public method that allows user to insert data into binary tree
	 * @param data the data that will be inserted into binary tree
	 */
	public void insert(int data)
	{
		//iterates number of data being passed
		numOfItems++;
		//calls private function to insert into binary tree
		root = insert(root, data);
	}
	/**
	 * Private method that inserts user data into binary tree. This method is self balancing.
	 * @param node Node referred to the binary tree
	 * @param data Provided by user to be input into binary tree
	 * @return node back to the program to be reassigned
	 */
	private Node insert(Node node, int data)
	{
		if(node == null)
		{
			//creates beginning node
			node = new Node(data);
		}
		else if(data < node.data)
		{
			node.leftNode = insert(node.leftNode, data);
			if(getHeight(node.leftNode) - getHeight(node.rightNode) == 2)
			{
				if(data < node.leftNode.data)
				{
					node = switchWithLeftChild(node);
				}
				else
				{
					node = switchTwiceWithLeftChild(node);
				}
			}
		}
		else if(data > node.data)
		{
			node.rightNode = insert(node.rightNode, data);
			if(getHeight(node.rightNode) - getHeight(node.leftNode) == 2)
			{
				if(data > node.rightNode.data)
				{
					node = switchWithRightChild(node);
				}
				else
				{
					node = switchTwiceWithRightChild(node);
				}
			}
		}
		node.height = computeMax(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
		
		return node;
	}
	/**
	 * This method computes the maximum height of the left and right child of a node
	 * @param leftChild the left child of the node
	 * @param rightChild the right child of the node
	 * @return an Integer that gives the max height of the tree
	 */
	private int computeMax(int leftChild, int rightChild)
	{
		if(leftChild > rightChild)
		{
			return leftChild;
		}
		else
		{
			return rightChild;
		}
	}
	/**
	 * This method switches the left child with the parent
	 * @param node The node that will be switched
	 * @return The new node that was constructed 
	 */
	private Node switchWithLeftChild(Node node)
	{
		//creates new node then takes leftNode to new nodes root
		Node node2 = node.leftNode;
		//leftNode of the new node takes rightNode
		node2.leftNode = node.rightNode;
		//the new node is assigned back into the rightNode of original node
		node.rightNode = node2;
		
		//recalculating heights for both nodes
		node2.height = computeMax(getHeight(node2.leftNode), getHeight(node2.rightNode)) + 1;
		node.height = computeMax(getHeight(node2.leftNode), node.height) + 1;
		
		return node;
		
	}
	/**
	 * This method switches the right child with the parent
	 * @param node The node that will be switched
	 * @return the new node that was constructed
	 */
	private Node switchWithRightChild(Node node)
	{
		//creates new node then takes rightNode to new nodes root
		Node node2 = node.rightNode;
		//RightNode of original takes leftNode of the new node
		node.rightNode = node2.leftNode;
		//new node takes the original node to its leftNode
		node2.leftNode = node;
		
		//recalculating heights for both nodes
		node2.height = computeMax(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
		node.height = computeMax(getHeight(node2.rightNode), node.height) + 1;
		
		return node2;
	}
	/**
	 * This method is similar to switchWithRightChild but only does the operation twice
	 * @param node the node that will be switched
	 * @return the new constructed node that was switched
	 */
	private Node switchTwiceWithLeftChild(Node node)
	{
		//same concept as switchWithLeftChild function, only doing it twice
		node.leftNode = switchWithRightChild(node.leftNode);
		return switchWithLeftChild(node);
	}
	/**
	 * This method is similiar to the switchWithRightChild but only does the operation twice
	 * @param node the node that will be switched
	 * @return the new constructed node that was switched
	 */
	private Node switchTwiceWithRightChild(Node node)
	{
		//same concept as switchWithRightChild function, only doing it twice
		node.rightNode = switchWithRightChild(node.rightNode);
		return switchWithLeftChild(node);
	}
	/**
	 * This function will get the height of the binary tree
	 * @param node The node that will be height evaluated
	 * @return An integer value that represents the height of the binary tree
	 */
	public static int getHeight(Node node)
	{
		
		if(node == null)
		{
			return -1;
		}
		else
		{
			//height data within node
			return node.height;
		}

	}
	/**
	 * This function will display the data InOrder
	 * @param root the tree that will be displayed
	 */
	public void displayDataInOrder(Node root)
	{
		if(root != null)
		{
			displayDataInOrder(root.leftNode);
			System.out.print(" " + root.data);
			displayDataInOrder(root.rightNode);
		}
	}
	/**
	 * This function will display the data PostOrder
	 * @param root the tree that will be displayed
	 */
	public void displayDataPostOrder(Node root)
	{
		if(root != null)
		{
			displayDataPostOrder(root.leftNode);
			displayDataPostOrder(root.rightNode);
			System.out.print(root.data + " ");
		}
	}
	/**
	 * This function will display the data PreOrder
	 * @param root the tree that will be displayed
	 */
	public void displayDataPreOrder(Node root)
	{
		if(root != null)
		{
			System.out.print(root.data + " ");
			displayDataPreOrder(root.leftNode);
			displayDataPreOrder(root.rightNode);
		}
	}
	/**
	 * This function will find what level a data resides in a node.
	 * @param data the data that is being searched
	 * @return An integer that represents what level the node carrying the data is in
	 */
	public int findItem(int data)
	{
		Node currentNode = root;
		
		int count = 0;
		
		//loop until finds data
		while(currentNode != null)
		{
			//checks if currentNode contains data
			if(currentNode.data == data)
			{
				count++;
				return count;
			}
			//traversing to the leftNode based on data condition
			else if(currentNode.data > data)
			{
				currentNode = currentNode.leftNode;
			}
			//traversing to the rightNode based on data condition
			else
			{
				currentNode = currentNode.rightNode;
			}
			
			count++;
		}
		//returns -1 if nothing is within the node.
		return -1;
	}
	/**
	 * This function will give the root node
	 * @return the root node
	 */
	public Node getRoot()
	{
		//a way to access the root data
		return root;
	}
	/**
	 * This function will find the data the user wants to delete and find the node and delete it.
	 * It will also reconnect the binary tree to compensate for the deleted node
	 * @param data the data the user wants to delete
	 * @return a boolean of true or false based on if the data was found and deleted or not found.
	 */
	public boolean delete(int data)
	{
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		
		
		while(current.data != data)
		{
			parent = current;
			//traversing tree based on conditions
			if(current.data > data)
			{
				isLeftChild = true;
				current = current.leftNode;
			}
			else
			{
				isLeftChild = false;
				current = current.rightNode;
			}
			if(current == null)
			{
				return false;
			}
		}
		//node has no child
		if(current.leftNode == null && current.rightNode == null)
		{
			//deleting root data if only one
			if(current==root)
			{
				root = null;
			}
			//
			if(isLeftChild ==true)
			{
				parent.leftNode = null;
			}
			else
			{
				parent.rightNode = null;
			}
		}
		//if node to be deleted has only one child
		else if(current.rightNode == null)
		{
			if(current==root)
			{
				root = current.leftNode;
			}
			else if(isLeftChild)
			{
				parent.leftNode = current.leftNode;
			}
			else
			{
				parent.rightNode = current.leftNode;
			}
		}
		else if(current.leftNode == null)
		{
			if(current==root)
			{
				root = current.rightNode;
			}
			else if(isLeftChild)
			{
				parent.leftNode = current.rightNode;
			}
			else{
				parent.rightNode = current.rightNode;
			}
		}
		else if(current.leftNode != null && current.rightNode != null)
		{
			
			//now we have found the minimum element in the right sub tree
			Node child = getChild(current);
			if(current==root)
			{
				root = child;
			}
			else if(isLeftChild)
			{
				parent.leftNode = child;
			}
			else{
				parent.rightNode = child;
			}			
			child.leftNode = current.leftNode;
		}	
		numOfItems--;
		return true;		
	}
	/**
	 * This function will find the child of the node of the deleted node
	 * @param deleteNode The node that was deleted from the binary tree when user specified
	 * @return the child of the deleted node
	 */
	public Node getChild(Node deleteNode)
	{
		Node child = null;
		Node childsParent = null;
		Node current = deleteNode.rightNode;
		while(current != null)
		{
			childsParent = child;
			child = current;
			current = current.leftNode;
		}
		// if has right child, add it to the left
		if(child != deleteNode.rightNode)
		{
			childsParent.leftNode = child.rightNode;
			child.rightNode = deleteNode.rightNode;
		}
		return child;
	}
	/**
	 * This function checks to see if the binary tree is height balanced or not
	 * @param root the tree that will be evaluated
	 * @return a boolean of true or false based on if the tree is balanced or not
	 */
	public boolean checkBalanced(Node root)
	{
		if(root == null)
		{
			return true;
		}
		//checks to see if leftNode and rightNode are similiar or by one off
		else if((root.leftNode.height - root.rightNode.height) <= 1)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	/**
	 * This function will find the median node of the binary tree
	 * @param node the node that will be evaluated
	 * @return the node that is the median of the tree
	 */
	public static Node findMedian(Node node)
	{
		if(node == null)
		{
			return null;
		}
		
		Node left = findMedian(node.leftNode);
		
		if(left != null)
		{
			return left;
		}
		//condition that checks if median
		 if(index == numOfItems / 2)
		 {
			 return node;
		 }
		 index++;
		 
		 return findMedian(node.rightNode);
		
	}
	/**
	 * This function will get the data from the tree InOrder and will store it in an array
	 * @param current the node that is going to be stored within the array
	 */
	public static void getDataInOrder(Node current)
	{
		if(current == null)
		{
			return;
		}
		
		storageArray[counter] = current.data;
		counter++;
		getDataInOrder(current.leftNode);
		getDataInOrder(current.rightNode);
		
	}
	/**
	 * This function will try to balance the binary tree if needed
	 * @return the balanced tree
	 */
	public static BinaryTree balanceTree()
	{
		
		storageArray = new int[numOfItems];
		counter = 0;
		
		Node current = root;
		
		getDataInOrder(current);
		
		current = findMedian(current);
		
		BinaryTree newTree = new BinaryTree(current, numOfItems);
		
		boolean stop = true;
		
		for(int ii = 0; ii < storageArray.length; ii++)
		{
			if(stop == true)
			{
				if(current.data != storageArray[ii])
				{
					newTree.insert(storageArray[ii]);
					numOfItems--;
				}
			}
			
			if(current.data == storageArray[ii])
			{
				stop = false;
			}
			else
			{
				stop = true;
			}

		}
		
		return newTree;
		
		
	}
	
}
