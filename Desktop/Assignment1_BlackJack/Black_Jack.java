import java.util.Scanner;

public class Black_Jack 
{

	static int[] playersHand;
	static int[] dealersHand;
	
	
	public void Blackjack() 
	{
		Deck cards = new Deck();
		String[] deck = cards.createNewDeck();
		int[] deckValue = cards.giveDeckValue();

		//shuffling the deck 6 times for good shuffle
		for(int ii = 0; ii <= 6; ii++)
		{
			deck = cards.shuffleDeck(deck, deckValue);
		}
		
		//access global variable in deck
		deckValue = Deck.deckValueStorage;

		int[] playersHand = new int[5];
		int[] dealersHand = new int[5];
		
		int playerMoney = 100;
		int moneyBet;
		
		@SuppressWarnings("resource")
		Scanner getInput = new Scanner(System.in);
		
		boolean stop = false;
		
		System.out.println("You have $" + playerMoney + ". How much do you want to bet?");
		moneyBet = getInput.nextInt();
		
		while(!stop)
		{
			
		}
		
		
	}
	
	


}
