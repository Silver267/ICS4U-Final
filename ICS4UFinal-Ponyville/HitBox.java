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
    private SideWorld sw;
    private boolean start;
    private int time, damageTime;//time is for the gap between each shoot, damageTime is for the gap between each damage
    private int hp;
    private boolean final1;
    public HitBox(boolean final1){
        heart = new GreenfootImage("The Heart.png");
        heart.scale(27, 27);
        setImage(heart);
        time = 5;
        damageTime = 5;
        this.final1 = final1;
        Statics.setHP(40);
    }
    /**
     * Act - do whatever the HitBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
        if(final1){
            shoot();
            success();
        }
        
        time--;
        damageTime--;
        dead();
        
        
        
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        start = true;
    }
    
    public void move(){
        if(Greenfoot.isKeyDown("w")){
            setRotation(270);
            move(2);
        }
        if(Greenfoot.isKeyDown("a")){
            setRotation(180);
            move(2);
        }
        if(Greenfoot.isKeyDown("s")){
            setRotation(90);
            move(2);
        }
        if(Greenfoot.isKeyDown("d")){
            setRotation(0);
            move(2);
        }
    }
    
    public void shoot(){
        if(Greenfoot.isKeyDown("z") && (time % 5 == 0)){
            getWorld().addObject(new LightBall(false, 270), getX(), getY());
        }
        
    }
    
    public void dead(){
        if(Statics.getHP() <= 0){
            Statics.setLevel(2);
            Greenfoot.setWorld(new MainWorld());
            
        }
    }
    
    public void success(){
        if(final1 && start && sw.getBoss().getHp() < 0){
            EndWorld ew = new EndWorld();
            ew.setEnd();
            Greenfoot.setWorld(ew);
            
        }
    }
    
}
