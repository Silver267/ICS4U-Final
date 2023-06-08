import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleScreen here.
 * 
 * @author George Lu
 * @version 2023/6/1 11:52
 */
public class BattleScreen extends Screen
{
    private GreenfootImage b1, b2, b3;
    public BattleScreen(){
        b1 = new GreenfootImage(5,200);
        b2 = new GreenfootImage(400, 5);
        b3 = new GreenfootImage(5,200);
        b1.drawRect(300, 300, 200, 200);
        setImage(b1);
    }
    
    
    /**
     * Act - do whatever the BattleScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    
    
}
