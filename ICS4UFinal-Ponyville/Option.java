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
    private boolean needBlack;
    private int time1;
    //int check meant to check if the option is a continue button
    public Option(GreenfootImage gf, GreenfootImage gf1, boolean check){
        animation = new GreenfootImage[2];
        animation[0] = gf;
        animation[1] = gf1;
        
        if(check){
            animation[0].scale(85, 85);
            animation[1].scale(85, 85);
        }else{
            animation[0].scale(110, 110);
            animation[1].scale(110, 110);
        }
        time1 = 20;
        
        setImage(gf);
    }
    
    /**
     * @param choose This means the image selected, true means first one, false means second one
     */
    public GreenfootImage getImage(boolean choose){
        if(choose){
            return animation[0];
        }else{
            return animation[1];
        }
    }
    
    public void act(){
        continueAnimation();
        if(needBlack){
            time1--;
        }
    }
    
    public boolean isClick(){
        if(Greenfoot.mouseClicked(this)){
            needBlack = true;
        }
       
        return Greenfoot.mouseClicked(this);
    }
    
    public void continueAnimation(){
        if(isClick()){
            needBlack = true;
            setImage(animation[1]);
        }
        if(needBlack && time1 == 0){
            needBlack = false;
            time1 = 20;
            setImage(animation[0]);
        }
    }
    
}
