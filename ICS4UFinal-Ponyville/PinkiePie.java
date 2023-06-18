import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Pinkie pie that releases bullets
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class PinkiePie extends Enemy{
    private int x, meth, timer, prevAng, cnt, cnt2;
    private double ang; 
    private SideWorld sw;
    private static int count;
    
    //meth: which attack pattern will use 
    private GreenfootImage gf;
    public PinkiePie(int meth, int duration){
        x = 0; this.meth = meth; cnt2 = 0;
        cnt = 0; ang = 0; timer = duration;
        gf = new GreenfootImage("MainPony/PP-1.png");
        gf.scale(150, 150);
        setImage(gf);
    }
    
    public static void countplus(){
        count++;
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
        ArrayList<Picture> p = (ArrayList<Picture>)(sw.getObjects(Picture.class));
        for(Picture x: p){
            sw.removeObject(x);
        }
    }
    
    private void phase2ATK(){
        if(cnt==0){
            phase2Method1();
            cnt = 60;
        }else{
            cnt--;
        }
        if(cnt2==0){
            phase2Method2();
            cnt2 = 180;
        }else{
            cnt2--;
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
            if(count >= 1){
                ((SideWorld)getWorld()).setConversation();
            }
            sw.setContinueChooseLine(true);
            sw.setSpeakFirst();
            sw.setContinueChoose(false);
            sw.addObject(new Picture(1), 600, 150);
            getWorld().removeObject(this);
        }
            
    }
}
