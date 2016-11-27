
/**
 * Implements a basic form of Thorpe's Strategy
 *
 */
public class AgentThorpe extends Agent{
	
	
	
	@Override
	public boolean getMove() {
		Hand hand = this.getHand();
		//The dealers face-up card
		Card dealersFU = Game.dealersFU;
		if(hand.numAces == 0){
			if(hand.getValue() > 16) return false;
			if(hand.getValue() < 12) return true; //We hit if the hand is less than 12 (Doubling down isn't supported)
			if(dealersFU.getValue() >= 7 || dealersFU.isAce()) return true;
			if(hand.getValue() == 12 && dealersFU.getValue() <= 3) return true;
			return false;
		}
		if(hand.getValue() < 8) return true;
		if(hand.getValue() == 8 
			&& dealersFU.getValue() != 2
			&& dealersFU.getValue() != 7
			&& dealersFU.getValue() != 8)
			return true;
		return false;
	}

}
