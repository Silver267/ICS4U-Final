import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PinkiePie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TwilightSparkle extends Enemy{
    /**
     * Act - do whatever the PinkiePie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, meth, timer, prevAng, cnt;
    private double ang; 
    private SideWorld sw;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public TwilightSparkle(int meth, int duration){
        x = 0; this.meth = meth;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/TS-1.png");
        gf.scale(150, 150);
        setImage(gf);
    }
    
    private void phase1ATK(){
        if(cnt==0){
            cnt = 600;
            getWorld().addObject(new Generate(300), ((SideWorld)getWorld()).getHitBox().getX(), ((SideWorld)getWorld()).getHitBox().getY());
        }else{
            cnt--;
        }
    }
    
    private void phase2ATK(){
        if(cnt==0){
            cnt = 60;
            getWorld().addObject(new Generate2(120), ((SideWorld)getWorld()).getHitBox().getX()-50, ((SideWorld)getWorld()).getHitBox().getY());
            getWorld().addObject(new Generate2(120), ((SideWorld)getWorld()).getHitBox().getX()+50, ((SideWorld)getWorld()).getHitBox().getY());
        }else{
            cnt--;
        }
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
    }
    
    public void act(){
        timer--;
        switch(meth){
            case 1:
                phase1ATK();
                break;
            case 2:
                phase2ATK();
                break;
        }
        if(timer==0){
            sw.changeTalk(true);
            ((SideWorld)getWorld()).remAllBullets();
            
            getWorld().removeObject(this);
        }
            
    }
}
