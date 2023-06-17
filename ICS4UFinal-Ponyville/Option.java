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
    private boolean startTimer, check;
    private int timer;
    /**
     * This is the constructor of class Option
     *
     * @param gf This will take in an image
     * @param gf1 This will take in another image
     * @param check This will take in a boolean and determine type of button, if true, then it is a continue button, false will means it is a normal button
     */
    public Option(GreenfootImage gf, GreenfootImage gf1, boolean check){
        animation = new GreenfootImage[2];
        animation[0] = gf;
        animation[1] = gf1;
        this.check = check;
        startTimer = false;
        timer = 20;
        
        if(check){
            animation[0].scale(85, 85);
            animation[1].scale(85, 85);
            setImage(gf1);
        }else{
            animation[0].scale(110, 110);
            animation[1].scale(110, 110);
            setImage(gf);
        }
        
        
        
    }
    
    
    
    public void act(){
        if(startTimer){
            timer--;
            
        }
        if(check){
            if(((SideWorld)getWorld()).getContinueChoose()){
                animation();
            }
        }else{
            if(((SideWorld)getWorld()).getContinueChooseLine()){
                animation();
            }
            
        }
        
        
        
    }
    
    public boolean isClick(){
        if(Greenfoot.mouseClicked(this)){
            startTimer = true;
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param x This boolean will determine which image to return, true means return teh first one, false means return the second one
     */
    public GreenfootImage getImage(boolean x){
        if(x){
            return animation[0];
        }else{
            return animation[1];
        }
    }
    
    private void animation(){
        if(isClick()){
            setImage(animation[1]);
        }
        if(timer == 0){
            setImage(animation[0]);
            timer = 20;
            startTimer = false;
        }
    }
    
}
