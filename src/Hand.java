import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cards = new ArrayList<Card>();
	int numAces;
	
	public Hand(){}
	public Hand(Card card1, Card card2)
	{
		this.add(new Card[]{card1, card2});
	}
	
	public int getValueWithoutAces() {
		int value = 0;
		for(Card card : cards){
			value += card.getValue();
		}
		return value;
	}
	
	public int getValue(){
		int value = getValueWithoutAces();
		if(value <= 11 && numAces > 0) return value + 10;
		return value;
	}
	
	public int getValueWithAces(){
		return getValueWithoutAces() + numAces * 10;
	}
	
	public boolean bustCheck(){
		return getValueWithoutAces() > 21;
	}
	
	public void add(Card card){
		cards.add(card);
		if(card.isAce()) numAces++;
	}
	
	public void add(Card[] cards){for(Card card : cards) this.add(card);}
}
