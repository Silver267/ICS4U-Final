import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Option here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Option extends BattleScreen
{
    private GreenfootImage[] animation;
    private boolean startCount;
    private int count;
    //int check meant to check if the option is a continue button
    public Option(GreenfootImage gf, GreenfootImage gf1, boolean check){
        animation = new GreenfootImage[2];
        animation[0] = gf;
        animation[1] = gf1;
        
        if(check){
            animation[0].scale(85, 85);
        }else{
            animation[0].scale(110, 110);
        }
        
        setImage(gf);
    }
    /**
     * Act - do whatever the Option wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public boolean isClick(){
        startCount = true;
        return Greenfoot.mouseClicked(this);
    }
    
}
