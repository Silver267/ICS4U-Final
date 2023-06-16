import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Generate2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Generate2 extends removableBullet{
    private int activeCnt, x;
    
    public Generate2(int activeCnt){
        this.activeCnt = activeCnt;
        GreenfootImage tmp = new GreenfootImage("generate.png");
        tmp.scale(75, 75); x = 60;
        tmp.setTransparency(127);
        setImage(tmp);
    }
    
    public void act(){
        if(activeCnt!=0)
            activeCnt--;
        else{
            if(x==0){
                getWorld().removeObject(this);
                return;
            }
            if(x%12==0){
                for(int i=0; i<6; i++){
                    getWorld().addObject(new Bullet_Undirected(0, 3, (int)(Greenfoot.getRandomNumber(30)+i*60), 1, 15, getX(), getY(), false), getX(), getY());
                }
            }
            x--;
        }
    }
}
