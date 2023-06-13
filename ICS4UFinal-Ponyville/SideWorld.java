import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SideWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SideWorld extends World
{

    /**
     * Constructor for objects of class SideWorld.
     * 
     */
    private GreenfootImage back;
    private BattleBox[][] box;
    private int id;
    public SideWorld(int id)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 675, 1, false); 
        this.id = id;
        //id = 0, level boss, id = 1, 2, or 3, typical little pony, just talk heal. If static.getLevl = 1, level 1 enemies, 
        box = new BattleBox[9][9];
        //start at x = 303, y = 675, chnage is x = 66, y =40
        for(int i = 0; i < 9; i++){
            for(int j  = 0; j < 9; j++){
                box[i][j] = new BattleBox();
                addObject(box[i][j], 336 + i*66, 655 - j* 40);
            }
            
        }
        back = new GreenfootImage("sideWorldBack1.jpg");
        setBackground(back);
        addObject(new HitBox(), 400, 400);
        addObject(new BattleScreen(), 800, 400);
        addObject(new Boss(), 600, 150);
        
    }
    
    public void ponyTalk(){
        GreenfootImage start = new GreenfootImage(594, 360);
        SparkleEngine.drawLable(Statics.getStartLine()[id*Static.getLevel()-1]);
    }
    
}