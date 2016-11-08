import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BlackJack extends JFrame
{

	public static void main(String[] args) 
	{
		Deck deck = Deck.generateStandardDeck();
		//Hand hand = new Hand(deck.draw(), deck.draw());
		Game game = new Game();
		
		game.setDeck(deck);
		game.addPlayer(new AgentAlwaysPass());
		game.addPlayer(new AgentAlwaysPass());
		
		//HandUI handui = new HandUI(hand);
		test t = new test();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.getContentPane().add(game.ui, BorderLayout.CENTER);
		t.pack();
		t.setVisible(true);
		
		game.run();
	}

}
