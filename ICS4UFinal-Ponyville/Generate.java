import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Generate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Generate extends removableBullet{
    
    private int activeCnt, x;
    
    public Generate(int activeCnt){
        this.activeCnt = activeCnt;
        GreenfootImage tmp = new GreenfootImage("generate.png");
        tmp.scale(75, 75); x = 300;
        tmp.setTransparency(127);
        setImage(tmp);
    }
    
    public void act(){
        if(activeCnt>0){
            activeCnt--;
            setLocation(((SideWorld)getWorld()).getHitBox().getX(), ((SideWorld)getWorld()).getHitBox().getY());
        }else{
            if(x==0){
                getWorld().removeObject(this);
                return;
            }
            if(x==300){
                for(int i=0; i<6; i++){
                    getWorld().addObject(new LightRay(i*60, 30, 120, 120), getX(), getY());
                }
            }
            if(x<180 && x%15==0){
                for(int i=0; i<12; i++){
                    getWorld().addObject(new Bullet_Undirected(0, 3, (int)(x+i*30), 1, 15, getX(), getY(), false), getX(), getY());
                }
            }
            x--;
        }
    }
}
