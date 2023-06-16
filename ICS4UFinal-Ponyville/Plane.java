import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The thing that Boss is supposed to call at the second phase
 * 
 * @author George Liu
 * @version 1.0
 */
public class Plane extends Enemy
{
    private int direction, ind, time, time1, shoot;//direction will determine the direction of this ball, time will determine time this object went through, time1 will be the [place to store time
    //shoot: number of directions the plane will shoot.
    private boolean turn, activate;//this boolean will control when to turn
    private double bs,os, ts; //bs: bullet speed; os = self (own) speed; ts = turning speed (turning speed of Plane)
    public Plane(int shoot, double bs, double os, double ts, int path, int direction){
        GreenfootImage image = new GreenfootImage(30, 10);
        image.setColor(new Color(231, 76, 60));
        image.fillOval(0, 0, 30, 10);
        image.setColor(Color.WHITE);            
        image.drawOval(0, 0, 30, 10);
        setImage(image);
        direction = ind; activate = false;
        setRotation(direction);
        this.bs = bs;
        this.os = os;
        this.ts = ts;
        turn = true;
        this.shoot = shoot;
        ind = direction;
    }
    /**
     * Act - do whatever the Plane wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        time++;
        moveRound();
        move(os);
        if(time%16==0)
            shootBullet();
    }
    
    public void moveRound(){
        if(turn){
            direction=(direction+2)%360;
            setRotation(direction);
        }
        
        if(direction == ind && !activate){
            time1 = time; activate = true;
            turn = false;
        }
        
        if(time - time1 > 120){
            turn = true; activate = false;
        }
    }
    
    
    public void shootBullet(){
        for(int i=0; i<shoot; i++){
            getWorld().addObject(new Bullet_Undirected(0, bs, i*(360/shoot)+direction, 1, 13, getX(), getY(), false), getX(), getY());
        }
    }
}
