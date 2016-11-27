
public class AgentProbabilityLearner extends AgentProbability{
	
	//private static final float STEP = .05f;
	
	public AgentProbabilityLearner() {
		super(0f);
	}
	
	private static final int TESTS = 10;
	private static final int DEPTH = 10;
	private static final int SAMPLES = 1000;
	
	public void train(){
		float max1 = 1.0f;
		float maxv1 = 0;
		float max2 = 0;
		float maxv2 = 0;
		for(int i = 0; i < DEPTH; i++){
			if(max1 < max2){float temp = max1; max1 = max2; max2 = temp;}
			float step = (max1 - max2) / TESTS;
			this.acceptableProbability = max2;
			for(int j = 0; j < TESTS; j++){
				float value = train(SAMPLES);
				if(value > maxv1){
					max2 = max1;
					maxv2 = maxv1;
					max1 = this.acceptableProbability;
					maxv1 = value;
				} else if (value > maxv2){
					max2 = this.acceptableProbability;
					maxv2 = value;
				}
				this.acceptableProbability += step;
			}
		}
		//this.acceptableProbability = max1;
		this.update(max1);
	}
	
	public float train(int samples){
		for(int i = 0; i < samples; i++) Game.run();
		float stat = Game.wins.get(this) / (float)samples;
		Game.reset();
		return stat;
	}
	
	private void update(float aProb){
		this.acceptableProbability = aProb;
		this.name = this.name.substring(0, this.name.lastIndexOf("(")) + "(" + this.acceptableProbability*100 + ")";
	}
}
