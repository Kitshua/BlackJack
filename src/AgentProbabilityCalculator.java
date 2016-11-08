
public class AgentProbabilityCalculator extends Agent{

	@Override
	public boolean getMove() {
		Deck deck = Deck.generateStandardDeck();
		deck.removeAll(this.getHand());
		deck.removeAll(Game.discard);
		return false;
	}
	
}
