package lynbrook.sail.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;


public class GamePanel extends JPanel implements Runnable
{
    private BufferedImage mMemoryImage;

    private Graphics2D mMemoryGraphics;

    private boolean mThreadRunning;

    private static final long serialVersionUID = Constants.SERIAL_VERSION_NUMBER;

    private Thread mGameThread;

    private GameController mController;


    public GamePanel()
    {
        setPreferredSize( new Dimension( Constants.WIDTH, Constants.HEIGHT2 ) );
        setFocusable( true );
        requestFocus();

    }


    /**
     * display the component on the screen
     */
    @Override
    public void addNotify()
    {
        super.addNotify();

        if ( mGameThread == null )
        {
            mThreadRunning = true;
            mMemoryImage = new BufferedImage( Constants.WIDTH, Constants.HEIGHT2, 1 );
            mMemoryGraphics = (Graphics2D)mMemoryImage.getGraphics();
            mController = new GameController();
            addKeyListener( mController );
            addMouseListener( mController );
            mGameThread = new Thread( this );
            mGameThread.start();
        }
    }


    public void run()
    {
        long start;

        while ( mThreadRunning )
        {

            start = System.currentTimeMillis();
            update();
            waitIfNecessary( start );
        }

    }


    private void waitIfNecessary( long start )
    {

        long elapsed = System.currentTimeMillis() - start;

        long wait = Constants.TARGET_TIME - elapsed / 1000;
        if ( wait < 0 )
            wait = Constants.TARGET_TIME;

        try
        {
            Thread.sleep( wait );
        }
        catch ( Exception e )
        {

        }
    }


    private void update()
    {
        mController.update();
        mController.draw( mMemoryGraphics );
        Graphics g2 = getGraphics();
        g2.drawImage( mMemoryImage, 0, 0, Constants.WIDTH, Constants.HEIGHT2, null );
        g2.dispose();
    }

}
