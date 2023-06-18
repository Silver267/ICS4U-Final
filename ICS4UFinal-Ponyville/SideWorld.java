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
    private String toSay, horseSay;//This string will hold what the enmy will say next, the horseSay will hold what the horse maysay next
    private Label conversationCentre;//This label will show the conversation
    private boolean keepCount, remove;//This boolean will check if the time should keep count, remove will tell if the four oprions are removed
    private boolean done, sayIt, sayMore, start, change;//This boolean will check if the pony fail to talk heal the enemy, sayIt controls when the enemy will response, sayMore controls when can the character continue speak, start will tell the program to enable the continue button
    private GreenfootSound bgm;
    
    
    //new variable
    private boolean continueChoose, continueChooseLine, startCount;
    private int countClick, speakFirst;
    private boolean clickOnce; //This boolean is used to check if the continue should keep + rounds
    
    public SideWorld(int id)
    {
        super(1200, 675, 1, false); 
        this.id = id;
        
        backGround = new GreenfootImage("images/BackGround/battle"+Statics.getLevel()+".jpg");
        
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
            continueChooseLine = false;
            
            character = 8 + Statics.getLevel();
            
            conversation = Statics.getConversation().get(character); 
            rounds = 0;
            limit = Integer.parseInt(conversation.get(conversation.size()-2).substring(8,9)); 
            
            if(character == 9){
                addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 200), 600, 150);
            }else if(character == 10){
                addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
            }else if(character == 11){
                addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
            }
        }else if(Statics.getLevel() != 4){
            bgm = new GreenfootSound("bgm-normal-battle.mp3");
            addObject(new BattleScreen(), 600, 510);
            Statics.takeInWords();
           
            
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
            clickOnce = true;
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
        if(id == 0){
            if(speakFirst == 1){
                String tmp = Statics.getStartLine()[character];
                conversationCentre = new Label(changeLine(tmp), 25);
                addObject(conversationCentre, 600, 500);
                speakFirst++;
                
            }
        }
        chooseLine();
        if(id > 0){
            coniformeed();
        }else if(id == 0){
            coniform();
        }
        
        
        
        
    }
    
    /**
     * This method will allow the player to coniform the line they continue with
     */
    private void coniformeed(){
        if(continueChoose){
            
            if(cf.isClick()){
                if(!done){
                    rounds++;
                    
                }else if(done){
                    
                    if(id > 0){
                        startCount = true;
                        continueChooseLine = false;
                    }
                    
                }
                removeObject(conversationCentre);
                conversationCentre = new Label(changeLine(toSay), 25);
                conversationCentre.setFillColor(Color.BLACK);
                addObject(conversationCentre, 600, 500);
                
                if(done && countClick >= 2){
                    //unMusic()
                    Greenfoot.setWorld(new MainWorld());                
                }
                
                if(startCount){
                    countClick++;
                }
                
                if(rounds >= limit){
                    continueChooseLine = false;
                    if(id > 0){
                        startCount = true;
                        if(countClick > 2){
                            Statics.setStay(id); Statics.setOrb(Statics.getOrb()-1);
                            //unMusic()
                            Greenfoot.setWorld(new MainWorld());  
                        }
                    }
                }else if(!done){
                    continueChoose = false;
                }
                
                
            }
        }
    }
    
    public void coniform(){
        if(continueChoose && cf.isClick()){
            startCount = true;
            if(startCount){
                countClick++;
            }
            if(!done && clickOnce){
                rounds++;
                Fluttershy.countplus();
                PinkiePie.countplus();
                TwilightSparkle.countplus();
                clickOnce = false;
            }
            //below will reset the first line if the player failed at the first round
            if(speakFirst == 2 && done){
                speakFirst = 0;
                
            }
            removeObject(conversationCentre);
            conversationCentre = new Label(changeLine(toSay), 25);
            conversationCentre.setFillColor(Color.BLACK);
            addObject(conversationCentre, 600, 500);
            continueChooseLine = false;
            if(countClick == 2){
                countClick = 0;
                removeObject(conversationCentre);
                startCount = false;
                continueChooseLine = false;
                continueChoose = false;
                if(character == 9){
                    addObject(new Fluttershy(Greenfoot.getRandomNumber(2)+1, 600), 600, 150);
                }else if(character == 10){
                    addObject(new PinkiePie(Greenfoot.getRandomNumber(2)+1, 800), 600, 150);
                }else if(character == 11){
                    addObject(new TwilightSparkle(Greenfoot.getRandomNumber(2)+1, 1200), 600, 150);
                }
                if(rounds >= limit){
                    Statics.setLevel(Statics.getLevel()+1);
                    Statics.rsetStay(); Statics.setOrb(3);
                    Statics.setHP(40); Statics.setActive(false);
                    if(Statics.getLevel() == 4){
                        Greenfoot.setWorld(new SideWorld(0));  
                    }else{
                    Greenfoot.setWorld(new MainWorld());  
                }
                }
            }
        
        
        }
    }
  
    
    /**
     * This method will allow the player to choose the lines
     */
    private void chooseLine(){
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
    
    private void changePresent(int x){
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
        clickOnce = true;

        
    }
    
    public void setSpeakFirst(){
        speakFirst++;
    }
    
    public void setContinueChooseLine(boolean x){
        continueChooseLine = x;
    }
    
    public void setContinueChoose(boolean x){
        continueChoose = x;
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
    
    
    
    private String changeLine(String txt){
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
    
    public void setConversation(){
        conversationCentre = new Label(changeLine(toSay), 25);
        conversationCentre.setFillColor(Color.BLACK);
        addObject(conversationCentre, 600, 500);
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