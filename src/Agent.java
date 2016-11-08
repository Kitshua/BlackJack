
public abstract class Agent 
{
	private String name;
	private Hand hand;
	
	public Agent()
	{
		name = "player";
	}
	
	public Agent(String name)
	{
		this.name = name;
	}
	
	public void deal(Hand hand)
	{
		this.hand = hand;
	}
	
	public boolean hit(Card card)
	{
		hand.add(card);
		return hand.checkValue();
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return hand.getValue();
	}
	
	public Hand discardHand()
	{
		Hand out = hand;
		hand = null;
		return out;
	}
	
	public Hand getHand(){return hand;}
	
	public abstract boolean getMove();
	
}
