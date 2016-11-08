import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class BlackJackUI extends JPanel{
	private static final long serialVersionUID = -8845115928471384619L;
	
	HashMap<Agent, HandUI> hands;
	ArrayList<Agent> players;
	Agent currentPlayer = null;
	int num;
	
	public BlackJackUI(ArrayList<Agent> players){
		this.players = players;
		num = players.size();
	}
	
	public void generate(){
		if(hands != null)for(Agent player : hands.keySet()) this.remove(hands.get(player));
		hands = new HashMap<Agent, HandUI>();
		for(Agent player : players){
			hands.put(player, new HandUI(player.getHand())); 
			this.add(hands.get(player));
		}
	}
	
	public void update(Agent player){
		hands.get(player).update();
	}
	
	public void setPlayer(Player p, PlayerActions pa){
		if(currentPlayer != null) hands.get(currentPlayer).remove(pa);
		hands.get(p).add(pa);
		currentPlayer = p;
	}
}
