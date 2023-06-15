import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LightBall here.
 * 
 * @author George Lu
 * @version (a version number or a date)
 */
public class LightBall extends StraightDart
{
    private GreenfootImage lightBall;
    private Color color;
    private SideWorld sw;
    private boolean side, gone;//This boolean will determine if this lightBall is from enemy, true is from enemy, gone will determine if the pbject should be transparent
    public LightBall(boolean side, int direction){
        lightBall = new GreenfootImage("RedBall.png");
        lightBall.scale(15,15);
        setImage(lightBall);
        this.side = side;
        setRotation(direction);
        
    }
    /**
     * Act - do whatever the LightBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(2.8);
        
        if(!gone){
            damage();
        }
        if(gone){
            lightBall.setTransparency(0);
            setImage(lightBall);
            
        }
        if(getX() > 1250 || getX() < -50 || getY() > 700 || getY() < -25){
            getWorld().removeObject(this);
        }
        
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        
    }
    
    public boolean getSide(){
        return side;
    }
    
    public void damage(){
        if(side){
            if(isTouching(HitBox.class)){
                Statics.setHP(Statics.getHP()-1);
                gone = true;
                
            }
            
        }else{
            if(isTouching(Boss.class)){
                sw.getBoss().changeHp(8);
                gone = true;
            }
        }
        
    }
}
