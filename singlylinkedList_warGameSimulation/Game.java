/* Program Name: War_Game
 * 
 *    Date                Author           
 * 09/22/2015        Md Tanvirul Islam       
 */



package warGame;

import java.util.Scanner;


//Main class
public class Game 
{
	public static void main(String[] args) 
	{	
		// Two lists that holds player one and two's cards
		MyList<Card> player1Deck = new MyList<Card>();
		MyList<Card> player2Deck = new MyList<Card>();
		
		int numberOfRounds = 0;
	
		// Take input and distribute the cards
		Scanner input = new Scanner(System.in);
		distributeCards(input, player1Deck, player2Deck);
		input.close();
		
		// Simulate the game and count number of rounds
		numberOfRounds = simulateGame(player1Deck, player2Deck);
		
		// Get the result and print
		System.out.println(getResult(player1Deck, player2Deck, numberOfRounds));
	}
	
	
	
	// A method to take card deck as input and distribute cards among two players
	public static void distributeCards(Scanner input, MyList<Card> player1, MyList<Card> player2)
	{ 
		String[] cardsOnDeck = input.nextLine().split(" ");
		
		for (int i = 1; i <= cardsOnDeck.length; i++)
		{
			if (i%2 != 0)
			{
				assignCardToPlayer1:
				for (Card currentCard : Card.values())
				{
					if (cardsOnDeck[i-1].equals(currentCard.getCardName())) 
					{
						player1.addAtFront(currentCard);
						break assignCardToPlayer1;
					}
				}
			}
			else
			{
				assignCardToPlayer2:
				for (Card currentCard : Card.values())
				{
					if (cardsOnDeck[i-1].equals(currentCard.getCardName())) 
					{
						player2.addAtFront(currentCard);
						break assignCardToPlayer2;
					}
				}
			}
		}
	}
	
	
	// A method that simulated the game and counts number of rounds
	public static int simulateGame(MyList<Card> player1, MyList<Card> player2)
	{
		int rounds = 0;
		
		while (player1.getSize() != 0 && player2.getSize() != 0)
		{
			playCard(player1, player2);
			rounds++;
			
			if (rounds == 10000) break;
		}
		
		return rounds;
	}
	
	
	// A method to play a single round
	public static void playCard(MyList<Card> player1, MyList<Card> player2)
	{
		// Check if Player 1 won the round
		if (player1.getHeadData().getCardValue() > player2.getHeadData().getCardValue())
		{
			player1Scored(player1, player2);
		}
		
		// Check if Player 2 won the round
		else if (player1.getHeadData().getCardValue() < player2.getHeadData().getCardValue())
		{
			player2Scored(player1, player2);
		}
		
		// If the round is draw
		else
		{
			// The pile of cards that will be made as long as the round is draw
			MyList<Card> pileOfCards = new MyList<Card>();
			
			while (player1.getHeadData().getCardValue() == player2.getHeadData().getCardValue())
			{
				buildPile(pileOfCards, player1, player2);
				buildPile(pileOfCards, player1, player2);
			
				if (player1.getSize() == 0 || player2.getSize() == 0) 
					break;
			}
			
			// Check which player will get the whole pile that was built
			if (player1.getSize() != 0 && player2.getSize() != 0)
			{	
				if (player1.getHeadData().getCardValue() > player2.getHeadData().getCardValue())
				{
					player1.addAtBack(pileOfCards);
					player1Scored(player1, player2);
				}
				else 
				{
					player2.addAtBack(pileOfCards);
					player2Scored(player1, player2);

				}
			}
			else 
				pileOfCards = null;
		}
	}
	
	
	// Card re-arrangements if player 1 scored
	public static void player1Scored(MyList<Card> player1, MyList<Card> player2)
	{
		player1.addAtBack(player1.getHeadData());
		player1.addAtBack(player2.getHeadData());
		player1.removeFirst();
		player2.removeFirst();
	}
	
	
	// Card re-arrangements if player 2 scored
	public static void player2Scored(MyList<Card> player1, MyList<Card> player2)
	{
		player2.addAtBack(player1.getHeadData());
		player2.addAtBack(player2.getHeadData());
		player1.removeFirst();
		player2.removeFirst();
	}
	
	
	// Card re-arrangements to build the pile when the round is draw
	public static void buildPile(MyList<Card> pileOfCards, MyList<Card> player1, MyList<Card> player2)
	{
		pileOfCards.addAtBack(player1.getHeadData());
		pileOfCards.addAtBack(player2.getHeadData());
		player1.removeFirst();
		player2.removeFirst();
	}
	
	
	// A method to get the result of the simulation
	public static String getResult(MyList<Card> player1, MyList<Card> player2, int rounds)
	{
		if (rounds >= 10000 || player1.getSize() == 0 && player2.getSize() == 0)
			return "Draw after " + Integer.toString(rounds) + " rounds";
		else if (player1.getSize() != 0)
			return "1 wins after " + Integer.toString(rounds) + " rounds";
		else
			return "2 wins after " + Integer.toString(rounds) + " rounds";
	}
		
}


