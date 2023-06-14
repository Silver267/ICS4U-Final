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
    private Label conversationCentre;//This label will show the conversation
    private boolean done, sayIt, sayMore;//This boolean will check if the pony fail to talk heal the enemy, sayIt controls when the enemy will response, sayMore controls when can the character continue speak
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
            character = id*3-1;
            conversation = Statics.getConversation().get(character);
            GreenfootImage lines = new GreenfootImage(300, 200);
            a = new OptionA();
            b = new OptionB();
            c = new OptionC();
            d = new OptionD();
            cf = new Coniform();
            addObject(a, 150, 400);
            addObject(b, 150, 550);
            addObject(c, 1050, 400);
            addObject(d, 1050, 550);
            addObject(cf, 1050, 650);
            String tmp = changeLine(conversation.get(1));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            rounds = 0;
            if(character == 0){
                character1 = new GreenfootImage("BrightMac/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 1){
                character1 = new GreenfootImage("PearButter/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 2){
                character1 = new GreenfootImage("GrannySmith/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 3){
                character1 = new GreenfootImage("Scootaloo/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 4){
                character1 = new GreenfootImage("SweetieBelle/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 5){
                character1 = new GreenfootImage("AppleBloom/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 6){
                character1 = new GreenfootImage("Palette/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 7){
                character1 = new GreenfootImage("Trixie/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }else if(character == 8){
                character1 = new GreenfootImage("LusterDawn/tile000.png");
                addObject(new Pony(character1), 600, 100);
            }
            
        }
        sayMore = true;
        
    }
    
    public void act(){
        if(id > 0){
            if(sayMore){
                chooseLine();
            }
            
            if(cf.isClick()){
                sayIt = true;
                sayMore = true;
            }
            if(sayIt){
                keepSpeak();
                sayIt = false;
            }
        }
        
    }
    
    
    public void chooseLine(){
        if(a.isClick()){
            String tmp = changeLine(conversation.get(2 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            removeObject(conversationCentre);
            addObject(conversationCentre, 600, 400);
            toSay = conversation.get(6 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
            rounds++;
            sayMore = false;
        }else if(b.isClick()){
            String tmp = changeLine(conversation.get(3 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            toSay = conversation.get(7 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
            rounds++;
            sayMore = false;
        }else if(c.isClick()){
            String tmp = changeLine(conversation.get(4 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            toSay = conversation.get(8 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
            rounds++;
            sayMore = false;
        }else if(d.isClick()){
            String tmp = changeLine(conversation.get(5 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            toSay = conversation.get(9 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }
            rounds++;
            sayMore = false;
        }
        
    }
    
    public void keepSpeak(){
        removeObject(conversationCentre);
        String tmp = changeLine(toSay);
        conversationCentre = new Label(tmp, 25);
        conversationCentre.setFillColor(Color.BLACK);
        addObject(conversationCentre, 600, 400);
        if(done){
            //write some code to send the pny back
        }
    }
    
    public String changeLine(String txt){
        String tmp = "";
        int num = txt.length()/65;
        for(int i = 0; i <= num; i++){
            tmp = tmp + txt.substring(Math.min(12 + i*65, txt.length()-1), Math.min(12 + (i+1)*65, txt.length()-1)) + "\n";
        }
        return tmp;
    }
    
    public void ponyTalk(){
        GreenfootImage start = new GreenfootImage(594, 360);
    }
}