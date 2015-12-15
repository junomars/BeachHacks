import java.util.Scanner;

public class Assignment_1 
{

	static String[] deckOfCards;
	static int cardCounter;
	
	public static void main(String[] args)
	{
		menu();
	}
	public static void menu()
	{
		//Main menu
		System.out.println(" _______________________________________________");
		System.out.println("|  Menu:                                        |");
		System.out.println("|  Create a new deck                        [1] |");
		System.out.println("|  Deal 4 cards and show the remainer cards [2] |");
		System.out.println("|  Shuffle the card and show the cards      [3] |");
		System.out.println("|  Play the Blackjack game                  [4] |");
		System.out.println("|  Exit                                     [0] |");
		System.out.println("|_______________________________________________|");
		System.out.print("Selection: ");
		
		//Do not close Scanner so program can repeat.
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int selection = input.nextInt();
		Deck deck = new Deck();
		Cards card = new Cards();
		
		//menu decisions
		switch(selection)
		{
		case 1: //Creates a new deck
			{
				deckOfCards = deck.createNewDeck();
				
				for(int ii = 0; ii <= deckOfCards.length - 1; ii++)
				{
					System.out.println(deckOfCards[ii]);
				}
					
				menu();
				break;
			}
		case 2: //Deals 4 cards and show the number of remainder cards
			{
				if(deckOfCards == null)
				{
					System.out.println("You have not created a deck yet!");
					menu();
					break;
				}
				else
				{
					cardCounter = card.giveFourCards(deckOfCards, cardCounter);
					menu();
					break;
				}
			}
		case 3: //Shuffles card and shows the cards
			{
				if(deckOfCards == null)
				{
					System.out.println("You have not created a deck yet!");
					menu();
					break;
				}
				else
				{
					deckOfCards = deck.shuffleDeck(deckOfCards, null);
					
					for(int dd = 0; dd <= deckOfCards.length - 1; dd++)
					{
						System.out.println(deckOfCards[dd]);
					}
					
					menu();
					break;
				}
				
			}
		case 4: //Black Jack game start
			{
				Black_Jack game = new Black_Jack();
				game.Blackjack();
				menu();
				break;
			}
		case 0: //Exit menu and program
			{
				System.out.println("Goodbye.");
				break;
			}
		default:
			{
				System.out.println("Please enter a valid number.");
				System.out.println("");
				menu();
				break;
			}
			

		}
		
	}

}
