import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Essentially something that displays left and right keys on starting world.
 * 
 * @author various
 * @version 1.0
 */
public class PageIcon extends Actor
{
    GreenfootImage img;
    public PageIcon() {
        GreenfootImage img = new GreenfootImage("PageIcon.png");
        img.scale(170, 100);
        setImage(img);
    }
    
    /**
     * Act - do whatever the PageIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
