import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleWorld here.
 * 
 * @author Yawen Zhang
 * @version 0.1
 */
public class EndWorld extends World
{
    /**
     * Constructor for objects of class BattleWorld.
     * 
     */
    public EndWorld(boolean result)
    {    
        // Create a new world with 1200x675 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        
        if (result) {
            setBackground(new GreenfootImage("images/EndWorldGood.jpg"));
        } else {
            setBackground(new GreenfootImage("images/EndWorldBad.jpg"));
        }
    }
    
    public EndWorld()
    {
        super(1200, 675, 1, false);
        
        if (Statics.getLevel() == 3) {
            setBackground(new GreenfootImage("images/9.jpg"));
        }
    }
}
