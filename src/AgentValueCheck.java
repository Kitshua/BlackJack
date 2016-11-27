
public class AgentValueCheck extends Agent{
	int value;
	
	public AgentValueCheck(int value){
		super();
		this.name += "(" + value + ")";
		this.value = value;
	}
	
	@Override
	public boolean getMove() {
		return this.getValue() < value;
	}

}
