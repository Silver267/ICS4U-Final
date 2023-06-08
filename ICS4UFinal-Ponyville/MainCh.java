import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainCh here.
 * 
 * @author Xuanxi Jiang
 * @version (a version number or a date)
 */
public class MainCh extends SuperSmoothMover{
    
    private int[] realPos, prevPos;
    private double[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int dir, xx, turn, magic, targMP;
    private final double spd = 1.5;
    private boolean moving, facingFront;
    private floatingPanel fp;
    private GreenfootImage stand, standBack;
    private GreenfootImage[] walk = new GreenfootImage[16], walkBack = new GreenfootImage[16];
    
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
        moving = false;
        dir = -1; xx = 0; turn = 0;
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
    
    public void act(){
        move();
        display();
    }
    
    private void display(){
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
    public void move(){
        int x = getX(), y = getY();
        int[] gridPos = ((MainWorld)getWorld()).getMap().getMaps(new int[]{x, y});
        Statics.setPlayerCoords(gridPos);
        if(Statics.getMP()!=targMP)
            Statics.setMP(Statics.getMP()<targMP ? Statics.getMP()+1:Statics.getMP()-1);
        if(!moving){
            if(detect(gridPos)){
                targMP = Math.min(100, Statics.getMP()+25);
                turn++;
            }else if(Greenfoot.isKeyDown("z") && ((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0], gridPos[1]}).getType()>2){
                ((MainWorld)getWorld()).goBattle(((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0], gridPos[1]}).getType()-3);
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
        if(dir!=-1)
            shift(gridPos);
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
    
    /**
     * Animation that moves the character towards the target grid (so the character does not appear to be "teleported" to grids.
     * 
     * @param gridPos   The current position of character on grid {x, y}
     */
    private void shift(int[] gridPos){
        if(dir==1 || dir==3){
            if(prevPos[0]+dirs[dir][0]*spd*xx<=realPos[0] && prevPos[1]+dirs[dir][1]*spd*xx<=realPos[1]){
                setLocation((double)prevPos[0]+dirs[dir][0]*spd*xx, (double)prevPos[1]+dirs[dir][1]*spd*xx);
                xx++;
            }else{
                rsetPos(gridPos);
            }
        }else{
            if(prevPos[0]+dirs[dir][0]*spd*xx>=realPos[0] && prevPos[1]+dirs[dir][1]*spd*xx>=realPos[1]){
                setLocation((double)prevPos[0]+dirs[dir][0]*spd*xx, (double)prevPos[1]+dirs[dir][1]*spd*xx);
                xx++;
            }else{
                rsetPos(gridPos);
            }
        }
    }
    
    /**
     * Set the position of character on grid to the target grid.
     * Used by shift() method when the target position (animation) is reached or exceeded.
     */
    private void rsetPos(int[] gridPos){
        moving = false;
        dir = -1; xx = 0;
        setLocation(realPos[0], realPos[1]);
        prevPos = realPos.clone();
        if(((MainWorld)getWorld()).getMap().getNode(new int[]{gridPos[0], gridPos[1]}).getType()>2){
            //call floating window to press z
        }
    }
}
