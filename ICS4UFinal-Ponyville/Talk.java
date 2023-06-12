import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * Write a description of class Talk here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Talk extends Screen
{
    private ArrayList<String> talkStorage;
    private ArrayList<ArrayList<String>> talkStorage1;//talkStorage1 is to store each conversation one by one 
    private ArrayList<ArrayList<String[][]>> talkStorage2;//talekStorage2 is to store the conversation by the choice
    private String[] startLine;//this array will contain the first line of all the conversation
    private Scanner sc;
    private boolean moreLine;
    private int numberOfTalk;
    public Talk(){
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
        
        
        String check = talkStorage2.get(1).get(0)[0][0];
        System.out.println(check);
        
    }
    /**
     * Act - do whatever the Talk wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        // Add your action code here.
    }
}
