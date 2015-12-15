package Assignment4;

/**
 * This class will implement a Queue of character. The queue will either enqueue, dequeue, peek, isempty.
 * @author Daniel Son
 */
public class CharacterQueue
{

    private static char[] characterQueue;
    private static int enqueueHere, dequeueHere, maxQueueSize, queueSize;
    /**
     * Constructor initializes Queue with a default size of 10 and variables are initialized
     */
    public CharacterQueue()
    {
        characterQueue = new char[10];
        enqueueHere = 0;
        dequeueHere = 0;
        maxQueueSize = 10;
        queueSize = 0;
    }
    /**
     * Constructor initializes Queue size based on parameter given and variables are initialized
     * @param size
     */
    public CharacterQueue(int size)
    {
        characterQueue = new char[size];
        maxQueueSize = size;
        enqueueHere = 0;
        dequeueHere = 0;
        queueSize = 0;
    }
    /**
     * Function puts in value into queue
     * @param input character that will be put into queue
     */
    public void enqueue(char input)
    {
        characterQueue[enqueueHere] = input;
        queueSize++;
        if(queueSize >= maxQueueSize)
        {
        	char[] temp = new char[maxQueueSize + 1];
        	System.arraycopy(characterQueue, 0, temp, 0, maxQueueSize);
        	characterQueue = temp;
        	maxQueueSize = characterQueue.length;
        	
        }
        
        if(enqueueHere == characterQueue.length -1)
        {
            enqueueHere = (enqueueHere + 1) % maxQueueSize;

        }
        else
        {
            enqueueHere++;

        }

    }
    /**
     * function will remove first queued in value and return it to user
     * @return first queued value
     */
    public char dequeue()
    {
        if(dequeueHere == characterQueue.length)
        {
            dequeueHere = 0;
            queueSize--;
            return characterQueue[dequeueHere];
        }
        else if(queueSize == 0)
        {
        	//do nothing
        	return 0;
        }
        else
        {
            dequeueHere++;
            queueSize--;
            return characterQueue[dequeueHere - 1];
        }
    }
    /**
     * shows value of first queued in the Queue
     * @return first value that was queued
     */
    public char peek()
    {
        return characterQueue[dequeueHere];
    }
    /**
     * Shows the current size of the queue
     * @return current size of queue
     */
    public int size()
    {
        return queueSize;
    }
    /**
     * function checks to see if queue is empty or not
     * @return boolean based on if queue is empty
     */
    public boolean isEmpty()
    {
        return (queueSize == 0);
    }
 

}
