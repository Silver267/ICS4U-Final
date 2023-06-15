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
    private GreenfootImage image;
    public BattleScreen(){
        image = new GreenfootImage("DialogueBox.png");
        image.scale(700,460);
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
