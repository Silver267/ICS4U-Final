import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProgressBar here.
 * 
 * @author Yawen
 * @version 20230606
 */
public class ProgressBar extends Actor{
    GreenfootImage HPBar, MPBar;
    GreenfootImage img;
    int selection, barValue;
    
    /**
     * Act - do whatever the ProgressBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        //implementBar();
        if(selection==0)
            setImage(SparkleEngine.drawProgressBar(50, Statics.getHP(), 160, 20, new Color(255, 128, 128), new Color(204, 0, 0)));
        else if(selection==1)
            setImage(SparkleEngine.drawProgressBar(100, Statics.getMP(), 160, 20, new Color(255, 153, 255), new Color(179, 0, 179)));
    }
    
    public ProgressBar(int select){
        selection = select;
        if(selection==0)
            setImage(SparkleEngine.drawProgressBar(50, Statics.getHP(), 160, 20, new Color(255, 128, 128), new Color(204, 0, 0)));
        else if(selection==1)
            setImage(SparkleEngine.drawProgressBar(100, Statics.getMP(), 160, 20, new Color(255, 153, 255), new Color(179, 0, 179)));
    }
}
