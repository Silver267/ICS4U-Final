import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrier here.
 * 
 * @author Xuanxi Jiang
 * @version (a version number or a date)
 */
public class Barrier extends Actor{
    
    private GreenfootImage img;
    
    public void addedToWorld(World w){
        decideLook();
        setImage(img);
    }
    
    private void decideLook(){
        img = new GreenfootImage("Barriers/"+Statics.getLevel()+".png");
        img.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
    }
}