// Custom Singly Linked List that is used for holding the cards
class MyList<T> 
{
	// Node Class
	private static class Node<T>
	{
		T data;
		Node<T> nextNode;
	
		public Node(T inputObject)
		{
			this(inputObject, null);
		}
		
		public Node(T inputObject, Node<T> pointToNode)
		{
			data = inputObject;
			nextNode = pointToNode;
		}
		
		protected T getData() {return data;}
		protected Node<T> getNextNode() {return nextNode;}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	
	// Constructor
	public MyList()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	// A method that returns the size of the list
	public int getSize()
	{
		return this.size;
	}
	
	
	// A method that tells if the list is empty or not
	public boolean isEmpty()
	{
		return (this.size == 0);
	}
	
	
	// A method that returns the data of the first element of the list
	public T getHeadData()
	{
		if (isEmpty()) return null;
		return this.head.getData();
	}
	
	
	// A method to add an element up front of the list
	public void addAtFront(T item)
	{
		if ( isEmpty() )
		{
			this.head = new Node<T>(item);
			this.tail = this.head;
			this.size++;
		}
		else
		{
			this.head = new Node<T>(item, head);
			this.size++;
		}
	}
	
	
	// A method to add an element at the back of the list
	public void addAtBack(T item)
	{
		if ( isEmpty() )
		{
			this.head = new Node<T>(item);
			this.tail = this.head;
			this.size++;
		}
		else
		{
			Node<T> newTailNode = new Node<T>(item, null);
			this.tail.nextNode = newTailNode;
			this.tail = newTailNode;
			this.size++;
		}
	}
	
	
	// A method to merge a given list at the back of the current list
	public void addAtBack(MyList<T> addedList)
	{
		this.tail.nextNode = addedList.head;
		this.tail = addedList.tail;
		this.size += addedList.size;
	}
	
	
	// A method to remove the first element
	public T removeFirst()
	{
		if ( isEmpty() ) 
			return null;
		
		Node<T> removedElement = this.head;
		T removedData = removedElement.getData();
		
		this.head = head.getNextNode();
		removedElement = null;
		
		if (this.head == null)
			this.tail = null;
		
		this.size--;
		
		return removedData;
	}
}


// An enumerated class Card, which will have all the card names and an added value to them
enum Card
{
	TWO("2",2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 11),
	QUEEN("Q", 12),
	KING("K", 13),
	ACE("A", 14);
	
	private final String cardName;
	private final int cardValue;
	
	Card(String name, int value)
	{
		this.cardName = name;
		this.cardValue = value;
	}
	
	public String getCardName() 
	{
		return this.cardName;
	}
	
	public int getCardValue()
	{
		return this.cardValue;
	}
}



