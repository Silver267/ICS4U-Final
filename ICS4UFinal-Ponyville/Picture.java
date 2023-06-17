import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Picture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Picture extends Enemy
{
    private GreenfootImage[] bigHead;
    public Picture(int x){
        bigHead = new GreenfootImage[3];
        bigHead[0] = new GreenfootImage("MainPony/FS-1.png");
        bigHead[1] = new GreenfootImage("MainPony/PP-1.png");
        bigHead[2] = new GreenfootImage("MainPony/TS-1.png");
        for(int i = 0; i < 3; i++){
            bigHead[0].scale(150, 168);
        }
        setImage(bigHead[x]);
    }
    /**
     * Act - do whatever the Picture wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
