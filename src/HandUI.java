import java.io.IOException;

import javax.swing.JPanel;

public class HandUI extends JPanel{
	private static final long serialVersionUID = 4813866662520162557L;
	
	private Hand hand;
	CardImage[] cards;
	
	public HandUI(Hand hand){
		this.hand = hand;
		update();
	}
	
	private void render(){
		for(int i = 0; i < cards.length; i++)
			try {
				cards[i] = new CardImage(hand.cards.get(i));
				this.add(cards[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void update(){
		if(cards != null) for(CardImage card : cards){
			this.remove(card);
		}
		cards = new CardImage[hand.cards.size()];
		render();
	}
	
	
}
