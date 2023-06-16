import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class SideWorld here.
 * Bug List:
 *  Implement turn-based danmu
 *  Implement many danmu
 *  BGM (should be easy)
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
    private int successTime;
    private boolean checkSuccess;
    private Boss boss;
    private boolean talk, needAdd;//needAdd will determine if an new pony is needed
    private boolean keepSpeak;
    private HitBox hitBox;
    private String continue1;//continue1 will store the sentence the pony didn;t finish
    private Block b1,b2,b3,b4;
    private ArrayList<String> conversation;
    private int rounds, character, countTime, wrongTime;//countTime is used to count the time in current game, wrongTime counts the number of time the player hit the continue after they select the wrong choice
    private GreenfootImage character1;
    private Option a, b, c, d, cf;
    private int first, rounds1;
    private String toSay, horseSay;//This string will hold what the enmy will say next, the horseSay will hold what the horse maysay next
    private Label conversationCentre;//This label will show the conversation
    private boolean startCount;//This will check whther the system can start count the number of times the continute is hit
    private boolean keepCount, remove;//This boolean will check if the time should keep count, remove will tell if the four oprions are removed
    private boolean done, sayIt, sayMore, start, change;//This boolean will check if the pony fail to talk heal the enemy, sayIt controls when the enemy will response, sayMore controls when can the character continue speak, start will tell the program to enable the continue button
    public SideWorld(int id)
    {    
        // Create a new world with 600x500 cells with a cell size of 1x1 pixels.
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
        
        setPaintOrder(Enemy.class);
        b1 = new Block(0);
        b2 = new Block(0);
        b3 = new Block(90);
        b4 = new Block(90);
        /*
        addObject(b1, 600, 800);
        addObject(b2, 600, -100);
        addObject(b3, -100, 300);
        addObject(b4, 1300, 300);
        */
        a = new Option(new GreenfootImage("A.png"), new GreenfootImage("A1.png"), false);
        b = new Option(new GreenfootImage("B.png"), new GreenfootImage("B1.png"), false);
        c = new Option(new GreenfootImage("C.png"), new GreenfootImage("C1.png"),false);
        d = new Option(new GreenfootImage("D.png"), new GreenfootImage("D1.png"),false);
        cf = new Option(new GreenfootImage("Continue.png"), new GreenfootImage("Continue1.png"),true);
        
        
        
       
        
        if(id == 0 && Statics.getLevel() != 4){// add later
            hitBox = new HitBox(false);
            addObject(hitBox, 500, 500);
            addObject(a, 150, 380);
            addObject(b, 150, 530);
            addObject(c, 1050, 380);
            addObject(d, 1050, 530);
            addObject(cf, 1050, 633);
            keepCount = true;
            
            addObject(new HPBar(true), 200, 200);
            //addObject(new HPBar(false), 200, 400);
            
            
            Statics.takeInWords();
            System.out.println(character);
            
            character = 8 + Statics.getLevel();
            conversation = Statics.getConversation().get(character); //use after
            rounds = 0;
            limit = Integer.parseInt(conversation.get(conversation.size()-2).substring(8,9)); 
            System.out.println(character);
            
            if(character == 9){
                addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 600), 600, 150);
            }else if(character == 10){
                addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
            }else if(character == 11){
                addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
            }
            
            
            
            
            
            
        }else if(Statics.getLevel() != 4){
            addObject(new BattleScreen(), 600, 510);
            talkOnly = new GreenfootSound("bgm-normal-battle.mp3");
            Statics.takeInWords();
            //use this code after, character = id*Statics.getLevel()-1;
            //character = 8;
            character = id+(Statics.getLevel()-1)*3-1;
            
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
            addObject(conversationCentre, 600, 500);
            rounds = 0;
            if(character == 0){
                character1 = new GreenfootImage("BrightMac/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 1){
                character1 = new GreenfootImage("PearButter/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 2){
                character1 = new GreenfootImage("GrannySmith/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 3){
                character1 = new GreenfootImage("Scootaloo/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 4){
                character1 = new GreenfootImage("SweetieBelle/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 5){
                character1 = new GreenfootImage("AppleBloom/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 6){
                character1 = new GreenfootImage("Palette/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 7){
                character1 = new GreenfootImage("Trixie/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }else if(character == 8){
                character1 = new GreenfootImage("LusterDawn/tile000.png");
                addObject(new Pony(character1), 600, 200);
            }
            
        }else{
            addObject(boss = new Boss(2), 600, 200);
            addObject(new HPBar(true), 200, 200);
            addObject(new HPBar(false), 200, 400);
            hitBox = new HitBox(true);
            addObject(new BattleScreen(), 600, 510);
            addObject(hitBox, 500, 500);
        }
        sayMore = true;
        
    }
    
    public void changeTalk(boolean x){
        talk = x;
    }
    
    public HitBox getHitBox(){
        return hitBox;
    }
    
    public Boss getBoss(){
        return boss;
    }
    
    public void started(){
        if(id == 0){
            
        }else{
            talkOnly.playLoop();
        }
        
    }
    
    public void remAllBullets(){
        ArrayList<removableBullet> re = (ArrayList<removableBullet>)(getObjects(removableBullet.class));
        for(removableBullet x: re){
            removeObject(x);
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
            if(talk){
                if(first == 0){
                    String tmp = Statics.getStartLine()[character];
                    conversationCentre = new Label(changeLine(tmp), 25);
                    addObject(conversationCentre, 600, 500);
                    first = 2;
                }
                if(rounds == 1 && !keepSpeak){
                    continue1 = changeLine(toSay);
                    conversationCentre = new Label(continue1, 25);
                    conversationCentre.setFillColor(Color.BLACK);
                    addObject(conversationCentre, 600, 500);
                    keepSpeak = true;
                }
                if(rounds == 2 && !keepSpeak){
                    keepSpeak = true;
                    continue1 = changeLine(toSay);
                    conversationCentre = new Label(continue1, 25);
                    conversationCentre.setFillColor(Color.BLACK);
                    addObject(conversationCentre, 600, 500);
                }
                chooseLine();
                coniformeed();
            }
            
        }
        
    }
    
    
    
    /**
     * This method will allow the player to coniform the line they continue with
     */
    public void coniformeed(){
        if(cf.isClick() && change){
            if(id > 0){
                rounds++;
            }
            
            if(id == 0){
                if(!done){
                    rounds++;
                }
            }
            removeObject(conversationCentre);
            if(id > 0){
                continue1 = changeLine(toSay);
                conversationCentre = new Label(continue1, 25);
                conversationCentre.setFillColor(Color.BLACK);
                addObject(conversationCentre, 600, 500);
            }
            change = false;
            if(id == 0){
                if(done){
                    wrongTime++;
                }
            }
            if(rounds == limit){
                checkSuccess = true;
            }
            needAdd = true;
            keepSpeak = false;
        }
        
        if(id > 0){
            if(cf.isClick() && checkSuccess){
                successTime++;
            }
            if(cf.isClick() && startCount){
                wrongTime++;
            }
            if(cf.isClick() && done && wrongTime == 2){
                Greenfoot.setWorld(new MainWorld());
            }
            if(cf.isClick() && successTime >= 2 && !done){
                Statics.setOrb(Statics.getOrb()-1);
                Statics.setStay(id);
                Greenfoot.setWorld(new MainWorld());
            }
        }
        if(id == 0){
            
            if(rounds == 1 && needAdd){
                removeObject(conversationCentre);
                if(character == 9){
                    addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 600), 600, 150);
                }else if(character == 10){
                    addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
                }else if(character == 11){
                    addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
                }
                
                wrongTime = 0;
                done = false;
                needAdd = false;
                talk = false;
            }
            if(cf.isClick() && checkSuccess){
                successTime++;
            }
            if(cf.isClick() && successTime >= 2 && !done){
                talk = false;
                removeObject(conversationCentre);
                wrongTime = 0;
                done = false;
                Statics.setLevel(Statics.getLevel()+1);
                Statics.rsetStay();
                Statics.setOrb(3);
                Statics.setHP(40);
                Statics.setActive(false);
                if(character != 11)
                    Greenfoot.setWorld(new MainWorld());
                else
                    Greenfoot.setWorld(new SideWorld(0));
                    
                    //switch to final battle
            }
            if(wrongTime == 2 && done && cf.isClick()){
                talk = false;
                removeObject(conversationCentre);
                if(character == 9){
                    addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 600), 600, 150);
                }else if(character == 10){
                    addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
                }else if(character == 11){
                    addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
                }
                if(first == 2){
                    first = 0;
                }
                wrongTime = 0;
                done = false;
            }
            
            if(cf.isClick() && done){
                wrongTime++;
            }
            
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
            addObject(conversationCentre, 600, 500);
            horseSay = conversation.get(2 + rounds*8);
            toSay = conversation.get(6 + rounds*8);
            if(conversation.get(2 + rounds*8).substring(6,7).equals("F")){
                done = true;
                startCount = true;
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
            addObject(conversationCentre, 600, 500);
            horseSay = conversation.get(3 + rounds*8);
            toSay = conversation.get(7 + rounds*8);
            if(conversation.get(3 + rounds*8).substring(6,7).equals("F")){
                done = true;
                startCount = true;
            }else{
                done = false;
            }
            change = true;
        }else if(c.isClick() && rounds < limit){
            start = true;
            removeObject(conversationCentre);
            String tmp = changeLine(conversation.get(4 + rounds*8));
            conversationCentre = new Label(tmp, 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 500);
            horseSay = conversation.get(4 + rounds*8);
            toSay = conversation.get(8 + rounds*8);
            if(conversation.get(4 + rounds*8).substring(6,7).equals("F")){
                done = true;
                startCount = true;
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
            addObject(conversationCentre, 600, 500);
            horseSay = conversation.get(5 + rounds*8);
            toSay = conversation.get(9 + rounds*8);
            if(conversation.get(5 + rounds*8).substring(6,7).equals("F")){
                done = true;
                startCount = true;
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
        addObject(conversationCentre, 600, 500);
        if(done){
            //write some code to send the pny back
        }
    }
    
    public String changeLine(String txt){
        return SparkleEngine.wordWrap(txt.substring(12, txt.length()-1), new Font(25), 500);
    }
    
    public void ponyTalk(){
        GreenfootImage start = new GreenfootImage(594, 360);
    }
}