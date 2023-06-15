import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Frame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frame extends Actor{
    /**
     * Act - do whatever the Frame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Frame(){
        GreenfootImage img = new GreenfootImage("DialogueBox.png");
        img.scale(650, 250);
        setImage(img);
    }
    
    public void addedToWorld(World w){
        getWorld().addObject(new Label("Fastest Time: \n"+Statics.timeToString(), 40), getX(), getY());
    }
}
