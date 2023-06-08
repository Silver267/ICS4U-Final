import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Chaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chaser extends Encounters{
    
    private GreenfootImage img;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private boolean chk(int[] cur){
        if(cur[0]<0 && cur[1]<0 && cur[0]>=20 && cur[1]>=11)
            return false;
        if(((MainWorld)getWorld()).getMap().getNode(cur).getType()!=2){
            
        }
        return true;
    }
    
    public void decide(){
        int[] ccoord = ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(ccoord);
        int[][] vis = new int[20][11];
        for (int[] i:vis)
            Arrays.fill(i, -1);
        vis[ccoord[0]][ccoord[1]] = -2;
        int[] plCoord = Statics.getPlayerCoords();
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0]==plCoord[0] && cur[1]==plCoord[1])
                break;
            for(int i=0; i<4; i++){
                int[] curr = ((MainWorld)getWorld()).getMap().getMaps(new int[]{getX(), getY()});
                curr[0] += dirs[i][0]; curr[1] += dirs[i][1];
                if(chk(curr) && vis[curr[0]][curr[1]]==-1){
                    q.add(curr);
                    vis[curr[0]][curr[1]] = i;
                }
            }
        }
        
    }
}
