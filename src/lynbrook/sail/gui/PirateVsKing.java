package lynbrook.sail.gui;

import javax.swing.JFrame;


/**
 * This class runs the whole program
 *
 * @author yinin
 * @version May 31, 2019
 * @author Period: TODO
 * @author Assignment: pirateShip
 *
 * @author Sources: TODO
 */
public class PirateVsKing
{
    /**
     * Main class starts the game by setting the frame
     * 
     * @param args
     *            the arguments
     */
    public static void main( String[] args )
    {
        JFrame jf = new JFrame( "Pirate vs King" );
        jf.add( new GamePanel() );
        jf.setResizable( false );
        jf.pack();
        jf.setLocationRelativeTo( null );
        jf.setVisible( true );
        jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
