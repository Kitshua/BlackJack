
public abstract class Agent 
{
	private static int agentNumber = 0;
	protected String name;
	private Hand hand;
	
	public Agent()
	{
		name = "Agent " + agentNumber + " (" + this.getClass().getName() + ")";
		agentNumber++;
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
		return hand.bustCheck();
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
