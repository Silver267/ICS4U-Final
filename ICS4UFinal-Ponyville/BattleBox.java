import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class battleBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class battleBox extends Actor{
    public battleBox(){
        GreenfootImage img = new GreenfootImage(650, 410);
        img.drawRect(1, 1, 648, 408);
        setImage(img);
    }
}
