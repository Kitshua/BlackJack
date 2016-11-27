import java.awt.BorderLayout;

import javax.swing.JFrame;

public class test extends JFrame{
	public static void main(String [] args){
		Deck deck = Deck.generateStandardDeck();
		Hand hand = new Hand(deck.draw(), deck.draw());
		
		HandUI handui = new HandUI(hand);
		test t = new test();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.getContentPane().add(handui, BorderLayout.CENTER);
		t.pack();
		t.setVisible(true);
		
	}
}
