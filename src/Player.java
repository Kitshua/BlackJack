
public abstract class Player {
	private String name;
	private Hand hand;
	
	public Player(){name = "player";}
	public Player(String name){this.name = name;}
	
	public void deal(Hand hand){
		this.hand = hand;
	}
	
	public boolean hit(Card card){
		hand.add(card);
		return hand.checkValue();
	}
	
	public int getValue(){return hand.getValue();}
	public Hand discardHand()
	{
		Hand out = hand;
		hand = null;
		return out;
	}
	
	public abstract boolean getMove();
	
}
