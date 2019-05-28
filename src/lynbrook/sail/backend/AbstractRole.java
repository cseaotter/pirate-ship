package lynbrook.sail.backend;

/**
 * 
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Elaine Ye
 *  @version May 24, 2019
 *  @author  Period: 3
 *  @author  Assignment: Island Battle
 *
 *  @author  Sources: none
 */
public abstract class AbstractRole implements Role
{
  //common, can be put into abstract class
    /**
     * click role
     */
    private int health = 100;
    private UI ui;
    public void chooseRole() 
    {
        //call UI for displaying screen for role choosing
    }
    
    
    
    //common with pirate class
    public void calcAndUpdateHealth(int x, int y) 
    {
        
        // WHEN YOU KNOW THE COORDINATES OF THE CASTLE AND THE PIRATE)) x,y within boundary?  then subtract health
        
        health = health - 5;
        
        
        
        //evaluate damage
        //call UI to update bloodline
        
        
        
    }
    
   
    public abstract String getRoleName();
    
/*
    //common
    public void calculateBulletLocation() 
    {
        
    }
    //common
    public void shootBullet() 
    {
        
    }
    */
}
