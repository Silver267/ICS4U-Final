import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Panel here.
 * 
 * @author Xuanxi Jiang
 * @version (a version number or a date)
 */
public class Panel extends Actor{
    private GreenfootImage base, orb,greyOrb;
    private int maxX, maxY;
    private Label HPVal, MPVal, ColectVal;
    
    public void addedToWorld(World w){
        base = new GreenfootImage("Banner.jpg");
        orb = new GreenfootImage("textures/Ball.png");
        orb.scale(35, 35);
        greyOrb = SparkleEngine.greyIFy(orb);
        maxX = 1200; maxY = ((MainWorld)getWorld()).getMap().getSz()[1]+4;
        base.scale(maxX, maxY);
        setImage(base);
        HPVal = new Label("HP: ", 30);
        MPVal = new Label("MP: ", 30);
        ColectVal = new Label("Orb: ", 30);
        w.addObject(HPVal, 60, maxY/2);
        w.addObject(MPVal, maxX/3, maxY/2);
        w.addObject(ColectVal, (maxX*2)/3, maxY/2);
        refresh();
    }
    
    public void act(){
        
    }
    
    public void refresh(){
        GreenfootImage proc = new GreenfootImage(base);
        for(int i=0; i<3; i++)
            proc.drawImage(Statics.getOrb()<(3-i) ? orb : greyOrb, (maxX*2)/3+i*40+35, maxY/2-35/2);
        setImage(proc);
    }
}
