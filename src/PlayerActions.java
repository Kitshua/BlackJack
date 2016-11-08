import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayerActions extends JPanel implements ActionListener{
	private static final long serialVersionUID = 5745695776525389289L;
	Player p;
	
	public PlayerActions(){
		JButton Hit = new JButton("Hit");
	    Hit.setVerticalTextPosition(AbstractButton.CENTER);
	    Hit.setHorizontalTextPosition(AbstractButton.CENTER);
	    Hit.setMnemonic(KeyEvent.VK_Z);
	    Hit.addActionListener(this);
	    
		
		JButton Pass = new JButton("Pass");
	    Pass.setVerticalTextPosition(AbstractButton.CENTER);
	    Pass.setHorizontalTextPosition(AbstractButton.CENTER);
	    Pass.setMnemonic(KeyEvent.VK_X);
	    Pass.setActionCommand("Pass");
	    Pass.addActionListener(this);
	    
	    this.add(Hit);
	    this.add(Pass);
	}
	
	public void setPlayer(Player p){this.p = p; enable();}
	public void removePlayer(){this.p = null; disable();}
	public void disable(){this.setEnabled(false);}
	public void enable(){this.setEnabled(true);}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Hit")) p.action = 1;
		else p.action = 0;
		
		removePlayer();
	}

}
