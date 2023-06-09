import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Chaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chaser extends MovingInTurns{
    
    private GreenfootImage img;
    private int cnt, hp, prehp;
    private Stack<Integer> curstk;
    private Stack<int[]> curPos;
    private GreenfootImage cc, ccdamaging;
    
    public Chaser(){
        curstk = new Stack<Integer>();
        curPos = new Stack<int[]>();
        moving = false; hp = 45; prehp = 45;
        spd = 3;
    }
    
    public void addedToWorld(World w){
        prevPos = new int[]{getX(), getY()};
        realPos = new int[]{getX(), getY()};
        cc = new GreenfootImage("EvilCharacter.png");
        cc.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
        ccdamaging = new GreenfootImage(SparkleEngine.greyIFy(cc));
        setImage(cc);
    }
    
    protected void move(){
        if(!moving && cnt>0 && !curstk.isEmpty()){
            moving = true;
            cnt--; dir = curstk.pop();
            realPos = curPos.pop();
        }
        if(moving)
            shift(((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()}));
    }
    
    protected void display(){
        if(hp!=prehp){
            setImage(ccdamaging);
            prehp = hp;
        }else{
            setImage(cc);
        }
    }
    
    public void damage(){
        hp--;
    }
    
    public int getHP(){
        return hp;
    }
    
    public void action(int[] plCoord){
        decide(plCoord);
        cnt = 2;
    }

    public int[] where(){
        return ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
    }
    
    private boolean chk(int[] cur){
        if(cur[0]<0 || cur[1]<0 || cur[0]>=20 || cur[1]>=11)
            return false;
        if(((MainWorld)getWorld()).getMap().getNode(cur).getType()==2)
            return false;
        return true;
    }
    
    private void decide(int[] plCoord){
        int[] ccoord = ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(ccoord); int[][] vis = new int[20][11];
        for (int[] i:vis)
            Arrays.fill(i, -1);
        vis[ccoord[0]][ccoord[1]] = -2;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0]==plCoord[0] && cur[1]==plCoord[1])
                break;
            for(int i=0; i<4; i++){
                int[] curr = cur.clone();
                curr[0] += dirs[i][0]; curr[1] += dirs[i][1];
                if(chk(curr) && vis[curr[0]][curr[1]]==-1){
                    q.add(curr);
                    vis[curr[0]][curr[1]] = i;
                }
            }
        }
        curstk.clear(); curPos.clear();
        int[] btCoord = plCoord.clone();
        while(vis[btCoord[0]][btCoord[1]]!=-2){
            int tmp = vis[btCoord[0]][btCoord[1]];
            curstk.push(tmp); 
            curPos.push(((MainWorld)getWorld()).getMap().getPixes(btCoord));
            btCoord[0] -= dirs[tmp][0]; btCoord[1] -= dirs[tmp][1];
        }
    }
}
