import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class SideWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SideWorld extends World
{
    GreenfootImage backGround;
    
    /**
     * Constructor for objects of class SideWorld.
     * 
     */
    private GreenfootImage back;
    private BattleBox[][] box;
    private int id;
    private Boss boss;
    private HitBox hitBox;
    private ArrayList<String> conversation;
    private int rounds, character;
    private GreenfootImage character1;
    private OptionA a;
    private OptionB b;
    private OptionC c;
    private OptionD d;
    private Coniform cf;
    private String toSay;//This string will hold what the enmy will say next
    private boolean done, sayIt;//This boolean will check if the pony fail to talk heal the enemy, sayIt controls when the enemy will response
    public SideWorld(int id)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false); 
        this.id = id;
        //id = 0, level boss, id = 1, 2, or 3, typical little pony, just talk heal. If static.getLevl = 1, level 1 enemies, 
        box = new BattleBox[9][9];
        //start at x = 303, y = 675, chnage is x = 66, y =40
        if (id == 0) {
            for(int i = 0; i < 9; i++){
                for(int j  = 0; j < 9; j++){
                    box[i][j] = new BattleBox();
                    addObject(box[i][j], 336 + i*66, 655 - j* 40);
                }
            }
        }
        
        if (Statics.getLevel() == 1) {
            backGround = new GreenfootImage("images/BackGround/battle1.jpg");
        }
        if (Statics.getLevel() == 2) {
            backGround = new GreenfootImage("images/BackGround/battle2.jpg");
        }
        if (Statics.getLevel() == 3) {
            backGround = new GreenfootImage("images/BackGround/battle3.jpg");
        }
        
        setBackground(backGround);
        
        addObject(new BattleScreen(), 600, 510);
        
        if(id == 0){
            boss = new Boss();
            addObject(boss, 600, 150);
            hitBox = new HitBox();
            addObject(hitBox, 400, 400);
        }else{
            Statics.takeInWords();
            //use this code after, character = id*Statics.getLevel()-1;
            character = id*1-1;
            conversation = Statics.getConversation().get(character);
            GreenfootImage lines = new GreenfootImage(300, 200);
            a = new OptionA();
            b = new OptionB();
            c = new OptionC();
            d = new OptionD();
            cf = new Coniform();
            addObject(a, 150, 300);
            addObject(b, 150, 500);
            addObject(c, 1050, 300);
            addObject(d, 1050, 500);
            addObject(cf, 1050, 600);
            changeLine(conversation.get(1));
            rounds = 0;
            if(character == 0){
                character1 = new GreenfootImage("BrightMac/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }
            
        }
        
        
    }
    
    public void act(){
        chooseLine();
        if(cf.isClick()){
            sayIt = true;
        }
        if(sayIt){
            keepSpeak();
            sayIt = false;
        }
    }
    
    public void addedToWorld(World w){
        MouseInfo mouse = Greenfoot.getMouseInfo();
    }
    
    public void chooseLine(){
        if(a.isClick()){
            changeLine(conversation.get(2 + rounds*8));
            rounds++;
            toSay = conversation.get(6 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
        }else if(b.isClick()){
            changeLine(conversation.get(3 + rounds*8));
            rounds++;
            toSay = conversation.get(7 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
        }else if(c.isClick()){
            changeLine(conversation.get(4 + rounds*8));
            rounds++;
            toSay = conversation.get(8 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
        }else if(d.isClick()){
            changeLine(conversation.get(5 + rounds*8));
            rounds++;
            toSay = conversation.get(9 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
        }
        
    }
    
    public void keepSpeak(){
        changeLine(toSay);
        if(done){
            //write some code to send the pny back
        }
    }
    
    public void changeLine(String txt){
        int num = txt.length()/80;
        for(int i = 0; i <= num; i++){
            showText(txt.substring(Math.min(12 + i*80, txt.length()-1), Math.min(12 + (i+1)*80, txt.length()-1)), 600, 400+i*40);
        }
    }
    
    public void ponyTalk(){
        GreenfootImage start = new GreenfootImage(594, 360);
    }
}