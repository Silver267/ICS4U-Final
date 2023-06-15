import greenfoot.*;
/**
 * Undirected bullet (goes in a straight line and will not automatically aim to target)
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class Bullet_Undirected extends removableBullet{
    //Borrowed the concept of speed, friendly from Mr. Cohen
    private int pict, power, size, facing;
    //pict = 0 -> Enemy normal bullet
    //pict = 1 -> Player normal bullet
    private double speed;
    private SideWorld sw;
    //facing is the side which the bullet is facing
    //for bullets which heads a straight line but initially facing the player.
    //power is the power of the bullet, depends how many hp (or shield) the target will take
    //if touching this bullet.
    //size is the size of bullet.
    //Note that this bullet does not have a "hit zone", so size should be < 15.
    private boolean friendly;
    //variables for sub-pixel movement
    private double subPixPosX, subPixPosY, subPixIncX, subPixIncY;
    
    public Bullet_Undirected(int pict, double speed, int facing, int power, int size, int x, int y, boolean friendly){
        this.pict = pict; this.speed = speed; this.facing = facing; this.power = power; this.size = size; this.friendly = friendly;
        setRotation(facing);
        setImage(temp());
        setLocation(x, y);
        //initialization of sub-pixel movement - Stores the increase value in a double.
        subPixPosX = x; subPixPosY = y;
        subPixIncX = Math.cos((facing)*(Math.PI/180))*speed;
        subPixIncY = Math.sin((facing)*(Math.PI/180))*speed;
    }
    
    public void addedToWorld(World w){
        sw = (SideWorld)w;
    }

    public void act(){
        //custom sub-pixel movement in replacemenent of move() which features accurate movement at angle.
        subPixPosX+=subPixIncX; subPixPosY+=subPixIncY;
        setLocation((int)subPixPosX, (int)subPixPosY);
        if(friendly){//If friendly bullet, decrease enemy hp when hitted the enemy.
            if(this.isTouching(Boss.class)){
                getWorld().removeObject(this);
                sw.getBoss().damage(-8);
                return;
            }
        }else{//else, decrease player hp when hitted the player.
            if(this.isTouching(HitBox.class)){
                getWorld().removeObject(this);
                Statics.setHP(Statics.getHP()-1);
                //TODO: If HP <=0, send to start of the world.
                return;
            }
        }
        if(isEdge())//If touching edge or touching information panel, remove itself.
            getWorld().removeObject(this);
    }
    
    private boolean isEdge(){
        int x = getX(), y = getY();
        if(x<-100 || y<-100)
            return true;
        if(x>1700 || y>775)
            return true;
        return false;
    }

    private GreenfootImage temp(){//Draw image,doesn't matter
        if(pict==0){
            //Enemy bullet
            GreenfootImage image = new GreenfootImage(size*3/2, size*2/3);
            image.setColor(new Color(231, 76, 60));
            image.fillOval(0, 0, size*3/2, size*2/3);
            image.setColor(Color.WHITE);
            image.drawOval(0, 0, size*3/2, size*2/3);
            return image;
        }
        if(pict==1){
            //Player's bullet
            GreenfootImage image = new GreenfootImage("Star.png");
            return image;
        }
        return null;
    }
    
    public void gone(){
        
    }
}
