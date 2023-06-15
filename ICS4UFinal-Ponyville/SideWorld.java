import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class SideWorld here.
 * Bug List:
 *  If user pressed continue button without selecting anything, program will error.
 *  After pausing world / Resetting world, Statics.takeInWords() will error
 *  If exceed conversation limit, program will error (fixable by kicking player back to map)
 *  Need to somehow display which button corresponds to what respond.
 *  Make it look better.
 *  Currently buttons means preview - make button able to select other text
 *  Kick player back to world
 *  According to planning:
 *      Except for final boss, all other danmu is by turns:
 *          Boss releases danmu for few seconds
 *          Go to talk to boss (one exchange dialogue)
 *          Boss continue release danmu for few seconds
 *          etc.
 *      Until all correct talks are slected, then proceed to next level.
 *  Include BGM:
 *      bgm-normal-battle for normal pony (talk only)
 *      bgm-boss-pony for pony boss (danmu + talk)
 *      bgm-boss-final for final boss (danmu)
 * 
 * @author George && Ming
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
    private GreenfootSound talkOnly, damnAndTalk, justDamn;
    private BattleBox[][] box;
    private int id, time, limit;//time is used to find the length of time the player get kick out, limit is the limit to number of rounds
    private Boss boss;
    private HitBox hitBox;
    private ArrayList<String> conversation;
    private int rounds, character, countTime;//countTime is used to count the time in current game
    private GreenfootImage character1;
    private Option a, b, c, d, cf;
    private String toSay, horseSay;//This string will hold what the enmy will say next, the horseSay will hold what the horse maysay next
    private Label conversationCentre;//This label will show the conversation
    private boolean keepCount, remove;//This boolean will check if the time should keep count, remove will tell if the four oprions are removed
    private boolean done, sayIt, sayMore, start, change;//This boolean will check if the pony fail to talk heal the enemy, sayIt controls when the enemy will response, sayMore controls when can the character continue speak, start will tell the program to enable the continue button
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
        
        a = new Option(new GreenfootImage("A.png"), new GreenfootImage("A1.png"), false);
        b = new Option(new GreenfootImage("B.png"), new GreenfootImage("B1.png"), false);
        c = new Option(new GreenfootImage("C.png"), new GreenfootImage("C1.png"),false);
        d = new Option(new GreenfootImage("D.png"), new GreenfootImage("D1.png"),false);
        cf = new Option(new GreenfootImage("Continue.png"), new GreenfootImage("Continue1.png"),true);
        
        if(id == 0){
            boss = new Boss();
            addObject(boss, 600, 150);
            hitBox = new HitBox();
            addObject(hitBox, 400, 400);
            addObject(a, 150, 380);
            addObject(b, 150, 530);
            addObject(c, 1050, 380);
            addObject(d, 1050, 530);
            addObject(cf, 1050, 633);
            keepCount = true;
        }else{
            talkOnly = new GreenfootSound("bgm-normal-battle.mp3");
            Statics.takeInWords();
            //use this code after, character = id*Statics.getLevel()-1;
            character = id*Statics.getLevel()-1;
            //character = id*3-1;
            conversation = Statics.getConversation().get(character);
           
            GreenfootImage lines = new GreenfootImage(300, 200);
            limit = Integer.parseInt(conversation.get(conversation.size()-2).substring(8,9));
            addObject(a, 150, 380);
            addObject(b, 150, 530);
            addObject(c, 1050, 380);
            addObject(d, 1050, 530);
            addObject(cf, 1050, 633);
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
    
    public HitBox getHitBox(){
        return hitBox;
    }
    
    public void started(){
        if(id == 0){
            
        }else{
            talkOnly.playLoop();
        }
        
    }
    
    public void stopped(){
        if(id == 0){
            
        }else{
            talkOnly.pause();
        }
        
    }
    
    public void act(){
        if(id > 0){
            chooseLine();
            if(start){
                coniformeed();
            }
            
            
        }else if(id == 0){
            if(keepCount){
                countTime++;
            }
            if(countTime > 600){
                keepCount = false;
            }
        }
        
    }
    
    /**
     * This method will allow the player to coniform the line they continue with
     */
    public void coniformeed(){
        if(cf.isClick() && change){
            rounds++;
            removeObject(conversationCentre);
            String tmp = changeLine(toSay);
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            change = false;
            if(id == 0){
                keepCount = true;
            }
            
        }
        if(cf.isClick() && done){
            System.out.println(Statics.getLevel());
            Greenfoot.setWorld(new MainWorld());
        }
    }
    
    public int getCountTime(){
        return countTime;
    }
    
    /**
     * This method will allow the player to choose the lines
     */
    public void chooseLine(){
        if(a.isClick() && rounds < limit ){
            start = true;
            removeObject(conversationCentre);
            String tmp = changeLine(conversation.get(2 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            removeObject(conversationCentre);
            addObject(conversationCentre, 600, 400);
            horseSay = conversation.get(2 + rounds*8);
            toSay = conversation.get(6 + rounds*8);
            System.out.println(conversation.get(2 + rounds*8).substring(6,7).equals("F"));
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }else{
                done = false;
            }
            change = true;

        }else if(b.isClick() && rounds < limit ){
            start = true;
            removeObject(conversationCentre);
            String tmp = changeLine(conversation.get(3 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            horseSay = conversation.get(3 + rounds*8);
            toSay = conversation.get(7 + rounds*8);
            System.out.println(conversation.get(3 + rounds*8).substring(6,7).equals("F"));
            if(conversation.get(3 + rounds*8).substring(6,7).equals("F")){
                done = true;
                
            }else{
                done = false;
            }
            change = true;
        }else if(c.isClick() && rounds < limit){
            start = true;
            removeObject(conversationCentre);
            /**FIX THIS: kick the player back**/
            String tmp = changeLine(conversation.get(4 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            horseSay = conversation.get(4 + rounds*8);
            toSay = conversation.get(8 + rounds*8);
            System.out.println(conversation.get(4 + rounds*8).substring(6,7).equals("F"));
            if(conversation.get(4 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }else{
                done = false;
            }
            
            change = true;
        }else if(d.isClick() && rounds < limit){
            start = true;
            removeObject(conversationCentre);
            String tmp = changeLine(conversation.get(5 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 400);
            horseSay = conversation.get(5 + rounds*8);
            toSay = conversation.get(9 + rounds*8);
            System.out.println(conversation.get(5 + rounds*8).substring(6,7));
            if(conversation.get(5 + rounds*8).substring(6,7).equals("F")){
                done = true;
            }else{
                done = false;
            }
            change = true;
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