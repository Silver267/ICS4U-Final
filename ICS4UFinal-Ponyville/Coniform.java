import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coniform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coniform extends BattleScreen
{
    public Coniform(){
        GreenfootImage gf = new GreenfootImage("nextButton.png");
        //gf.scale(100, 50);
        setImage(gf);
    }
    /**
     * Act - do whatever the Coniform wants to do. This method is called whenever
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
