import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HPBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HPBar extends SuperSmoothMover
{
    private GreenfootImage hpBar;
    private SideWorld sw;
    private boolean side;
    private int hpI, hpC;//hpI is the inital hp, hpC is the hp that will change
    //side = true means it will show the main ch, visaversa
    public HPBar(boolean side){
    
        if(side){
            hpI = Statics.getHP();
            hpBar = SparkleEngine.drawProgressBar(hpI, Statics.getHP(), 150, 15, Color.RED, Color.BLACK);
        }
        this.side = side;
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
        if(!side){
            hpI = sw.getBoss().getHp();
            hpBar = SparkleEngine.drawProgressBar(hpI, Statics.getHP(), 150, 15, Color.RED, Color.BLACK);
        }
        setImage(hpBar);
    }
    /**
     * Act - do whatever the HPBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        hp();
    }
    
    public void hp(){
        if(side){
            hpC = Statics.getHP();
            hpBar = SparkleEngine.drawProgressBar(hpI, hpC, 150, 15, Color.RED, Color.BLACK);
            setImage(hpBar);
        }else{
            hpC = sw.getBoss().getHp();
            hpBar = SparkleEngine.drawProgressBar(hpI, hpC, 150, 15, Color.RED, Color.BLACK);
            setImage(hpBar);
        }
    }
}
