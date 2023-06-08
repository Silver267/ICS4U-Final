import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class floatingPanel here.
 * 
 * @author Xuanxi Jiang
 * @version (a version number or a date)
 */
public class floatingPanel extends Actor{
    private long prevTime, cnt, duration;
    
    private void init(int duration, GreenfootImage i){
        setImage(i); cnt = 0;
        if(duration==-1)
            prevTime = -1;
        this.duration = duration;
    }
    
    /**
     * @param duration  duration in seconds.
     * @param content   the content of the floating panel.
     */
    public floatingPanel(int duration, String content){
        GreenfootImage base = new GreenfootImage("textures/Box.png");
        init(duration, base);
    }
    
    /**
     * @param duration  duration in seconds
     * @param i         Image that this floating panel displays
     */
    public floatingPanel(int duration, GreenfootImage i){
        init(duration, i);
    }
    
    //Fade in, Fade out
    public void animation(){
        
    }
    
    /**
     * Act - do whatever the floatingPanel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        // Add your action code here.
    }
}
