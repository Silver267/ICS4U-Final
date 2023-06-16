import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The 'A, B, C, D' keys.
 * 
 * @author George Liu
 * @version 1.0
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
    
    public boolean isClick(){
        startCount = true;
        return Greenfoot.mouseClicked(this);
    }
    
}
