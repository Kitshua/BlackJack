import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Game {
	public static ArrayList<Agent> agents = new ArrayList<Agent>();
	public static Deck deck;
	public static Deck discard = new Deck();
	public static boolean score = true; //We don't want to keep a scoreboard if we're going to be running thousands of games
	
	public static TreeMap<Integer, HashMap<Agent, Integer>> scoreboard = new TreeMap<Integer, HashMap<Agent, Integer>>();
	
	public static HashMap<Agent, Integer> wins = new HashMap<Agent, Integer>();
	public static HashMap<Agent, Integer> ties = new HashMap<Agent, Integer>();
	public static HashMap<Agent, Integer> loss = new HashMap<Agent, Integer>();
	public static HashMap<Agent, Integer> bust = new HashMap<Agent, Integer>();
	
	
	public static HashMap<Agent, Integer> roundscores;
	public static int round = 0;
	public static PlayerActions pa = new PlayerActions();
	public static BlackJackUI ui = new BlackJackUI(agents);
	public static boolean uiActive = true;
	
	public static Hand dealersHand; //The dealers hand
	public static Card dealersFU; //The dealers face-up card
	
	@Deprecated
	public static void setPlayers(Agent[] agents){Game.agents = new ArrayList<Agent>(); for(Agent player : agents) Game.agents.add(player); ui = new BlackJackUI(Game.agents);}
	public static void addPlayer(Agent agent){
		Game.agents.add(agent); 
		ui.generate();
		wins.put(agent, 0);
		ties.put(agent, 0);
		loss.put(agent, 0);
		bust.put(agent, 0);
	}
	
	public static void setDeck(Deck deck){Game.deck = deck;}
	public static void addDeck(Deck deck){Game.deck = Deck.shuffleTogether(Game.deck, deck);}
	
	/**
	 * Runs one full turn
	 */
	public static void run()
	{
		//Round indication, just in case it is needed
		round++;
		//Create a new map to hold this rounds scores
		roundscores = new HashMap<Agent, Integer>();
		
		//Deal the cards
		deal();
		
		//Java UI
		if(uiActive) ui.generate();
		
		//In normal blackjack all of the player take their turns followed by the dealer
		//The players are trying to beat the dealersHand
		for(Agent agent : agents)
		{
			//if(player instanceof Player) {pa.setPlayer((Player) player); ui.setPlayer((Player)player, pa);}
			while(agent.getMove()) if(hit(agent)) break;; //While the player keeps hitting it is there turn (we don't support splits or doubles)
			roundscores.put(agent, agent.getValue()); //Record the round data
			//System.out.println(agent.getValue());
			if(uiActive) ui.generate();
		}
		
		//After all the players have gone, it is the dealers turn
		dealersTurn();
		
		int dealersValue = dealersHand.getValue();
		
		for(Agent agent : agents)
		{
			int agentValue = roundscores.get(agent);
			if(agentValue > 21) bust.put(agent, bust.get(agent) + 1);
			else{
				int result = result(dealersValue, agentValue);
				if(result > 0) wins.put(agent, wins.get(agent) + 1);
				else if(result == 0) ties.put(agent, ties.get(agent) + 1);
				else loss.put(agent, loss.get(agent) + 1);
			}
		}
		
		//Record the dealers score, we don't need to if we aren't recording scores
		if(score) roundscores.put(null, dealersValue);
		//discard hands
		discardHands();
		//Save this rounds scores
		if(score) scoreboard.put(round, roundscores);
	}
	
	/**
	 * Shuffles the deck
	 */
	public static void shuffle()
	{
		deck.shuffle();
	}
	
	/**
	 * Shuffles the discard back into the deck
	 */
	public static void shuffleDiscard(){
		addDeck(discard);
		discard = new Deck();
	}
	
	/**
	 * Draw a card for the agent
	 * @param agent The agent requesting a hit, null for the dealer
	 * @return Whether or not the agent busted
	 */
	public static boolean hit(Agent agent){
		if(deck.size() == 0) shuffleDiscard();
		if(agent == null) {
			dealersHand.add(deck.draw());
			return dealersHand.bustCheck();
		}
		return agent.hit(deck.draw());
	}
	
	/**
	 * Deal cards to every player
	 */
	public static void deal()
	{
		//If there aren't enough cards, shuffle
		if(deck.size() < (agents.size() + 1) * 2) shuffleDiscard();
		for(Agent agent : agents){
			Hand hand = new Hand(deck.draw(), deck.draw());
			agent.deal(hand);
		}
		dealersHand = new Hand(deck.draw(), deck.draw());
		dealersFU = dealersHand.cards.get(0);
	}
	
	/**
	 * The dealer plays by specific rules.
	 * They must hit if the value in their hand is less than 
	 */
	public static void dealersTurn(){
		while(dealersHand.getValue() < 17){
			if(hit(null)) break;
		}
	}
	
	/**
	 * Put everyone's cards into the discard pile
	 */
	public static void discardHands()
	{
		for(Agent agent : agents)
		{
			discard.add(agent.discardHand());
		}
		
		discard.add(dealersHand);
		dealersHand = null;
		dealersFU = null;
	}
	
	public static void resetScoreBoard(){
		round = 0;
		scoreboard = new TreeMap<Integer, HashMap<Agent, Integer>>();
		wins = new HashMap<Agent, Integer>();
		ties = new HashMap<Agent, Integer>();
		loss = new HashMap<Agent, Integer>();
		bust = new HashMap<Agent, Integer>();
	}
	
	public static void printScoreboard(){
		System.out.println("===SCOREBOARD=====================================");
		
		for(int i = 1; i <= round; i++) printRound(i);
		
		System.out.println("==================================================");
	}
	
	public static void printRound(int round){
		System.out.println(roundToString(round));
	}
	
	public static String roundToString(int round){
		if(!scoreboard.containsKey(round)) return "";
		HashMap<Agent, Integer> roundboard = scoreboard.get(round);
		int dealersValue = roundboard.get(null);
		boolean dealerBust = dealersValue > 21;
		String out = "Round " + round + " || " + "Dealer: ";
		if(dealerBust) out += "B";
		else out += dealersValue;
		
		for(Agent agent : roundboard.keySet()){
			if(agent == null) continue;
			out += "\t" + agent.getName() + ": ";
			int agentValue = roundboard.get(agent);
			if(agentValue > 21) out += "\tB\tB";
			else out += "\t" + agentValue + "\t" + resultToString(dealersValue, agentValue);
		}
		
		return out;
	}
	
	public static int result(int dealer, int agent){
		if(agent > 21) return -1;
		if(dealer > 21) return 1;
		return ((Integer)agent).compareTo(dealer);
	}
	
	public static String resultToString(int dealer, int agent){
		int result = result(dealer, agent);
		if(result > 0) return "W";
		if(result == 0) return "T";
		return "L";
	}
	
	public static void printStatistics(){
		System.out.println("===Statistics=====================================");
		
		for(Agent agent : agents) printAgentsStatistics(agent);
		
		System.out.println("==================================================");
		System.out.println("Total Rounds:" + round);
	}
	
	public static void printAgentsStatistics(Agent agent){
		System.out.println(agentStatsToString(agent));
	}
	
	private static final DecimalFormat FLOAT_POINT = new DecimalFormat("0.##");
	
	public static String agentStatsToString(Agent agent){
		String out = agent.getName() + ": ";
		//Flat Numbers
		int w = wins.get(agent);
		int t = ties.get(agent);
		int l = loss.get(agent);
		int b = bust.get(agent);
		
		//faster computations
		double percentageMult = (double) (100.0 / round);
		
		//Percentages
		double wp = w*percentageMult;
		double tp = t*percentageMult;
		double lp = l*percentageMult;
		double bp = b*percentageMult;
		
		out += "\t W: " + w + " - " + FLOAT_POINT.format(wp) + "%";
		out += "\t T: " + t + " - " + FLOAT_POINT.format(tp) + "%";
		out += "\t L: " + l + " - " + FLOAT_POINT.format(lp) + "%";
		out += "\t B: " + b + " - " + FLOAT_POINT.format(bp) + "%";
		
		return out;
	}
}
