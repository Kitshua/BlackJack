import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	private Stack<Card> cards = new Stack<Card>();

	/**
	 * Shallow copy constructor
	 * @param deck The deck to copy
	 */
	public Deck(Deck deck){
		this.cards.addAll(deck.cards);
	}
	
	public Deck() {}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public static void shuffle(Deck deck) {
		shuffle(deck.cards);
	}

	public static void shuffle(List<Card> deck) {
		Collections.shuffle(deck);
	}

	public static Deck shuffleTogether(Deck deck1, Deck deck2) {
		Deck newDeck = addTogether(deck1, deck2);
		shuffle(newDeck);
		return newDeck;
	}
	
	/**
	 * Combines two decks together to form a new deck
	 * @param deck1 The first deck
	 * @param deck2 The second deck
	 * @return The deck made from combining deck1 and deck2
	 */
	public static Deck addTogether(Deck deck1, Deck deck2) {
		Deck newDeck = new Deck();
		newDeck.cards.addAll(deck1.cards);
		newDeck.cards.addAll(deck2.cards);
		return newDeck;
	}

	private static String[] standardSuits = { "H", "C", "S", "D" };
	private static String[] standardRanks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	private static int[] standardValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
	
	/**
	 * Creates a standard 52 (No joker) card deck
	 * @return A standard deck
	 */
	public static Deck generateStandardDeck() {
		return generateDeck(standardSuits, standardRanks, standardValues);
	}
	
	/**
	 * Creates a deck from a number of standard decks
	 * @param number The amount of standard decks
	 * @return The deck
	 */
	public static  Deck generateStandardDeck(int number) {
		Deck deck = new Deck();
		for(int i = 0; i < number; i++){
			deck = addTogether(deck, generateStandardDeck());
		}
		return deck;
	}

	public static Deck generateDeck(String[] suits, String[] ranks, int[] values) {
		if (ranks.length != values.length)
			throw new RuntimeException("List of Ranks and List of Values are not of the same length!");

		Deck deck = new Deck();

		int rankslength = ranks.length;
		for (String suit : suits)
			for (int i = 0; i < rankslength; i++)
				deck.add(suit, ranks[i], values[i]);

		deck.shuffle();
		return deck;
	}

	public void add(Card card) {
		cards.push(card);
	}

	public void add(String suit, String rank, int value) {
		this.add(new Card(suit, rank, value));
	}

	public void add(Hand hand) {
		cards.addAll(hand.cards);
	}
	
	public void remove(Card card){
		for(Card c : cards){
			if(c.equals(card)) {cards.remove(c); break;}
		}
	}
	
	public void removeAll(Collection<Card> c){
		for(Card card : c) this.remove(card);
	}
	
	public void removeAll(Hand h){
		this.removeAll(h.cards);
	}
	
	public void removeAll(Deck d){
		this.removeAll(d.cards);
	}

	public Card draw() {
		return cards.pop();
	}
	
	public Stack<Card> getCards(){
		return cards;
	}
	
	public int size(){
		return cards.size();
	}
}
