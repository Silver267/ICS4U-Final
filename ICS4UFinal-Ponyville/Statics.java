/**
 * A class that stores static variables, could potentially be used for game saves.
 * 
 * @author Xuanxi Jiang
 * @version 0.1
 */
public class Statics{
    //Current level (from 1 to 3), player position x, player position y.
    //Note: ppX and ppY are coordinates of Map, 
    private static int lV, ppX, ppY, MP, HP, orbReim;
    private static boolean active;
    
    /**
     * Set active state to true if player is already inside a world (ending from normal pony battle)
     * 
     * @param in    The new state of active.
     */
    public static void setActive(boolean in){
        active = in;
    }
    
    /**
     * Returns if the player is currently active in world.
     * 
     * @return boolean  Returns true only if player is not in spawn point.
     */
    public static boolean isActive(){
        return active;
    }
    
    /**
     * Sets the number of remaining memory orbs.
     * 
     * @param val   the updated number of memory orbs.
     */
    public static void setOrb(int val){
        orbReim = val;
    }
    
    /**
     * Returns the current number of remaining memory orbs.
     * 
     * @return int  the number of memory orbs remaining.
     */
    public static int getOrb(){
        return orbReim;
    }
    
    /**
     * returns the current HP of player.
     * 
     * @return int  The current HP of player
     */
    public static int getHP(){
        return HP;
    }
    
    /**
     * sets the HP of player to the given value
     * 
     * @param val   The HP value after changing
     */
    public static void setHP(int val){
        HP = val;
    }
    
    /**
     * returns the current MP of player.
     * 
     * @return int  The current MP of player
     */
    public static int getMP(){
        return MP;
    }
    
    /**
     * sets the MP of player to the given value
     * 
     * @param val   The MP value after changing
     */
    public static void setMP(int val){
        MP = val;
    }
    
    /**
     * sets the current level (map) of player. (1, 2, 3)
     * 
     * @param lv    The current level.
     */
    public static void setLevel(int lv){
        lV = lv;
    }
    
    /**
     * returns current level (1, 2, 3);
     * 
     * @return int  The current level
     */
    public static int getLevel(){
        return lV;
    }
    
    /**
     * sets the current coordinate of player
     * 
     * @param coord     The coordinate of player
     */
    public static void setPlayerCoords(int[] coord){
        ppX = coord[0];
        ppY = coord[1];
    }
    
    /**
     * returns player's X and Y coordinate on grid
     * 
     * @return int[2]  returns player's X and Y coordinate on grid, respectively
     */
    public static int[] getPlayerCoords(){
        return new int[]{ppX, ppY};
    }
}
