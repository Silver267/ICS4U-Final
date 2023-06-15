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
    private GreenfootImage ball;
    private int time, time1;
    private int[] direction;
    private boolean exist, reverse;
    private SideWorld sw;
    public Boss(){
        ball = new GreenfootImage("blackball.png");
        ball.scale(35,35);
        setImage(ball);
        time = 0;
        direction = new int[8];
        for(int i = 0; i < 4; i++){
            direction[i] = i*90;
        }
        for(int i = 4; i < 8;i++){
            direction[i] = 45 + (i-4)*90;
        }
        reverse = false;
    }
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(sw.getCountTime() % 600 != 0){
            roundShoot(0);
            roundShoot(1);
            roundShoot(2);
            roundShoot(3);
            if(time1 > 750){
                roundShoot(4);
                roundShoot(5);
                roundShoot(6);
                roundShoot(7);
            }
            time--;
            time1++;
            skyKill();
        }
        
    }
    
    public void addedToWorld(World w){
        exist = true;
        sw = (SideWorld)w;
    }
    
    public void roundShoot(int x){
        if(time1 < 300){
            direction[x]++;
            if(time % 5 == 0 && exist){
            getWorld().addObject(new LightBall(false, direction[x]), getX(), getY());
        }
        }else if(time1 < 600){
            direction[x]+=2;
            if(time % 4 == 0 && exist){
            getWorld().addObject(new LightBall(false, direction[x]), getX(), getY());
        }
        }else if(time1 < 900){
            direction[x]+=3;
            if(time % 3 == 0 && exist){
            getWorld().addObject(new LightBall(false, direction[x]), getX(), getY());
        }
        }else if(time1 < 1200){
            direction[x]-=2;
            if(time % 4 == 0 && exist){
                getWorld().addObject(new LightBall(false, direction[x]), getX(), getY());
            }
        }else if(time1 < 1500){
            direction[x]--;
            if(time % 5 == 0 && exist){
                getWorld().addObject(new LightBall(false, direction[x]), getX(), getY());
            }
        }else{
            time1 = 0;
        }
        
        
        
    
    }
    
    public void skyKill(){
        if(time1 % 600 == 0){
            Random r = new Random();
            int x = 336 + (1 + r.nextInt(9))*66;
            int y = 655 - (1 + r.nextInt(9))*40;
            getWorld().addObject(new Bomb(), x, y);
        }
        
        
    }
}
