import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Main file
 * Runs the blackjack program
 * Controls:
 * ps 	- Prints the current Scoreboard
 * pr 	- Prints the current Rounds board
 * s 	- Prints the current Statistics
 * r 	- Resets the scoreboard
 * st 	- Toggles Scoring (Turn off for better performance)
 * ui	- Toggles the UI (Turn off for better performance)
 * Any number - Runs the game for that many rounds
 * Other - Runs the game once
 *
 */
public class BlackJack extends JFrame
{
	private static final long serialVersionUID = 6981591644773193552L;
	public static UI ui = new UI();
	
	public static void main(String[] args) 
	{
		Deck deck = Deck.generateStandardDeck(6); //The number is how many decks you wish to use
		//Hand hand = new Hand(deck.draw(), deck.draw());
		
		//AgentProbabilityLearner learner = new AgentProbabilityLearner();
		
		Game.setDeck(deck);
		Game.addPlayer(new AgentAlwaysPass());
		Game.addPlayer(new AgentAlwaysHit());
		Game.addPlayer(new AgentThorpe());
		//Game.addPlayer(learner);
		Game.addPlayer(new AgentValueCheck(12));
		Game.addPlayer(new AgentValueCheck(13));
		Game.addPlayer(new AgentValueCheck(14));
		Game.addPlayer(new AgentValueCheck(15));
		Game.addPlayer(new AgentValueCheck(16));
		Game.addPlayer(new AgentValueCheck(17));
		Game.addPlayer(new AgentValueCheck(18));
		Game.addPlayer(new AgentValueCheck(19));
		Game.addPlayer(new AgentValueCheck(20));
		
		Game.addPlayer(new AgentStochasticGradient());
		
		
		//learner.train();
		//System.out.println("Training Complete!");
		//HandUI handui = new HandUI(hand);
		
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.getContentPane().add(Game.ui, BorderLayout.CENTER);
		ui.pack();
		ui.setBounds(0, 0, 300, 100);
		ui.setVisible(false);
		
		Scanner k = new Scanner(System.in);
		
		while(true) {
			String action = k.nextLine();
			if(action.equals("ps")) Game.printScoreboard(); 			//Print Scoreboard
			else if(action.equals("pr")) Game.printRound(Game.round); 	//Print Round
			else if(action.equals("s")) Game.printStatistics();	//Statistics
			else if(action.equals("r")) Game.resetScoreBoard(); 		//Reset scoreboard
			else if(action.equals("st")) { 								//Scoring Toggle
				Game.score = !Game.score;
				if(Game.score) System.out.println("Scoring on!");
				else System.out.println("Scoring off!");
			}
			else if(action.equals("ui")) { 								//UI toggle
				Game.uiActive = !Game.uiActive;
				ui.setVisible(Game.uiActive);
				if(Game.uiActive) System.out.println("UI on!");
				else System.out.println("UI off!");
			}
			else if(isInt(action)){ 									//Run for Action number of rounds 
				int num = Integer.parseInt(action);						//NOTE: turn off scoring and ui for large numbers!
				for(int i = 0; i < num; i++) Game.run();
			}
			else Game.run(); 											//Otherwise, run once
		}
	}
	
	public static boolean isInt(String s){
		try { Integer.parseInt(s); return true; }
		catch(Exception e){}
		return false;
	}
	
	
}
