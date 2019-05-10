package Actors;
import info.gridworld.actor.Bug;

public class Ship 
{
    private int x;
    private int y;
    
    /**
     * Constructs a ship
     */
    public Ship()
    {
        x= 5;
        y = 5;
    }
    
    /**
     * Return x coordinate
     * @return x row
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Return y coordinate
     * @return y column
     */
    public int getY()
    {
        return y;
    }
    
    public void advance()
    {
        
    }
    
}
