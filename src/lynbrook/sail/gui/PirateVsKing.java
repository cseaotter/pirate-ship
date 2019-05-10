package lynbrook.sail.gui;

import javax.swing.JFrame;


public class PirateVsKing
{
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
