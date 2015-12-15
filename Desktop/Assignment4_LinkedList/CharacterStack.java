package Assignment4;

/**
 * This program will implement a stack of character. You can pop a value, push a value, isEmpty the stack and peek the value.
 * @author Daniel Son
 */
public class CharacterStack
{
    private char[] characterStack;
    private int top;
    private int size;
    /**
     * initializes Stack with a default size of 20 and initializes variables
     */
    public CharacterStack()
    {
        characterStack = new char[20];
        top = -1;
        size = 0;
    }
    /**
     * Initializes Stack based on the parameters given and initializes variables
     * @param size
     */
    public CharacterStack(int size)
    {
        characterStack = new char[size];
        top = -1;
        this.size = 0;
    }
    /**
     * Function puts character into stack
     * @param input character that will be pushed into stack
     */
    public void push(char input)
    {
        size++;
        characterStack[++top] = input;

    }
    /**
     * Removes and returns value of the stack 
     * @return recent value of the stack
     */
    public char pop()
    {
       // size--;
    	return characterStack[top--];
        
    }
    /**
     * checks to see if stack is empty or not
     * @return boolean based on if stack is empty
     */
    public boolean isEmpty()
    {
        return (top == -1);
    }
    /**
     * returns the value that is on top of the stack but does not remove it from stack
     * @return the value on top of stack
     */
    public char peek()
    {
        return characterStack[top];
    }
    /**
 	* the function getSize will get give the amount of elements within the stack
 	* @return integer value of the size
 	*/
    public int getSize()
    {
    	return size;
    }
}
