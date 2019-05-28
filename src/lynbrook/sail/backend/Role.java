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
public interface Role
{
   public enum RoleName {
       king,
       pirate
   }
   
   public void calcAndUpdateHealth(int x, int y);
   
   public String getRoleName();
   
   public int getHealth();
   
}
