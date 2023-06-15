import greenfoot.*;
/**
 * Undirected bullet (goes in a straight line and will not automatically aim to target)
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class bullet_turn extends SuperSmoothMover{
    private int pict, power, size, facing;
    private double speed;
    private SideWorld sw;
    private boolean rev, revdir, flip;
    
    public bullet_turn(int facing, int x, int y, boolean flip){
        facing += flip?(20):(-20); this.flip = flip;
        this.facing = facing;
        setRotation(facing);
        setImage(temp());
        setLocation(x, y);
        speed = -10; rev = false; revdir = false;
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
    }

    public void act(){
        if(speed>=0)
            move(speed/10);
        else if(!rev)
            facing += revdir?(flip?(4):(-4)):(flip?(-4):(4));
        setRotation(facing);
        if(rev)
            speed--;
        else
            speed++;
        if(speed==40)
            rev = true;
        if(speed==-10){
            rev = false;
            revdir = !revdir;
        }
            
        if(this.isTouching(HitBox.class)){
            getWorld().removeObject(this);
            Statics.setHP(Statics.getHP()-1);
            //TODO: If HP <=0, send to start of the world.
            return;
        }
        if(isEdge())//If touching edge or touching information panel, remove itself.
            getWorld().removeObject(this);
    }
    
    private boolean isEdge(){
        int x = getX(), y = getY();
        if(x<-100 || y<-100)
            return true;
        if(x>1700 || y>775)
            return true;
        return false;
    }

    private GreenfootImage temp(){
        GreenfootImage img = new GreenfootImage("candy_icon.png");
        img.scale(60, 45);
        img.rotate(90);
        return img;
    }
}
