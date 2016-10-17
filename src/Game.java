import java.util.ArrayList;

public class Game {
	public static ArrayList<Player> players;
	public static Deck deck;
	public static Deck discard = new Deck();
	
	public static void setPlayers(Player[] players){Game.players = new ArrayList<Player>(); for(Player player : players) Game.players.add(player);}
	public static void addPlayer(Player player){Game.players.add(player);}
	
	public static void setDeck(Deck deck){Game.deck = deck;}
	public static void addDeck(Deck deck){Game.deck = Deck.shuffleTogether(Game.deck, deck);}
	
	public static void run()
	{
		deal();
		
		for(Player player : players)
		{
			while(player.getMove());
		}
		
		discardHands();
	}
	
	public static void shuffle()
	{
		deck.shuffle();
	}
	
	public static void deal()
	{
		for(Player player : players){
			Hand hand = new Hand(deck.draw(), deck.draw());
			player.deal(hand);
		}
	}
	
	public static void discardHands()
	{
		for(Player player : players)
		{
			discard.add(player.discardHand());
		}
	}
}
