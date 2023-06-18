import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy class
 * 
 * @author George Liu
 * @version 1.0
 */
public class Enemy extends SuperSmoothMover
{
    protected int hp;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void damage(int x){
        hp -= x;
    }
    
}
