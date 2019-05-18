package lynbrook.sail.actor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import lynbrook.sail.gui.IslandMap;

public class Bullet extends Weapon
{
    public Bullet(
        IslandMap tm,
        double pos,
        double pos2,
        double speed,
        double speed2,
        double damage,
        boolean right,
        boolean up )
    {
        // TODO Auto-generated constructor stub
        super( tm, pos, pos2, speed, speed2, damage, right, up );
    }
    private boolean isDirectionUp;
    private float speedX,speedY;
    private int size = 5;

    
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g2d.setColor(Color.orange);
        g2d.fill(new Ellipse2D.Double(xPos,yPos,size,size));                            

    }
    
    public Point2D getCentre() 
    {
        Point2D c=new Point2D.Double(xPos+size/2,yPos+size/2);
        return c;
    }

}

