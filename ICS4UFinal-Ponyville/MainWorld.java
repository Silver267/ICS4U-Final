import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MainWorld here.
 * 
 * @author Xuanxi Jiang
 * @version (a version number or a date)
 */
public class MainWorld extends World{
    ShaderBox[][] sb;
    private boolean havePanel;
    private CoordMap mp;
    private int prevPlayerX, prevPlayerY;
    private LinkedList<int[]> prv = new LinkedList<int[]>();
    private MainCh chara;
    private static GreenfootSound bgm;
    
    public MainWorld(){
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false);
        //Delete this line when implementing multi-world
        Statics.setMP(100);
        Statics.setOrb(3);
        havePanel = false;
        sb = new ShaderBox[20][11];
        mp = new CoordMap(Statics.getLevel(), 20, 11, 1200, 675);
        chara = new MainCh();
        for(int i=0; i<20; i++){
            for(int j=0; j<11; j++){
                addObject(sb[i][j] = new ShaderBox(mp.getSz()[0], mp.getSz()[1]), mp.getPixes(new int[]{i, j})[0], mp.getPixes(new int[]{i, j})[1]);
                int nodeType = mp.getNode(new int[]{i, j}).getType();
                if(Statics.isActive())
                    addObject(chara, mp.getPixes(Statics.getPlayerCoords())[0], mp.getPixes(Statics.getPlayerCoords())[1]);
                if(!Statics.isActive() && nodeType==1)
                    addObject(chara, mp.getPixes(new int[]{i, j})[0], mp.getPixes(new int[]{i, j})[1]);
                else if(nodeType==2)
                    addObject(new Barrier(), mp.getPixes(new int[]{i, j})[0], mp.getPixes(new int[]{i, j})[1]);
                else if(nodeType>=3 && Statics.getStay(nodeType-3)==0)
                    addObject(new touchEquip(mp.getNode(new int[]{i, j}).getType()-3), mp.getPixes(new int[]{i, j})[0], mp.getPixes(new int[]{i, j})[1]);
            }
        }
        Statics.setActive(true);
        /**
         * BGM thoughts:
         * 1 - Nectar Meadow (Pokemon SMD Music 037
         * 2 - Sun/Moon Disc 3 Music 37
         * 3 - XY Disc 2 Music 25
         * Boss Battle: The Bitter Cold Stage 2
         * Final Boss: Battle (Ultra Necrozma)
         * Talk: An Eternal Prison
         */
        bgm = new GreenfootSound("bgm-l-"+Statics.getLevel()+".mp3");
        bgm.stop(); bgm.setVolume(70);
        addObject(new Panel(), 1200/2, (getMap().getSz()[1]+4)/2);
        setBackground("BackGround/"+Statics.getLevel()+".jpg");
        addObject(new ProgressBar(0), 160, 32);
        addObject(new ProgressBar(1), 500, 32);
        setPaintOrder(Label.class, floatingPanel.class, ProgressBar.class, Panel.class, ShaderBox.class, MainCh.class, Barrier.class, touchEquip.class);
        update(); music();
    }
    
    public void goBattle(int id){
        //todo
        unMusic();
        SideWorld batle = new SideWorld(id);
        Statics.setOrb(Statics.getOrb()-1);
        Greenfoot.setWorld(batle);
    }
    
    public CoordMap getMap(){
        return mp;
    }
    
    public void act(){
        if(chara.isMoving() || chara.getMagic()>0)
            update();
    }
    
    public void started(){
        music();
    }
    
    public void stopped(){
        unMusic();
    }
    
    private void unMusic(){
        bgm.stop();
    }
    
    private void music(){
        bgm.playLoop();
    }
    
    public void damage(){
        ArrayList<Chaser> enemies = (ArrayList<Chaser>)getObjects(Chaser.class);
        for(Chaser c:enemies){
            if(SparkleEngine.ManhattenDistance(c.where(), Statics.getPlayerCoords())<=2){
                c.damage();
                if(c.getHP()<=0)
                    removeObject(c);
            }
        }
    }
    
    public void action(int[] plCoord){
        ArrayList<Chaser> enemies = (ArrayList<Chaser>)getObjects(Chaser.class);
        for(Chaser c:enemies)
            c.action(plCoord);
        spawnChaser();
    }
    
    private void spawnChaser(){
        int spawnRate = Statics.getPlayerCoords()[0];
        double rate = (1.0-(spawnRate*0.01+0.2))*100;
        if(Greenfoot.getRandomNumber(100)>rate){
            int dec = Greenfoot.getRandomNumber(2);
            if(Statics.getPlayerCoords()[0]==19 || Statics.getPlayerCoords()[0]==0 || Statics.getPlayerCoords()[1]==1 || Statics.getPlayerCoords()[1]==10)
                return;
            if(Greenfoot.getRandomNumber(2)==0){
                int tmp = Greenfoot.getRandomNumber(20);
                while(mp.getNode(new int[]{tmp, dec==0 ? 1:10}).getType()!=0)
                    tmp = Greenfoot.getRandomNumber(20);
                addObject(new Chaser(), mp.getPixes(new int[]{tmp, dec==0 ? 0:10})[0], mp.getPixes(new int[]{tmp, dec==0 ? 0:10})[1]);
            }else{
                int tmp = Greenfoot.getRandomNumber(11);
                while(mp.getNode(new int[]{dec==0 ? 0:19, tmp}).getType()!=0)
                    tmp = Greenfoot.getRandomNumber(11);
                addObject(new Chaser(), mp.getPixes(new int[]{dec==0 ? 0:19, tmp})[0], mp.getPixes(new int[]{dec==0 ? 0:19, tmp})[1]);
            }
        }
    }
    
    private void update(){
        for(int[] i:prv)
            sb[i[0]][i[1]].iluminate(0);
        prv.clear();
        ArrayList<Chaser> enemies = (ArrayList<Chaser>)getObjects(Chaser.class);
        for(Chaser c:enemies){
            sb[c.where()[0]][c.where()[1]].iluminate(10);
            prv.add(c.where());
        }
        int[] currCoords = Statics.getPlayerCoords();
        for(int i=-2; i<=2; i++){
            for(int j=-2; j<=2; j++){
                int[] tmp = {currCoords[0]+i, currCoords[1]+j};
                if(tmp[0]>=0 && tmp[1]>=0 && tmp[0]<20 && tmp[1]<11){
                    if(SparkleEngine.ManhattenDistance(tmp, currCoords)<=2){
                        prv.add(tmp);
                        int normBright = 100-(SparkleEngine.ManhattenDistance(tmp, Statics.getPlayerCoords()))*33;
                        sb[tmp[0]][tmp[1]].iluminate(Math.max(normBright, chara.getMagic()));
                    }
                    if(SparkleEngine.ManhattenDistance(tmp, currCoords)==0){
                        if(havePanel && mp.getNode(currCoords).getType()<=2){
                            havePanel = false;
                        }else if(!havePanel && mp.getNode(currCoords).getType()>2 && Statics.getStay(mp.getNode(currCoords).getType()-3)==0){
                            havePanel = true;
                            addObject(new floatingPanel(currCoords), mp.getPixes(currCoords)[0], mp.getPixes(currCoords)[1]);
                        }
                    }
                }
            }
        }
    }
}
