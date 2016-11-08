import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Player extends Agent{
	public short action = -1;
	
	public Player(String name){
		super(name);
	}
	
	@Override
	public boolean getMove() {
		while(action == -1){
			try{Thread.sleep(200);
			    } catch(InterruptedException e) {
			    	e.printStackTrace();
			    }
		}
		short out = action;
		action = -1;
		return out == 1;
	}

}
