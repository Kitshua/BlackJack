import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;

public class Player extends Agent{
	public short action = -1;
	
	public Player(String name){
		super(name);
	}
	
	@Override
	public boolean getMove() {
		System.out.println(this.getName() + ": Hit or Stand?");
		String in = new Scanner(System.in).nextLine();
		return in.equalsIgnoreCase("hit");
	}

}
