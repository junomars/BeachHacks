package assignment3;

/**
 * @author Daniel Son
 */
public class CharacterStack
{
    private char[] characterStack;
    private int top;
    /**
     * initializes Stack with a default size of 20 and initializes variables
     */
    public CharacterStack()
    {
        characterStack = new char[20];
        top = -1;
    }
    /**
     * Initializes Stack based on the parameters given and initializes variables
     * @param size
     */
    public CharacterStack(int size)
    {
        characterStack = new char[size];
        top = -1;
    }
    /**
     * Function puts character into stack
     * @param input character that will be pushed into stack
     */
    public void push(char input)
    {
        characterStack[++top] = input;
    }
    /**
     * Removes and returns value of the stack 
     * @return recent value of the stack
     */
    public char pop()
    {
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

}
