
public class Card 
{
	private int rank, suit;
	
	private String[] suitNames = new String[]{"H","C","S","D"};
	private String[] rankNums = new String[]{"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private int[] points = new int[]{1,2,3,4,5,6,7,8,9,10,10,10,10};
	
	public Card()
	{
		suit = 0;
		rank = 0;
	}
	
	public Card(int suitIndex, int rankIndex)
	{
		suit = suitIndex;
		rank = rankIndex;	
	}
	
	public void setRank(int newRank)
	{
		rank = newRank;
	}
	
	public void setSuit(int newSuit)
	{
		suit = newSuit;
	}
	
	public int getRank()
	{
		return rank;
	}
	
	public int getSuit()
	{
		return suit;
	}
	
	public int getPoints()
	{
		return points[rank];
	}
	
	public String toString()
	{
		return "Rank: " + rankNums[rank] + "\nSuit: " + suitNames[suit];
	}
}
