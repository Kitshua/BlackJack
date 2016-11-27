
/**
 * 
 * Always hits unless they have 21
 *
 */
public class AgentAlwaysHit extends Agent{

	@Override
	public boolean getMove() {
		return this.getValue() != 21;
	}
	
}