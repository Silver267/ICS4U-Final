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
    private ArrayList<ArrayList<String>> talkStorage1;
    private Scanner sc;
    private boolean moreLine;
    private int numberOfTalk;
    public Talk(){
        talkStorage = new ArrayList<String>();
        
        try{
            sc = new Scanner(new File("Story.txt"));
        }catch(FileNotFoundException e){
                
        }
        
        while(!moreLine){
            try{
                talkStorage.add(sc.nextLine());
            }catch (NoSuchElementException e){
                moreLine = true;
            }
        }
        
        
        for(int i = 0; i < talkStorage.size(); i++){
            String tmp = talkStorage.get(i);
            if(tmp.length() > 5 && tmp.substring(0,5).equals("Start")){
                numberOfTalk++;
            }
        }
        for(String x: talkStorage){
            System.out.println(x);
        }
        talkStorage1 = new ArrayList<ArrayList<String>>();
        int c = 0;
        //System.out.println(talkStorage.get(0));
        
        for(int i = 0; i < numberOfTalk; i++){
            boolean go = true;
            ArrayList<String> tmp = new ArrayList<String>();
            while(go){
                String tmp1 = talkStorage.get(c);
                tmp.add(tmp1);
                c++;
                System.out.println(c);
                
                if(tmp1.length() > 3 && (tmp1.substring(0,3).equals("End") || c == talkStorage.size())){
                    //System.out.println(i);
                    go = false;
                }
                
                
                
               
            }
            
           
        }
        
        
        /*
        for(ArrayList<String> x: talkStorage1){
            for(int i = 0; i < 19; i++){
                System.out.println(x.get(i));
            }
        }
        */
        
        
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
