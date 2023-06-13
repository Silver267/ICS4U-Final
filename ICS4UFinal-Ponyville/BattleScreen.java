import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleScreen here.
 * 
 * @author George Lu
 * @version 2023/6/1 11:52
 */
public class BattleScreen extends Screen
{
    // Yawen
    GreenfootImage image = new GreenfootImage(700, 400);
    public BattleScreen(){
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
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
