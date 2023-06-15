import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LightRay here.
 * 
 * @author George Lu
 * @version (a version number or a date)
 */
public class LightRay extends Weapon
{
    private GreenfootImage lightRay;
    private boolean start, damage;//damage will tell if the lightray will start damage
    private int direction, width, warnTime;
    private int damageTime;//This will check the gap it damage the hitbox
    public LightRay(int direction, int width, int warnTime){
        lightRay = new GreenfootImage(width, 2000);
        lightRay.fillRect(0, 0, width, 2000);
        setRotation(direction);
        this.width = width;
        this.warnTime = warnTime;
        damageTime = 15;
        
    }
    
    public void addedToWorld(World w){
        start = true;
    }
    
    /**
     * Act - do whatever the LightRay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        warnIt();
        warnTime--;
        if(warnTime < 0){
            lightRay.scale(width, 2000);
            setImage(lightRay);
            damage = true;
        }
        if(damage){
            damageTime--;
            damageThem();
        }
    }
    
    public void warnIt(){
        if(start && warnTime > 0){
            lightRay.scale(5, 2000);
            setImage(lightRay);
        }
    }
    
    public void damageThem(){
        if(damageTime%15 == 0){
            if(isTouching(HitBox.class)){
                Statics.setHP(Statics.getHP()-1);
            }
        }
    }
}
