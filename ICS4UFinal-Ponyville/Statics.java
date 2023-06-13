import java.util.*;
import java.io.*;

/**
 * A class that stores static variables, could potentially be used for game saves.
 * 
 * @author Xuanxi Jiang
 * @version 0.1
 */
public class Statics{
    //Current level (from 1 to 3), player position x, player position y.
    //Note: ppX and ppY are coordinates of Map, 
    private static int lV, ppX, ppY, MP, HP, orbReim;
    private static boolean active;
    //below is use to read in all the conversations
    private static ArrayList<String> talkStorage;
    private static ArrayList<ArrayList<String>> talkStorage1;//talkStorage1 is to store each conversation one by one 
    private static ArrayList<ArrayList<String[][]>> talkStorage2;//talekStorage2 is to store the conversation by the choice
    private static String[] startLine;//this array will contain the first line of all the conversation
    private static Scanner sc;
    private static boolean moreLine;
    private static int numberOfTalk;
    
    
    /**
     * This method will return the startline array
     * 
     * @return String[]
     */
    public static String[] getStartLine(){
        return startLine;
    }
    
    /**
     * George's method
     * 
     * This method will read in all the conversations in the story.txt
     * This method should be call at the start of game
     */
    public static void takeInWords(){
        talkStorage = new ArrayList<String>();
        talkStorage1 = new ArrayList<ArrayList<String>>();
        talkStorage2 = new ArrayList<ArrayList<String[][]>>();
        
        
        try{
            sc = new Scanner(new File("Story.txt"));
        }catch(FileNotFoundException e){
                
        }
        
        //use to read in all the lines
        while(!moreLine){
            try{
                talkStorage.add(sc.nextLine());
            }catch (NoSuchElementException e){
                moreLine = true;
            }
        }
        
        //use to find the number of different enemy characters`
        for(int i = 0; i < talkStorage.size(); i++){
            String tmp = talkStorage.get(i);
            if(tmp.length() > 5 && tmp.substring(0,5).equals("Start")){
                numberOfTalk++;
            }
        }
        
        
        int c = 0;
        
        //use to put the conversation between each enemy character and the horse into diffeent arraylists
        for(int i = 0; i < numberOfTalk; i++){
            boolean go = true;
            ArrayList<String> tmp = new ArrayList<String>();
            while(go){
                String tmp1 = talkStorage.get(c);
                tmp.add(tmp1);
                c++;
                
                if(tmp1.length() > 3 && (tmp1.substring(0,3).equals("End") || c == talkStorage.size())){
                    go = false;
                }
                
            }
            
            talkStorage1.add(tmp);
            c++;
           
        }
        startLine = new String[talkStorage1.size()];
        
       
        
        for(int i = 0; i < talkStorage1.size(); i++){
            startLine[i] = talkStorage1.get(i).get(1).substring(12);
            ArrayList<String[][]> tmp = new ArrayList<String[][]>();
            //int size = Integer.parseInt(talkStorage1.get(i).get(talkStorage1.size()-2).substring(8,9));
            //System.out.println(talkStorage1.get(i).get(talkStorage1.size()));
            //System.out.println(size);
            String[][] tmp1 = new String[4][4];//this is correct option for horse
            String[][] tmp2 = new String[4][4];//this is the wrong option for horse
            String[][] tmp3 = new String[4][4];//this is the correct option for enemy
            String[][] tmp4 = new String[4][4];//this is the wrong option for enemy
            int c1, c2, c3, c4, c5;
            c1 = c2 = c3 = c4 = c5 = 0;
            
            for(int j = 2; j < talkStorage1.get(i).size(); j++){
                String tmp5 = talkStorage1.get(i).get(j);
                if(tmp5.substring(0,1).equals("h")){
                    if(tmp5.substring(6,7).equals("T")){
                        tmp1[c5][c1] = tmp5.substring(11);
                        c1++;
                    }else{
                        tmp2[c5][c2] = tmp5.substring(11);
                        c2++;
                    }
                }else{
                    if(tmp5.substring(6,7).equals("T")){
                        tmp3[c5][c3] = tmp5.substring(11);
                        c3++;
                    }else{
                        tmp4[c5][c4] = tmp5.substring(11);
                        c4++;
                    }
                }
                if((j-2) % 8 == 0 && j != 2){
                    c1 = c2 = c3 = c4 = 0;
                    c5++;
                }
            }
            tmp.add(tmp1);
            tmp.add(tmp2);
            tmp.add(tmp3);
            tmp.add(tmp4);
            talkStorage2.add(tmp);
        }
    }
    
    /**
     * Set active state to true if player is already inside a world (ending from normal pony battle)
     * 
     * @param in    The new state of active.
     */
    public static void setActive(boolean in){
        active = in;
    }
    
    /**
     * Returns if the player is currently active in world.
     * 
     * @return boolean  Returns true only if player is not in spawn point.
     */
    public static boolean isActive(){
        return active;
    }
    
    /**
     * Sets the number of remaining memory orbs.
     * 
     * @param val   the updated number of memory orbs.
     */
    public static void setOrb(int val){
        orbReim = val;
    }
    
    /**
     * Returns the current number of remaining memory orbs.
     * 
     * @return int  the number of memory orbs remaining.
     */
    public static int getOrb(){
        return orbReim;
    }
    
    /**
     * returns the current HP of player.
     * 
     * @return int  The current HP of player
     */
    public static int getHP(){
        return HP;
    }
    
    /**
     * sets the HP of player to the given value
     * 
     * @param val   The HP value after changing
     */
    public static void setHP(int val){
        HP = val;
    }
    
    /**
     * returns the current MP of player.
     * 
     * @return int  The current MP of player
     */
    public static int getMP(){
        return MP;
    }
    
    /**
     * sets the MP of player to the given value
     * 
     * @param val   The MP value after changing
     */
    public static void setMP(int val){
        MP = val;
    }
    
    /**
     * sets the current level (map) of player. (1, 2, 3)
     * 
     * @param lv    The current level.
     */
    public static void setLevel(int lv){
        lV = lv;
    }
    
    /**
     * returns current level (1, 2, 3);
     * 
     * @return int  The current level
     */
    public static int getLevel(){
        return lV;
    }
    
    /**
     * sets the current coordinate of player
     * 
     * @param coord     The coordinate of player
     */
    public static void setPlayerCoords(int[] coord){
        ppX = coord[0];
        ppY = coord[1];
    }
    
    /**
     * returns player's X and Y coordinate on grid
     * 
     * @return int[2]  returns player's X and Y coordinate on grid, respectively
     */
    public static int[] getPlayerCoords(){
        return new int[]{ppX, ppY};
    }
    
    
    /**
     * below is changed by George
     */
    
    
}
