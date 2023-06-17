import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Twilight that releases bullets.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class TwilightSparkle extends Enemy{
    private int x, meth, timer, prevAng, cnt;
    private double ang; 
    private SideWorld sw;
    private static int count;    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public TwilightSparkle(int meth, int duration){
        x = 0; this.meth = meth;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/TS-1.png");
        gf.scale(150, 150);
        setImage(gf);
    }
    
    public static void countplus(){
        count++;
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
            if(count >= 1){
                ((SideWorld)getWorld()).setConversation();
            }
            sw.setContinueChooseLine(true);
            sw.setSpeakFirst();
            sw.setContinueChoose(false);
            ((SideWorld)getWorld()).setContinueChooseLine(true);
            getWorld().removeObject(this);
        }
            
    }
}
