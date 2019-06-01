package lynbrook.sail.gui;

import javax.swing.JFrame;

/**
 * This class runs the whole program
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class PirateVsKing
{
	/**
	 * Main class starts the game by setting the frame
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		JFrame jf = new JFrame("Pirate vs King");
		jf.add(new GamePanel());
		jf.setResizable(false);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
