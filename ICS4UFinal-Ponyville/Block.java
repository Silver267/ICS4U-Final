import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author various
 * @version 1.0
 */
public class Block extends Actor
{
    private GreenfootImage rect;
    public Block(int r){
        rect = new GreenfootImage(1500, 10);
        setImage(rect);
        if(r > 0){
            setRotation(r);
        }
    }
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        removeTouching(SuperSmoothMover.class);
    }
}
