import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileNotFoundException;

/**
 * Write a description of class StartWorld here.
 * The starting world for this game. Introduces the background story and instructions for the user to better understand the game.
 * 
 * @author Molly Wu 
 * @version 6.9.2023
 */

public class StartWorld extends World
{
    GreenfootImage img;
    private int count = 0;
    private Label instructionOne;
    
    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        // Resizing the image to world size.
        img = new GreenfootImage("StartWorld.jpg");
        img.scale(1200, 675);
        setBackground(img);
        instructionOne = new Label("prev    next", 30);
    }
    
    public void act(){
        if (count == 9){
            moveWorld();
        }
        if (count == 0) {
            if (Greenfoot.isKeyDown("space")) {
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
            setBackground(new GreenfootImage(count+".jpg"));
        }
        if (rightKeyDown && !Greenfoot.isKeyDown("right")) {
            rightKeyDown = false;
        }
    }
}
