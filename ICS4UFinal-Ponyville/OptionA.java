import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OptionA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OptionA extends BattleScreen
{
    public OptionA(){
        GreenfootImage gf = new GreenfootImage("OptionTest.png");
        gf.scale(100, 50);
        setImage(gf);
    }
    
    /**
     * Act - do whatever the OptionA wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public boolean isClick(){
        return Greenfoot.mouseClicked(this);
    }
}
