package lynbrook.sail.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        long elapsed;
        long wait;

        while ( mThreadRunning )
        {

            start = System.nanoTime();
            update();
            elapsed = System.nanoTime() - start;

            wait = Constants.TARGET_TIME - elapsed / 1000000;
            if ( wait < 0 )
                wait = Constants.TARGET_TIME;

            try
            {
                Thread.sleep( wait );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }

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
