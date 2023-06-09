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
    
    public Chaser(){
        setImage("TheHeart.png");
    }
    
    
    private boolean chk(int[] cur){
        if(cur[0]<0 || cur[1]<0 || cur[0]>=20 || cur[1]>=11)
            return false;
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
        System.out.println(plCoord[0]+"  "+plCoord[1]);
         System.out.println(ccoord[0]+"  "+ccoord[1]);
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
        LinkedList<Integer> backTrack = new LinkedList<Integer>();
        int[] btCoord = Statics.getPlayerCoords();
        while(vis[btCoord[0]][btCoord[1]]!=-2){
            int tmp = vis[btCoord[0]][btCoord[1]];
            backTrack.add(tmp);
            btCoord[0] -= dirs[tmp][0]; btCoord[1] -= dirs[tmp][1];
        }
        for(int i:backTrack){
            System.out.println(i);
        }
    }
}
