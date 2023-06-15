import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class PinkiePie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PinkiePie extends Enemy{
    /**
     * Act - do whatever the PinkiePie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, meth, timer, prevAng, cnt;
    private double ang; 
    private SideWorld sw;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public PinkiePie(int meth, int duration){
        x = 0; this.meth = meth;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/PP-1.png");
        gf.scale(150, 150);
        setImage(gf);
    }
    
    private void phase1Method1(){
        double fx = (8*Math.sin(0.3*x));
        ang = ang+fx;
        for(int i=0; i<8; i++){
            getWorld().addObject(new Bullet_Gens((int)(i*45+ang), (int)(i*45+ang), getX(), getY(), 2), getX(), getY());
        }
    }
    
    private void phase2Method1(){
        int px = ((SideWorld)getWorld()).getHitBox().getX()-getX();
        int py = ((SideWorld)getWorld()).getHitBox().getY()-getY();
        for(int i=0; i<6; i++){
            getWorld().addObject(new Bullet_Undirected(0, 5, (int)(Math.toDegrees(Math.atan2(py, px)))+i*60, 1, 18, getX(), getY(), false), getX(), getY());
        }
    }
    
    private void phase2Method2(){
        int offset = Greenfoot.getRandomNumber(50);
        for(int i=0; i<8; i++){
            getWorld().addObject(new bullet_turn(90, i*180+60+offset, getY(), Greenfoot.getRandomNumber(2)%2==0), i*180+60+offset, getY());
        }
    }
    
    private void phase1ATK(){
        if(cnt==0){
            phase1Method1();
            x++; cnt = 450;
        }else{
            cnt--;
        }
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
    }
    
    private void phase2ATK(){
        if(cnt==0){
            phase2Method2();
            cnt = 120;
        }else{
            cnt--;
        }
        if(cnt%60==0){
            x++;
            phase2Method1();
        }
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
