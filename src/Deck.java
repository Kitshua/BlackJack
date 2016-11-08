import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	private Stack<Card> cards = new Stack<Card>();

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
		Deck newDeck = new Deck();
		newDeck.cards.addAll(deck1.cards);
		newDeck.cards.addAll(deck2.cards);
		shuffle(newDeck);
		return newDeck;
	}

	private static String[] standardSuits = { "H", "C", "S", "D" };
	private static String[] standardRanks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	private static int[] standardValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

	public static Deck generateStandardDeck() {
		return generateDeck(standardSuits, standardRanks, standardValues);
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
}
