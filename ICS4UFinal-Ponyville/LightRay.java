import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LightRay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LightRay extends Weapon
{
    private GreenfootImage lightRay, warn;
    private int direction, width, warnTime;
    public LightRay(int direction, int width, int warnTime){
        lightRay = new GreenfootImage(width, 2000);
        this.direction = direction;
        this.width = width;
        this.warnTime = warnTime;
        warn = new GreenfootImage(5, 2000);
        lightRay
    }
    /**
     * Act - do whatever the LightRay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
