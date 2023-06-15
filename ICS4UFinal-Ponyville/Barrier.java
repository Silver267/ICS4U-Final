import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Barrier that blocks both player and chaser.
 * This is literally just a picture.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Barrier extends Actor{
    
    private GreenfootImage img;
    
    public void addedToWorld(World w){
        decideLook();
        setImage(img);
    }
    
    /**
     * Decides the look of barrier according to current level.
     */
    private void decideLook(){
        img = new GreenfootImage("Barriers/"+Statics.getLevel()+".png");
        img.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
    }
}
