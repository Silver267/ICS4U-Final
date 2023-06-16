import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleWorld here.
 * 
 * @author Yawen Zhang
 * @version 0.1
 */
public class EndWorld extends World
{
    private boolean end;
    Label instruction;
    /**
     * Constructor for objects of class BattleWorld.
     * 
     */
    public EndWorld(boolean result)
    {    
        // Create a new world with 1200x675 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        Statics.saveTime();
        GreenfootImage img = new GreenfootImage(result?"EndWorldGood.jpg":"EndWorldBad.jpg");
        img.scale(1200, 675);
        setBackground(img);
    }
    
    public void setEnd(){
        end = true;
    }
}
