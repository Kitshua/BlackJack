import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardImage extends JLabel
{
	private static final long serialVersionUID = 915890438145528088L;
	
	private static final String IMAGE_DIR = "./res/Cards/";
	private static final String IMAGE_EXT = ".png";
	
	public CardImage(Card card) throws IOException{this(card.getSuit()+card.getRank());}
	
	public CardImage(String card) throws IOException{
		super(new ImageIcon(ImageIO.read(new File(IMAGE_DIR + card + IMAGE_EXT))));
	}
	
}
