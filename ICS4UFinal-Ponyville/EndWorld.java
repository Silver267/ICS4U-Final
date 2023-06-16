import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleWorld here.
 * 
 * @author Yawen Zhang
 * @version 0.1
 */
public class EndWorld extends World
{
    private boolean end;
    private Label instruction;
    private GreenfootSound bgm;
    /**
     * Constructor for objects of class BattleWorld.
     * 
     */
    public EndWorld(boolean result)
    {    
        // Create a new world with 1200x675 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        if(result)
            Statics.saveTime();
        bgm = new GreenfootSound("bgm-end.mp3");
        GreenfootImage img = new GreenfootImage(result?"EndWorldGood.jpg":"EndWorldBad.jpg");
        img.scale(1200, 675);
        setBackground(img);
        music();
    }
    
    public void started(){
        music();
    }
    
    public void stopped(){
        unMusic();
    }
    
    /**
     * Don't play music.
     */
    private void unMusic(){
        bgm.stop();
    }
    
    /**
     * Play music.
     */
    private void music(){
        bgm.playLoop();
    }
    
    public void setEnd(){
        end = true;
    }
}
