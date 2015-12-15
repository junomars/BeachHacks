
public class Cards 
{
	static String[] deck;
	
	public int giveFourCards(String[] deckOfCards, int counter)
	{
		deck = deckOfCards;
		for(int sentinel = counter;counter < sentinel + 4; counter++)
		{
			System.out.print("{" + deck[counter] + "} ");
		}
		
		int cardRemainer = deck.length - counter;
		
		System.out.println("with " + cardRemainer + " amount of cards left.");
		
		return counter;
		
	}
	

	
}
