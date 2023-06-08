import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HitBox here.
 * 
 * @author George Lu
 * @version 2023/6/1 
 */
public class HitBox extends SuperSmoothMover
{
    private GreenfootImage heart;
    private int time, damageTime;//time is for the gap between each shoot, damageTime is for the gap between each damage
    private int hp;
    public HitBox(){
        heart = new GreenfootImage("The Heart.png");
        heart.scale(27, 27);
        setImage(heart);
        time = 5;
        damageTime = 5;
        hp = 100;
    }
    /**
     * Act - do whatever the HitBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
        shoot();
        time--;
        damageTime--;
    }
    
    public void move(){
        int x = getX(), y = getY();
        if(Greenfoot.isKeyDown("w")){
            y -= 1.5;
        }
        if(Greenfoot.isKeyDown("a")){
            x -= 1.5;
        }
        if(Greenfoot.isKeyDown("s")){
            y += 1.5;
        }
        if(Greenfoot.isKeyDown("d")){
            x += 1.5;
        }
        setLocation(x,y);
    }
    
    public void shoot(){
        if(Greenfoot.isKeyDown("z") && (time % 5 == 0)){
            getWorld().addObject(new LightBall(true, 270), getX(), getY());
        }
        
    }
    
    public void getHit(int x){
        hp-=x;
        
        
    }
}
