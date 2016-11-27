
public class AgentProbability extends Agent{

	protected float acceptableProbability;
	
	public AgentProbability(float acceptableProbability){
		this.acceptableProbability = acceptableProbability;
		this.name += "(" + acceptableProbability * 100 + "%)";
	}
	
	@Override
	public boolean getMove() {
		Deck deck = Game.getOriginal();
		deck.remove(Game.dealersFU);
		deck.removeAll(this.getHand());
		deck.removeAll(Game.discard);
		float bustChance = Statistic.bustChance(getHand(), deck);
		return acceptableProbability > bustChance;
	}
	
}
