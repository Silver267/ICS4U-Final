import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class sidePlane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SidePlane extends Enemy
{
    private GreenfootImage image;
    private int time, direction;
    public SidePlane(){
        image = new GreenfootImage(30, 10);
        image.setColor(new Color(231, 76, 60));
        image.fillOval(0, 0, 30, 10);
        image.setColor(Color.WHITE);            
        image.drawOval(0, 0, 30, 10);
        setImage(image);
        time = 15;
        direction = 20;
    }
    /**
     * Act - do whatever the sidePlane wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        time--;
        direction++;
        if(direction == 140){
            direction = 20;
        }
        shoot();
        // Add your action code here.
    }
    
    public void shoot(){
        if(time % 15 == 0){
            getWorld().addObject(new LightBall(true, direction), getX(), getY());
            getWorld().addObject(new LightBall(true, direction + 20), getX() , getY());
            getWorld().addObject(new LightBall(true, direction + 40), getX() , getY());
        }
    }
}
