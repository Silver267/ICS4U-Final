import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainCh here.
 * 
 * @author Xuanxi Jiang
 * @version (a version number or a date)
 */
public class MainCh extends MovingInTurns{
    private int magic, targMP;
    private boolean facingFront;
    private GreenfootImage stand, standBack;
    private GreenfootImage[] walk = new GreenfootImage[16], walkBack = new GreenfootImage[16];
    private floatingPanel fp;
    
    public MainCh(){
        spd = 1.5;
    }
    
    public void addedToWorld(World w){
        standBack = new GreenfootImage("FH/Stand/tile000.png");
        standBack.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
        stand = new GreenfootImage(standBack);
        stand.mirrorHorizontally();
        for(int i=0; i<=15; i++){
            walkBack[i] = new GreenfootImage("FH/Walk/tile0"+(i<10?"0":"")+i+".png");
            walkBack[i].scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
            walk[i] = new GreenfootImage(walkBack[i]);
            walk[i].mirrorHorizontally();
        }
        moving = false; xx = 0;
        int[] gridPos = ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
        Statics.setPlayerCoords(gridPos);
        int[] Mid = ((MainWorld)w).getMap().getPixes(gridPos);
        setLocation(Mid[0], Mid[1]);
        prevPos = new int[]{getX(), getY()};
        realPos = new int[]{getX(), getY()};
        targMP = 100;
        facingFront = true;
        setImage(stand);
    }
    
    public boolean isMoving(){
        return moving;
    }
    
    protected void display(){
        if(!moving){
            if(facingFront)
                setImage(stand);
            else
                setImage(standBack);
        }else{
            if(facingFront)
                setImage(walk[xx%16]);
            else
                setImage(walkBack[xx%16]);
        }
    }
    
    /**
     * Method for all the character's behaviors, including:
     * pressing "alt" to use magic
     * pressing "w", "a", "s", "d" to move around
     * and animation of moving towards another grid.
     */
    protected void move(){
        int x = getX(), y = getY();
        int[] gridPos = ((MainWorld)getWorld()).getMap().getMaps(new int[]{x, y});
        Statics.setPlayerCoords(gridPos);
        if(Statics.getMP()!=targMP)
            Statics.setMP(Statics.getMP()<targMP ? Statics.getMP()+1:Statics.getMP()-1);
        if(!moving){
            if(detect(gridPos)){
                targMP = Math.min(100, Statics.getMP()+25);
                ((MainWorld)getWorld()).action(gridPos);
            }else if(Greenfoot.isKeyDown("z") && ((MainWorld)getWorld()).getMap().getNode(gridPos).getType()>2 && Statics.getStay(((MainWorld)getWorld()).getMap().getNode(gridPos).getType()-3)==0){
                ((MainWorld)getWorld()).goBattle(((MainWorld)getWorld()).getMap().getNode(gridPos).getType()-3);
                return;
            }else if(Greenfoot.isKeyDown("alt") && Statics.getMP()>0){
                magic = Math.min(magic+2, 100);
                if(magic==100){
                    ((MainWorld)getWorld()).damage();
                    Statics.setMP(Math.max(Statics.getMP()-1, 0));
                    targMP = Statics.getMP();
                }
            }else{
                magic = Math.max(magic-2, 0);
            }
        }
        if(moving){
            shift(gridPos);
            magic = Math.max(magic-2, 0);
        }   
        if(Statics.getHP()<=0){
            Statics.setHP(50);
            Statics.setActive(false);
            getWorld().stopped();
            Greenfoot.setWorld(new MainWorld());
        }
    }
    
    /**
     * Gets the "magic" value of player, since the use of magic is an animation, magic is an int.
     * 
     * @return int      returns the magic value of player, 100 is considered "fully active".
     */
    public int getMagic(){
        return magic;
    }
    
    /**
     * Detect user input and move character's real position based on that.
     * Animation is implemented in shift() method.
     * 
     * @param gridPos   The current position of character on grid {x, y}
     * @return boolean  If the user have moved the character.
     */
    private boolean detect(int[] gridPos){
        xx = 0;
        if(Greenfoot.isKeyDown("w")){
            if(gridPos[1]-1>=0 && ((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0], gridPos[1]-1}).getType()!=2){
                gridPos[1]--; dir = 2;
                moving = true;
            }
        }else if(Greenfoot.isKeyDown("a")){
            if(gridPos[0]-1>=0 && ((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0]-1, gridPos[1]}).getType()!=2){
                gridPos[0]--; dir = 0;
                moving = true;
                facingFront = false;
            }
        }else if(Greenfoot.isKeyDown("s")){
            if(gridPos[1]+1<11 && ((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0], gridPos[1]+1}).getType()!=2){
                gridPos[1]++; dir = 3;
                moving = true;
            }
        }else if(Greenfoot.isKeyDown("d")){
            if(gridPos[0]+1<20 && ((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0]+1, gridPos[1]}).getType()!=2){
                gridPos[0]++; dir = 1;
                moving = true;
                facingFront = true;
            }
        }
        realPos = ((MainWorld)getWorld()).getMap().getPixes(gridPos);
        return moving;
    }
}
