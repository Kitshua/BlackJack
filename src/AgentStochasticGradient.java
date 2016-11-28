import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AgentStochasticGradient extends Agent {

	float weights[] = {1,1,1};
	
	public AgentStochasticGradient(){
		super();
		//Train on our Thorpe test data
		train("./res/testdata");
	}
	
	@Override
	public boolean getMove() {
		Hand hand = this.getHand();
		return predict(hand.numAces > 0, hand.getValueWithoutAces(), Game.dealersFU.getValue()) > 0;
	}	
	
	public float predict(Datum datum){
		return predict(datum.hasAce, datum.handValue, datum.dealersFU);
	}
	public float predict(boolean ace, int hand, int dealer){
		return predict(ace ? 1 : 0, hand, dealer);
	}
	public float predict(int ace, int hand, int dealer){
		return weights[0] * ace + weights[1] * hand + weights[2] * dealer > 0 ? 1 : -1;
	}
	
	private static final float STEP_SIZE = 1.0f;
	private static final float STEP_SIZE_REDUCTION = 0.5f;
	private static final int ROUNDS = 10000;
	private static final float REGULARIZATION = 0.1f;
	
	public void train(String filename){train(importData(filename));}
	public void train(ArrayList<Datum> data){
		System.out.println("Training started!");
		
		for(int i = 0; i < ROUNDS; i++){
			//Shuffles 
			Collections.shuffle(data);
			int updates = 0;
			
			for(Datum datum : data){
				updates++;
				
				float[] c = new float[]{weights[0], weights[1], weights[2]};
				
				float regularization = REGULARIZATION / data.size();
				if(regularization != 0){
					c[0] = c[0] * regularization;
					c[1] = c[1] * regularization;
					c[2] = c[2] * regularization;
				}
				
				float step = (float) (STEP_SIZE / (Math.pow(updates, STEP_SIZE_REDUCTION)));
				
				//Calculated the loss gradient
				float[] gradient = squaredLossGradient(datum, weights);
				//float[] gradient = logisticLossGradient(datum, weights);
				//Updates the weights based on the gradient loss function and regularization from earlier
				weights[0] -= (gradient[0] + c[0]) * step;
				weights[1] -= (gradient[1] + c[1]) * step;
				weights[2] -= (gradient[2] + c[2]) * step;
			}
		}
		
		System.out.println("Training Complete!");
	}
	
	public static float squaredLoss(Datum datum, float[] weights){
		float value = dot(datum, weights) - (datum.isHit ? 1 : -1);
		return value * value;
	}
	
	public static float[] squaredLossGradient(Datum datum, float[] weights){
		float value = dot(datum, weights) - (datum.isHit ? 1 : -1);
		value /= 2;
		return new float[]{(	datum.hasAce ? 1 : 0) * value,
							datum.handValue * value,
							datum.dealersFU * value
		};
	}
	
	public static float[] logisticLossGradient(Datum datum, float[] weights){
		float y = (datum.isHit ? 1 : -1);
		float epower = (float) Math.pow(Math.E, -1 * dot(datum, weights) * y);
		float value = -y * epower / (1 + epower);
		return new float[]{(	datum.hasAce ? 1 : 0) * value,
							datum.handValue * value,
							datum.dealersFU * value
		};
	}
	
	public static float dot(Datum datum, float[] weights){
		return  (	datum.hasAce ? 1 : 0) * weights[0]
				+	datum.handValue * weights[1]
				+	datum.dealersFU * weights[2];
	}
	
	public static ArrayList<Datum> importData(String filename){
		ArrayList<Datum> data = new ArrayList<Datum>();
		try {
			Scanner k = new Scanner(new File(filename));
			while(k.hasNextLine()){
				String[] datum = k.nextLine().split(" ");
				data.add(new Datum(	Boolean.parseBoolean(datum[0]),
									Integer.parseInt(datum[1]),
									Integer.parseInt(datum[2]),
									datum[3].charAt(0)));
			}
			k.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return data;
	}

}

class Datum {
	boolean hasAce;
	int handValue;
	int dealersFU;
	boolean isHit;
	
	public Datum(boolean ace, int hand, int dealer, char choice){
		this(ace, hand, dealer, choice == 'H');
	}
	public Datum(boolean ace, int hand, int dealer, boolean hit){
		hasAce = ace;
		handValue = hand;
		dealersFU = dealer;
		isHit = hit;
	}
}