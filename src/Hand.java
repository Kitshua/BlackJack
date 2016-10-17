import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cards = new ArrayList<Card>();
	int numAces;
	
	public Hand(){}
	public Hand(Card card1, Card card2)
	{
		this.add(new Card[]{card1, card2});
	}
	
	public int getValue(){
		int value = 0;
		for(Card card : cards){
			value += card.getValue();
		}
		value += numAces * 10;
		return value;
	}
	
	public boolean checkValue(){
		int value = getValue();
		while(value > 21 && numAces > 0){
			numAces--;
			value -= 10;
		}
		return value > 21;
	}
	
	public void add(Card card){
		cards.add(card);
		if(card.isAce()) numAces++;
	}
	
	public void add(Card[] cards){for(Card card : cards) this.add(card);}
}
