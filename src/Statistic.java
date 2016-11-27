import java.util.Stack;

public class Statistic {
	/**
	 * Calculates the chance that the next card drawn will cause a bust
	 * @param hand The current hand
	 * @param deck The cards that can be drawn
	 * @return A value between 0 and 1
	 */
	public static float bustChance(Hand hand, Deck deck){
		int currentValue = hand.getValue() - hand.numAces*10;
		int bustValue = 22 - currentValue;
		return greaterThanEqualChance(bustValue, deck);
	}
	
	public static float blackjackChance(Hand hand, Deck deck){
		int currentValue = hand.getValue() - hand.numAces*10;
		int blackjackValue = 21 - currentValue;
		return equalChance(blackjackValue, deck);
	}
	
	public static float equalChance(int value, Deck deck){
		Stack<Card> cards = deck.getCards();
		if(cards == null || cards.isEmpty()) return 0;
		int equal = 0;
		for(Card card : cards){
			if(card.getValue() == value) equal++;
		}
		
		return equal/((float)cards.size());
	}
	
	public static float greaterThanChance(int value, Deck deck){
		Stack<Card> cards = deck.getCards();
		if(cards == null || cards.isEmpty()) return 0;
		int above = 0;
		for(Card card : cards){
			if(card.getValue() > value) above++;
		}
		
		return above/((float)cards.size());
		
	}
	
	public static float greaterThanEqualChance(int value, Deck deck){
		return greaterThanChance(value - 1, deck);
	}
	
	public static float lessThanChance(int value, Deck deck){
		return 1 - greaterThanEqualChance(value, deck);
	}
	
	public static float lessThanEqualChance(int value, Deck deck){
		return 1 - greaterThanChance(value, deck);
	}
}
