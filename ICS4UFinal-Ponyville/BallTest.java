import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PinkiePie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BallTest extends Enemy{
    /**
     * Act - do whatever the PinkiePie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, meth, timer, cnt;
    private double prevAng;
    private boolean rev;
    private SideWorld sw;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public BallTest(int meth){
        x = 0; this.meth = meth;
        cnt = 0; prevAng = 0;
        gf = new GreenfootImage("MainPony/FS-1.png");
        gf.scale(150, 150); rev = false;
        setImage(gf);
    }
    
    private double phase1Method1(double prev){
        x++;
        if(this.getY()>675/8){
            setLocation(this.getX(), this.getY()-2);
            return 0;
        }else{
            if(prev<-7){//swing the "lines" of bullets, reverse swing if necessary.
                prev = -7;
                rev = true;
            }else if(prev>7){
                prev = 7;
                rev = false;
            }
            prev += (rev ? 1 : -1);
            int xpos = 600, ypos = this.getY();
            for(int i=0; i<3; i++){//generating the 4 "lines" of bullets on the side
                int theta = (i-1)*80;
                getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90+prev)+theta, 8, 17, xpos-xpos/3, ypos-50, false), xpos-xpos/3, ypos-50);
                getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90-prev)+theta, 8, 17, xpos+xpos/3, ypos-50, false), xpos+xpos/3, ypos-50);
                getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90+prev)+theta, 8, 17, xpos-3*(xpos/4), ypos+150, false), xpos-3*(xpos/4), ypos+150);
                getWorld().addObject(new Bullet_Undirected(0, 11, (int)(90-prev)+theta, 8, 17, xpos+3*(xpos/4), ypos+150, false), xpos+3*(xpos/4), ypos+150);
            }
            if(prev%7==0 && prev!=0){//The other bullets
                int tmp = (int)(Math.random()*18);
                if(timer<4500){//The two "rings" of bullets
                    for(int i=0; i<20; i++){
                        getWorld().addObject(new Bullet_Undirected(2, (2.13+x/867.0), i*18+tmp, 8, 17, xpos-3*(xpos/4), ypos+150, false), xpos-3*(xpos/4), ypos+150);
                        getWorld().addObject(new Bullet_Undirected(2, (2.13+x/867.0), i*18-tmp, 8, 17, xpos+3*(xpos/4), ypos+150, false), xpos+3*(xpos/4), ypos+150);
                    }
                }
                if(prev>0){//The bullets which aims to player
                    int dx = ((SideWorld)getWorld()).getHitBox().getX()-getX();
                    int dy = ((SideWorld)getWorld()).getHitBox().getY()-getY();
                    getWorld().addObject(new Bullet_Undirected(0, 5, (int)(Math.toDegrees(Math.atan2(dy, dx))), 1, 18, getX(), getY(), false), getX(), getY());
                }
            }
            return prev;
        }

    }
    
    private void phase1ATK(){
        if(cnt==0){
            cnt = 2;
            prevAng = phase1Method1(prevAng);
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
        }
        if(timer==0){
            sw.changeTalk(true);
            ((SideWorld)getWorld()).remAllBullets();
            
            getWorld().removeObject(this);
        }
            
    }
}
