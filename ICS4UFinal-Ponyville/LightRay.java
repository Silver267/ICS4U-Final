import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LightRay here.
 * 
 * @author George Lu
 * @version (a version number or a date)
 */
public class LightRay extends Weapon
{
    private GreenfootImage lightRay, base;
    private boolean start, damage;//damage will tell if the lightray will start damage
    private int direction, width, warnTime, duration;
    private int damageTime;//This will check the gap it damage the hitbox
    public LightRay(int direction, int width, int warnTime, int duration){
        base = new GreenfootImage("laser.png");
        for(int x=0; x<base.getWidth(); x++){
            for(int y=0; y<base.getHeight()/2; y++){
                Color cc = base.getColorAt(x, y);
                base.setColorAt(x, y, new Color(cc.getRed(), cc.getGreen(), cc.getBlue(), 0));
            }
        }
        lightRay = new GreenfootImage(base);
        setRotation(direction);
        this.width = width;
        this.warnTime = warnTime;
        damageTime = 15;
        this.duration = duration;
        
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
            lightRay = new GreenfootImage(base);
            lightRay.scale(width, 2000);
            setImage(lightRay);
            damage = true;
        }
        if(damage){
            damageTime--;
            damageThem();
        }
        remove();
    }
    
    public void warnIt(){
        if(start && warnTime > 0){
            lightRay = new GreenfootImage(base);
            lightRay.scale(5, 2000);
            setImage(lightRay);
        }
    }
    //if have time, create an animation to make a smooth transition
    
    public void damageThem(){
        if(damageTime%5 == 0){
            if(isTouching(HitBox.class)){
                Statics.setHP(Statics.getHP()-1);
            }
        }
    }
    
    public void remove(){
        if(warnTime == -1*duration){
            getWorld().removeObject(this);
        }
    }
}
