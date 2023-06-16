import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemy
{
    /**
     * Act - do whatever the PinkiePie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, cnt;
    private double prevAng;
    private boolean rev;
    private SideWorld sw;
    private boolean addSidePlane;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public Boss(){
        x = 0; hp = 3000;
        cnt = 0; prevAng = 0;
        gf = new GreenfootImage("blackball.png");
        gf.scale(150, 150); rev = false;
        setImage(gf);
    }
    
    public int getHp(){
        return hp;
    }
    
    public void changeHp(int x){
        hp -= x;
    }
    
    private double phase1Method1(double prev){
        x++;
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
                    for(int i=0; i<20; i++){
                        getWorld().addObject(new Bullet_Undirected(0, (2.13+x/867.0), i*18+tmp, 8, 17, xpos-3*(xpos/4), ypos+150, false), xpos-3*(xpos/4), ypos+150);
                        getWorld().addObject(new Bullet_Undirected(0, (2.13+x/867.0), i*18-tmp, 8, 17, xpos+3*(xpos/4), ypos+150, false), xpos+3*(xpos/4), ypos+150);
                    }
                if(prev>0){//The bullets which aims to player
                    int dx = ((SideWorld)getWorld()).getHitBox().getX()-getX();
                    int dy = ((SideWorld)getWorld()).getHitBox().getY()-getY();
                    getWorld().addObject(new Bullet_Undirected(0, 5, (int)(Math.toDegrees(Math.atan2(dy, dx))), 1, 18, getX(), getY(), false), getX(), getY());
                }
            }
            return prev;
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
    
    private void phase2ATK(){
        if(!addSidePlane){
            sw.addObject(new SidePlane(), getX() - 200, getY());
            sw.addObject(new SidePlane(), getX() + 200, getY());
            addSidePlane = true;
        }
    }
    
    
    public void act(){
        if(hp>1500)
            phase1ATK();
        else
            phase2ATK();
    }
    
    
}
