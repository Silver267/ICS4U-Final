import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PinkiePie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fluttershy extends Enemy{
    /**
     * Act - do whatever the PinkiePie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, meth, timer, prevAng, cnt;
    private double ang; 
    private SideWorld sw;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public Fluttershy(int meth, int duration){
        x = 0; this.meth = meth;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/FS-1.png");
        gf.scale(150, 150);
        setImage(gf);
    }
    
    private void phase1Method1(){
        double fx = ((-1/10.0)*x + 28/3.0); //generation (angle) function
        ang = ang+fx;
        int xpos = this.getX(), ypos = this.getY();
        for(int i=0; i<4; i++){//function to generate bullet for first spell card.
            double theta = 180-140/3 + i*90;//angle offset
            //set speed to 3.46 + x/584.0 so that the speed of bullets are increasing.
            getWorld().addObject(new Bullet_Undirected(0, (3.46+x/584.0), (int)(ang+theta), 8, 17, xpos, ypos, false), xpos, ypos);
        }
    }
    
    private void phase2Method1(){
        double fx = x*x*0.7;
        ang = ang+fx;
        for(int i=0; i<3; i++){
            getWorld().addObject(new Bullet_Undirected(0, 1.5, (int)(i*120+ang), 8, 17, getX(), getY(), false), getX(), getY());            
        }
        for(int i=0; i<3; i++){
            getWorld().addObject(new Bullet_Undirected(0, 3, (int)(i*120+ang+10), 8, 17, getX(), getY(), false), getX(), getY());            
        }
    }
    
    private void phase1ATK(){
        if(cnt==0){
            phase1Method1();
            x++; cnt = 4;
        }else{
            cnt--;
        }
    }
    
    private void phase2ATK(){
        if(cnt==0){
            x++; cnt = 8;
            phase2Method1();
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
