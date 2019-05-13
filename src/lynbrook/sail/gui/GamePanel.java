package lynbrook.sail.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.controller.Keys;
import lynbrook.sail.data.Constants;


public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private BufferedImage image;

    private Graphics2D g;

    private boolean running;

    private final int FPS = 30;

    private final int TARGET_TIME = 1000 / FPS;

    private static final long serialVersionUID = 100L;

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
            addKeyListener( this );
            mGameThread = new Thread( this );
            mGameThread.start();
        }
    }


    public void run()
    {
        init();
        long start;
        long elapsed;
        long wait;

        while ( running )
        {

            start = System.nanoTime();
            update();
            elapsed = System.nanoTime() - start;

            wait = TARGET_TIME - elapsed / 1000000;
            if ( wait < 0 )
                wait = TARGET_TIME;

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


    private void init()
    {
        running = true;
        image = new BufferedImage( Constants.WIDTH, Constants.HEIGHT2, 1 );
        g = (Graphics2D)image.getGraphics();
        mController = new GameController();
    }


    public void keyTyped( KeyEvent key )
    {
    }


    public void keyPressed( KeyEvent key )
    {
        Keys.keySet( key.getKeyCode(), true );
    }


    public void keyReleased( KeyEvent key )
    {
        Keys.keySet( key.getKeyCode(), false );
    }


    private void update()
    {
        mController.update();
        Keys.copy();
        mController.draw( g );
        Graphics g2 = getGraphics();
        g2.drawImage( image, 0, 0, Constants.WIDTH, Constants.HEIGHT2, null );
        g2.dispose();
    }

}
