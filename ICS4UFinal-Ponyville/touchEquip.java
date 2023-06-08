import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class touchEquip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class touchEquip extends Actor{
    private GreenfootImage img;
    private int id;
    
    public touchEquip(int id){
        this.id = id;
    }
    
    public void addedToWorld(World w){
        decideLook();
        setImage(img);
    }
    
    private void decideLook(){
        switch(id){
            case 0:
                img = new GreenfootImage("textures/Portal.png");
                img.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
                break;
            case 1:
                //first pony
            case 2:
                //second pony
            case 3:
                //third pony
        }
    }
    
    private void animate(){
        //animation of pony - ming??
    }
}
