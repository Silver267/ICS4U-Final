import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * World that holds every battle (including talking.).
 * 
 * @author George && Ming && Xuanxi Jiang
 * @version (a version number or a date)
 */
public class SideWorld extends World
{
    private GreenfootImage backGround;
    
    /**
     * Constructor for objects of class SideWorld.
     * 
     */
    private GreenfootImage back;
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
    private GreenfootSound bgm;
    
    
    //new variable
    private boolean continueChoose, continueChooseLine;
    
    public SideWorld(int id)
    {
        super(1200, 675, 1, false); 
        this.id = id;
        
        //This will set the background of the world by looking at the level
        if (Statics.getLevel() == 1) {
            backGround = new GreenfootImage("images/BackGround/battle1.jpg");
        }
        if (Statics.getLevel() == 2) {
            backGround = new GreenfootImage("images/BackGround/battle2.jpg");
        }
        if (Statics.getLevel() == 3) {
            backGround = new GreenfootImage("images/BackGround/battle3.jpg");
        }
        if (Statics.getLevel() == 4) {
            backGround = new GreenfootImage("images/BackGround/battle4.png");
        }
        setBackground(backGround);
        
        setPaintOrder(Enemy.class);
        b1 = new Block(0);
        b2 = new Block(0);
        b3 = new Block(90);
        b4 = new Block(90);
        
        a = new Option(new GreenfootImage("A.png"), new GreenfootImage("A1.png"), false);
        b = new Option(new GreenfootImage("B.png"), new GreenfootImage("B1.png"), false);
        c = new Option(new GreenfootImage("C.png"), new GreenfootImage("C1.png"),false);
        d = new Option(new GreenfootImage("D.png"), new GreenfootImage("D1.png"),false);
        cf = new Option(new GreenfootImage("Continue.png"), new GreenfootImage("Continue1.png"),true);
        
        continueChooseLine = true;
        
        if(id == 0 && Statics.getLevel() != 4){
            //add hitbox and option to the world
            hitBox = new HitBox(false);
            addObject(hitBox, 500, 500);
            addObject(a, 150, 380);
            addObject(b, 150, 530);
            addObject(c, 1050, 380);
            addObject(d, 1050, 530);
            addObject(cf, 1050, 633);
            
            bgm = new GreenfootSound("bgm-boss-pony.mp3");
            
            addObject(new Label("Current HP: ", 25), 60, 200);
            addObject(new HPBar(true), 200, 200);
            
            
            addObject(new battleBox(), 600, 480);
            
            Statics.takeInWords();
            
            character = 8 + Statics.getLevel();
            conversation = Statics.getConversation().get(character); 
            rounds = 0;
            limit = Integer.parseInt(conversation.get(conversation.size()-2).substring(8,9)); 
            
            if(character == 9){
                addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 600), 600, 150);
            }else if(character == 10){
                addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
            }else if(character == 11){
                addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
            }
        }else if(Statics.getLevel() != 4){
            bgm = new GreenfootSound("bgm-normal-battle.mp3");
            addObject(new BattleScreen(), 600, 510);
            Statics.takeInWords();
            //use this code after, character = id*Statics.getLevel()-1;
            character = 0;
            //character = id+(Statics.getLevel()-1)*3-1;
            
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
            bgm = new GreenfootSound("bgm-boss-final.mp3");
            addObject(boss = new Boss(), 600, 200);
            addObject(new Label("Current HP: ", 25), 60, 200);
            addObject(new HPBar(true), 200, 200);
            addObject(new Label("Boss HP: ", 25), 60, 250);
            addObject(new HPBar(false), 200, 250);
            hitBox = new HitBox(true);
            addObject(new battleBox(), 600, 480);
            addObject(hitBox, 500, 500);
        }
        sayMore = true;
        music();
    }
    
    

    public void act(){
        if(id > 0){
            chooseLine();
            coniformeed();
            
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
        if(continueChoose){
            cf.setImage(cf.getImage(true));
            if(cf.isClick()){
                if(!done){
                    rounds++;
                }else if(done){
                    continueChooseLine = false;
                    a.setImage(a.getImage(false));
                    b.setImage(b.getImage(false));
                    c.setImage(c.getImage(false));
                    d.setImage(d.getImage(false));
                }
                removeObject(conversationCentre);
                conversationCentre = new Label(changeLine(toSay), 25);
                conversationCentre.setFillColor(Color.BLACK);
                addObject(conversationCentre, 600, 500);
                
                
            }
            if(id == 0){
                if(character == 9){
                    addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 600), 600, 150);
                }else if(character == 10){
                    addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
                }else if(character == 11){
                    addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
                }
            }else if(id > 0 && done){
                                
            }
            
            
        }else{
            cf.setImage(cf.getImage(false));
        }
        /*
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
                unMusic();
                Greenfoot.setWorld(new MainWorld());
            }
            if(cf.isClick() && successTime >= 2 && !done){
                Statics.setOrb(Statics.getOrb()-1);
                Statics.setStay(id); unMusic();
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
                if(character != 11){
                    unMusic();
                    Greenfoot.setWorld(new MainWorld());
                }else{
                    unMusic();
                    Greenfoot.setWorld(new SideWorld(0));
                }
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
        */
    }
    
    
    
    /**
     * This method will allow the player to choose the lines
     */
    public void chooseLine(){
        if(rounds < limit && continueChooseLine){
            if(a.isClick()){
                changePresent(2);
            }else if(b.isClick()){
                changePresent(3);
            }else if(c.isClick()){
                changePresent(4);
            }else if(d.isClick()){
                changePresent(5);
            }
        }
        
        
    }
    
    public void changePresent(int x){
        //change the presence of the middle sentence
        removeObject(conversationCentre);
        horseSay = changeLine(conversation.get(x + rounds*8));//x is 2 at the start
        conversationCentre = new Label(horseSay, 25);
        conversationCentre.setFillColor(Color.BLACK);            
        addObject(conversationCentre, 600, 500);
        toSay = conversation.get(x+4 + rounds*8);
        
        //check if the conversaqtion should end
        if(conversation.get(x + rounds*8).substring(6,7).equals("F")){
            done = true;
        }else{
            done = false;
        }
        
        //tell the cf can move now
        continueChoose = true;
    }
    
    public boolean getContinueChoose(){
        return continueChoose;
    }
    
    public boolean getContinueChooseLine(){
        return continueChooseLine;
    }
    
    public int getCountTime(){
        return countTime;
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
        music();
    }
    
    public void stopped(){
        unMusic();
    }
    
    /**
     * Don't play music.
     */
    public void unMusic(){
        bgm.stop();
    }
    
    /**
     * Play music.
     */
    private void music(){
        bgm.playLoop();
    }
    
    
    public void remAllBullets(){
        ArrayList<removableBullet> re = (ArrayList<removableBullet>)(getObjects(removableBullet.class));
        for(removableBullet x: re){
            removeObject(x);
        }
    }
}