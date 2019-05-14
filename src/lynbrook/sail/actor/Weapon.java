
package lynbrook.sail.actor;

import lynbrook.sail.gui.IslandMap;


public class Weapon
{
    double xPos, yPos;//

    double xSpeed, ySpeed;

    double damage;

    boolean right, up;

    boolean alive = true;

    private IslandMap map;


    public Weapon(
        IslandMap tm,
        double pos,
        double pos2,
        double speed,
        double speed2,
        double damage,
        boolean right,
        boolean up )
    {
        map = tm;
        xPos = pos;
        yPos = pos2;
        xSpeed = speed;
        ySpeed = speed2;
        this.damage = damage;
        this.right = right;
        this.up = up;
    }


    /**
     * (non-Javadoc)
     * 
     * @see name.shabda.spacegame.Artifact#collision(name.shabda.spacegame.Artifact)
     */
    /*
     * public boolean collision(Artifact a) { int safe=5; boolean hasHit=false;
     * if(xPos-a.getXPos()<safe&&yPos-a.getYPos()<safe) hasHit=true; return
     * hasHit; }
     */
    /**
     * @return Returns the damage.
     */
    public double getDamage()
    {
        return damage;
    }


    /**
     * @param damage
     *            The damage to set.
     */
    public void setDamage( double damage )
    {
        this.damage = damage;
    }


    /**
     * @return Returns the right.
     */
    public boolean isRight()
    {
        return right;
    }


    /**
     * @param right
     *            The right to set.
     */
    public void setRight( boolean right )
    {
        this.right = right;
    }


    /**
     * @return Returns the up.
     */
    public boolean isUp()
    {
        return up;
    }


    /**
     * @param up
     *            The up to set.
     */
    public void setUp( boolean up )
    {
        this.up = up;
    }


    /**
     * @return Returns the xPos.
     */
    public double getXPos()
    {
        return xPos;
    }


    /**
     * @param pos
     *            The xPos to set.
     */
    public void setXPos( double pos )
    {
        xPos = pos;
    }


    /**
     * @return Returns the xSpeed.
     */
    public double getXSpeed()
    {
        return xSpeed;
    }


    /**
     * @param speed
     *            The xSpeed to set.
     */
    public void setXSpeed( double speed )
    {
        xSpeed = speed;
    }


    /**
     * @return Returns the yPos.
     */
    public double getYPos()
    {
        return yPos;
    }


    /**
     * @param pos
     *            The yPos to set.
     */
    public void setYPos( double pos )
    {
        yPos = pos;
    }


    /**
     * @return Returns the ySpeed.
     */
    public double getYSpeed()
    {
        return ySpeed;
    }


    /**
     * @param speed
     *            The ySpeed to set.
     */
    public void setYSpeed( double speed )
    {
        ySpeed = speed;
    }


    public void move()
    {
        if ( right )
        {
            xPos += xSpeed;
        }
        else if ( !right )
        {
            xPos -= xSpeed;
        }
        if ( up )
        {
            yPos -= ySpeed;
        }
        else if ( !up )
        {
            yPos -= ySpeed;
        }
    }


    public static void main( String[] args )
    {
    }

}
