import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileNotFoundException;

/**
 * Write a description of class StartWorld here.
 * The starting world for this game. Introduces the background story and instructions for the user to better understand the game.
 * 
 * Notes:
 * This is a release-preview version of the game, some features are not polished, and there are some errors in the introduction screen too.
 * 
 *  Additional notes:
 *      The keys to move around is not "wasd", instead, its the arrow keys (up, down, left, right)
 *      These keys are the same keys that you will use in battle when dodging bullets.
 * 
 * @author Molly Wu & Yawen & Xuanxi
 * @version 6.9.2023
 */

public class StartWorld extends World
{
    GreenfootImage img;
    private int count = 0;
    private Label instructionOne;
    private GreenfootSound bgm;
    private Frame f;
    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    
    private void unMusic(){
        bgm.stop();
    }
    
    private void music(){
        bgm.playLoop();
    }
    
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        // Resizing the image to world size.
        img = new GreenfootImage("StartWorld.jpg");
        img.scale(1200, 675);
        if(Statics.loadTime()!=-1)
            addObject(f = new Frame(), 900, 600);
        setBackground(img);
        instructionOne = new Label("prev    next", 30);
        bgm = new GreenfootSound("bgm-start.mp3");
        bgm.stop(); bgm.setVolume(70);
    }
    
    public void started(){
        music();
    }
    
    public void stopped(){
        unMusic();
    }
    
    public void act(){
        if (count == 9){
            moveWorld();
        }
        if (count == 0) {
            if (Greenfoot.isKeyDown("space")) {
                if(f!=null)
                    f.removeSelf();
                count++;
                setBackground(new GreenfootImage(count+".jpg"));
            }
        }        
        if (count < 9 && count > 0) {
            addObject(new PageIcon(), 240, 600);
            addObject(instructionOne, 240, 640);
            moveRight();
            moveLeft();
        }
    }
    
    public void moveWorld(){
        Statics.setLevel(1);
        Statics.setHP(40);
        Statics.setActive(false);
        Statics.setOrb(3);
        Statics.rsetStay();
        unMusic(); Statics.begin();
        Greenfoot.setWorld(new MainWorld());
    }
    
    private boolean leftKeyDown;
    private void moveLeft() {
        if (!leftKeyDown && Greenfoot.isKeyDown("left")) {
            leftKeyDown = true;
            count = Math.max(1, count-1);
            setBackground(new GreenfootImage(count+".jpg"));
        }
        if (leftKeyDown && !Greenfoot.isKeyDown("left")) {
            leftKeyDown = false;
        }
    }
    
    private boolean rightKeyDown;
    private void moveRight() {
        if (!rightKeyDown && Greenfoot.isKeyDown("right")) {
            rightKeyDown = true;
            count++;
            if(count<9)
                setBackground(new GreenfootImage(count+".jpg"));
        }
        if (rightKeyDown && !Greenfoot.isKeyDown("right")) {
            rightKeyDown = false;
        }
    }
}
