package lynbrook.sail.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    public GamePanel()
    {
        setPreferredSize( new Dimension(1024, 1024) );
        setFocusable( true );
        requestFocus();
    }
}
