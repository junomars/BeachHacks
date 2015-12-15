import java.lang.String;
import java.util.Random;

public class Deck 
{

	
	static int[] deckValueStorage;
	
		public String[] createNewDeck()
		{
			int[] deckMold = new int[52];
			int count = 1;
			
			//Used to read position of deck
			for(int ii = 0; ii <= deckMold.length - 1; ii++)
			{
				if(count == 14)
				{
					count = 1;
				}	
				deckMold[ii] = count;
				count++;
			}
			
			String[] cardWithoutNumber = {"Ace", "Jack","Queen","King"};
			String[] suit = {"Diamonds", "Clubs", "Hearts", "Spades"};
			String[] deck = new String[52];
			int suitCount = 0;
			count = 2;
			
			//loop to set and assign deck
			for(int jj = 0; jj <= deck.length - 1; jj++)
			{
				if(deckMold[jj] == 1)
				{
					deck[jj] = cardWithoutNumber[0] + " of " + suit[suitCount];
				}
				else if(deckMold[jj] == 11)
				{
					deck[jj] = cardWithoutNumber[1] + " of " + suit[suitCount];
				}
				else if(deckMold[jj] == 12)
				{
					deck[jj] = cardWithoutNumber[2] + " of " + suit[suitCount];
				}
				else if(deckMold[jj] == 13)
				{
					deck[jj] = cardWithoutNumber[3] + " of " + suit[suitCount];
				}
				else
				{
					deck[jj] = count + " of " + suit[suitCount];
					count++;
				}
				
				if(jj == 12 || jj == 25 || jj == 38)
				{
					suitCount++;
				}
				
				if(count == 11)
				{
					count = 2;
				}
			}
			

			//print out to check if cards and value are correct
			
			
			return deck;
		}
		
		public int[] giveDeckValue()
		{
			int[] deckValue = new int[52];
			int counter = 0;
			
			for(int ii = 0; ii < 52; ii++)
			{
				if(ii == 13 || ii == 26 || ii == 39)
				{
					counter = 0;
				}
				
				if((ii >= 10 && ii < 13)|| (ii>= 23 && ii <=25)||(ii>=36 && ii<=38)|| (ii>=49 && ii<=51))
				{
					deckValue[ii] = counter;
				}
				else
				{
					counter++;
					deckValue[ii] = counter;
				}
				
			}
			
			return deckValue;
		}
			
		public String[] shuffleDeck(String[] deck, int[] deckValue)
		{
			String tempString;
			int tempInt;
			Random generator = new Random();
			int firstRandomNumber = numberGenerator(generator);
			
			//Shuffling algorithm
			
			
			for(int jj = 0; jj < deck.length - 1; jj++)
			{
				 tempString = deck[jj];
				 deck[jj] = deck[firstRandomNumber];
				 deck[firstRandomNumber] = tempString;
				 
				 tempInt = deckValue[jj];
				 deckValue[jj] = deckValue[firstRandomNumber];
				 deckValue[firstRandomNumber] = tempInt;
				 
				 firstRandomNumber = numberGenerator(generator);
			}
			
			/*displaying shuffled numbers
			for(int dd = 0; dd <= deck.length - 1; dd++)
			{
				System.out.println(deck[dd]);
			}
			*/
			
			//storing deckValue array
			returnDeckValue(deckValue);
			
			return deck;
		}	
		
		public static int[] returnDeckValue(int[] deckValue)
		{
			deckValueStorage = deckValue;
			return deckValueStorage;
		}
		
		public static int numberGenerator(Random generator)
		{
			return generator.nextInt(51) + 1;
		}
		
		
}


