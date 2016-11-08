import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Game {
	public static ArrayList<Agent> players = new ArrayList<Agent>();
	public static Deck deck;
	public static Deck discard = new Deck();
	public static TreeMap<Integer, HashMap<Agent, Integer>> scoreboard = new TreeMap<Integer, HashMap<Agent, Integer>>();
	public static HashMap<Agent, Integer> roundscores;
	public static int turn = 0;
	public static PlayerActions pa = new PlayerActions();
	public static BlackJackUI ui = new BlackJackUI(players);
	
	public static void setPlayers(Agent[] players){Game.players = new ArrayList<Agent>(); for(Agent player : players) Game.players.add(player); ui = new BlackJackUI(Game.players);}
	public static void addPlayer(Agent player){Game.players.add(player); ui.generate();}
	
	public static void setDeck(Deck deck){Game.deck = deck;}
	public static void addDeck(Deck deck){Game.deck = Deck.shuffleTogether(Game.deck, deck);}
	
	
	public static void run()
	{
		turn++;
		roundscores = new HashMap<Agent, Integer>();
		
		deal();
		
		ui.generate();
		
		for(Agent player : players)
		{
			if(player instanceof Player) {pa.setPlayer((Player) player); ui.setPlayer((Player)player, pa);}
			while(player.getMove()) hit(player);
			roundscores.put(player, player.getValue());
			System.out.println(player.getValue());
			ui.generate();
		}
		
		discardHands();
		
		scoreboard.put(turn, roundscores);
	}
	
	public static void shuffle()
	{
		deck.shuffle();
	}
	
	public static boolean hit(Agent player){
		return player.hit(deck.draw());
	}
	
	public static void deal()
	{
		for(Agent player : players){
			Hand hand = new Hand(deck.draw(), deck.draw());
			player.deal(hand);
		}
	}
	
	public static void discardHands()
	{
		for(Agent player : players)
		{
			discard.add(player.discardHand());
		}
	}
}
