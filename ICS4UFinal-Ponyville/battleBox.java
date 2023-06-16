import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The box that displays the boundary of player's movement.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class battleBox extends Actor{
    public battleBox(){
        GreenfootImage img = new GreenfootImage(650, 410);
        img.drawRect(1, 1, 648, 408);
        setImage(img);
    }
}
