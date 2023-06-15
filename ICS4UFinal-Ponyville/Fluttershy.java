import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PonyBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fluttershy extends Enemy{
    /**
     * Act - do whatever the PonyBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, cnt;
    private double prevAng;
    
    public Fluttershy(){
        cnt = 0;
        x = 0;
        prevAng = 0;
    }
    
    private double phase1Method1(double dx, double ang){//method to generate the bullets of phase 1
        double fx = ((-1/10.0)*dx + 28/3.0); //generation (angle) function
        ang = ang+fx;
        int xpos = this.getX(), ypos = this.getY();
        for(int i=0; i<4; i++){//function to generate bullet for first spell card.
            double theta = 180-140/3 + i*90;//angle offset
            //set speed to 3.46 + x/584.0 so that the speed of bullets are increasing.
            getWorld().addObject(new Bullet_Undirected(0, (3.46+x/584.0), (int)(ang+theta), 8, 17, xpos, ypos, false), xpos, ypos);
        }
        return ang;
    }

    private void phase1ATK(){ //Attack method for phase1 (each progression is calculated by frames)
        if(cnt<=0){
            cnt = 1;
            prevAng = phase1Method1(x, prevAng);
            x++;
        }else{
            cnt--;
        }
    }
    
    
    private void die(){
        
    }
    
    public void act(){
        phase1ATK();
        // Add your action code here.
    }
}
