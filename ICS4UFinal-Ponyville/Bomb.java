import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends StraightDart
{
    private GreenfootImage bomb, red;
    private boolean flashing = true;
    private boolean visible = true;
    private boolean hitagain = true;
    private int counter = 50;
    private int counter1 = 5;
    private int counter2 = 120;
    public Bomb(){
        bomb = new GreenfootImage("skyBomb.jpg");
        setImage(bomb);
        red = new GreenfootImage(60, 40);
        red.setColor(Color.RED);
        red.fill();
    }
    
    public void act()
    {
        if (flashing){
            if (!visible){
                if (counter1 > 0){
                    counter1--;
                    if (counter1 == 0){
                        counter1 = 5;
                        visible = true;
                        bomb.setTransparency(255);
                    }
                }
            }
            else if (counter > 0){
                counter--;
                if (counter == 0){
                    bomb.setTransparency(0);
                    visible = false;
                    counter = 50;
                }
            }
            counter2--;
            if (counter2 == 0){
                flashing = false;
                counter2 = 30;
                counter1 = 5;
                this.setImage(red);
            }
        }
        else{
            //bomb = new GreenfootImage(red);
            if (!hitagain){
                counter1--;
                if (counter1 == 0){
                    hitagain = true;
                    counter1 = 5;
                }
            }
            if (isTouching(HitBox.class)){
                if (hitagain){
                    Statics.setHP(Statics.getHP()-1);
                    hitagain = false;
                }
            }
            counter2--;
            if (counter2 == 0){
                getWorld().removeObject(this);
            }
        }
    }
}
