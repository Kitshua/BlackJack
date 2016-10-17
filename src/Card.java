
public class Card 
{	
	private String rank, suit;
	private int value;
	
//	private String[] suitNames = new String[]{"H","C","S","D"};
//	private String[] rankNums = new String[]{"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};
//	private int[] points = new int[]{1,2,3,4,5,6,7,8,9,10,10,10,10};
	
//	public Card()
//	{
//		suit = 0;
//		rank = 0;
//	}
	
//	public Card(String suit, String rank)
//	{
//		this.suit = suit;
//		this.rank = rank;	
//	}
	
	public Card(String suit, String rank, int value)
	{
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}
	
	public void setRank(String newRank)
	{
		rank = newRank;
	}
	
	public void setSuit(String newSuit)
	{
		suit = newSuit;
	}
	
	public String getRank()
	{
		return rank;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public boolean isAce(){return rank.equals("A");}
	public static boolean isAce(Card card){return card.rank.equals("A");}
	
	public String toString()
	{
		return "Rank: " + rank + "\nSuit: " + suit + "\nValue:" + value;
	}
}
