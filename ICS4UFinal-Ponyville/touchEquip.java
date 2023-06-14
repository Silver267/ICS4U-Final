import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class touchEquip here.
 * 
 * @author Ming & Yawen
 * @version (a version number or a date)
 */
public class touchEquip extends Actor{
    private GreenfootImage img;
    private int id;
    private int animateCountDown;
    private int imageIndex = 0;
    private boolean animation = false;
    //Timeout for ponies to blink, counted in acts.
    private final int blinkTimeout = 300;
    //SimpleTimer animationTimer1 = new SimpleTimer();
    //SimpleTimer animationTimer2 = new SimpleTimer();
    //SimpleTimer animationTimer3 = new SimpleTimer();
    long startTime = System.currentTimeMillis();
    int imageLength = 6;
    
    // Creating Animation Images
    GreenfootImage[] BrightMac = new GreenfootImage[6];
    GreenfootImage[] PearButter = new GreenfootImage[6];
    GreenfootImage[] GrannySmith = new GreenfootImage[6];
    GreenfootImage[] Scootaloo = new GreenfootImage[6];
    GreenfootImage[] SweetieBelle = new GreenfootImage[6];
    GreenfootImage[] AppleBloom = new GreenfootImage[6];
    GreenfootImage[] Palette = new GreenfootImage[6];
    GreenfootImage[] Trixie = new GreenfootImage[6];
    GreenfootImage[] LusterDawn = new GreenfootImage[6];
    
    public touchEquip(int id){
        this.id = id;
        //[getlevel][id][picid]
        animateCountDown = blinkTimeout-2;
        // Initializing Animation Images
        initImage("BrightMac");
        initImage("PearButter");
        initImage("GrannySmith");
        initImage("Scootaloo");
        initImage("SweetieBelle");
        initImage("AppleBloom");
        initImage("Palette");
        initImage("Trixie");
        initImage("LusterDawn");
    }
    
    private void initImage(String ponyName) {
        if (ponyName.equals("BrightMac")) {
            for (int i = 0; i < imageLength; i++) {
                BrightMac[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                BrightMac[i].scale(60, 70);
            }
        }
        if (ponyName.equals("PearButter")) {
            for (int i = 0; i < imageLength; i++) {
                PearButter[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                PearButter[i].scale(60, 70);
            }
        }
        if (ponyName.equals("GrannySmith")) {
            for (int i = 0; i < imageLength; i++) {
                GrannySmith[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                GrannySmith[i].scale(60, 70);
            }
        }
        if (ponyName.equals("Scootaloo")) {
            for (int i = 0; i < imageLength; i++) {
                Scootaloo[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                Scootaloo[i].scale(60, 70);
            }
        }
        if (ponyName.equals("SweetieBelle")) {
            for (int i = 0; i < imageLength; i++) {
                SweetieBelle[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                SweetieBelle[i].scale(60, 70);
            }
        }
        if (ponyName.equals("AppleBloom")) {
            for (int i = 0; i < imageLength; i++) {
                AppleBloom[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                AppleBloom[i].scale(60, 70);
            }
        }
        if (ponyName.equals("Palette")) {
            for (int i = 0; i < imageLength; i++) {
                Palette[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                Palette[i].scale(60, 70);
            }
        }
        if (ponyName.equals("Trixie")) {
            for (int i = 0; i < imageLength; i++) {
                Trixie[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                Trixie[i].scale(60, 70);
            }
        }
        if (ponyName.equals("LusterDawn")) {
            for (int i = 0; i < imageLength; i++) {
                LusterDawn[i] = new GreenfootImage("images/" + ponyName + "/tile00" + i + ".png");
                LusterDawn[i].scale(60, 70);
            }
        }
    }
    
    public void addedToWorld(World w){
        //animationTimer1.mark();
        //animationTimer2.mark();
        //animationTimer3.mark();
    }
    
    public void act() {
        animateCountDown = (animateCountDown+1)%blinkTimeout;
        if(animateCountDown<blinkTimeout-15)
            animation = false;
        else
            animation = true;
        imageIndex = (((animateCountDown-blinkTimeout+15)/3)+1)%5;
        switch(id){
            case 0:
                img = new GreenfootImage("textures/Portal.png");
                img.scale(((MainWorld)getWorld()).getMap().getSz()[0], ((MainWorld)getWorld()).getMap().getSz()[1]);
                setImage(img);
                break;
            case 1:
                if (Statics.getLevel() == 1) {
                    if (animation) {
                        animate(BrightMac, imageIndex);
                    }
                }
                if (Statics.getLevel() == 2) {
                    if (animation) {
                        animate(Scootaloo, imageIndex);
                    }
                }
                if (Statics.getLevel() == 3) {
                    if (animation) {
                        animate(Palette, imageIndex);
                    }
                }
                break;
            case 2:
                if (Statics.getLevel() == 1) {
                    if (animation) {
                        animate(PearButter, imageIndex);
                    }
                }
                if (Statics.getLevel() == 2) {
                    if (animation) {
                        animate(SweetieBelle, imageIndex);
                    }
                }
                if (Statics.getLevel() == 3) {
                    if (animation) {
                        animate(Trixie, imageIndex);
                    }
                }
                break;
            case 3:
                if (Statics.getLevel() == 1) {
                    if (animation) {
                        animate(GrannySmith, imageIndex);
                    }
                }
                if (Statics.getLevel() == 2) {
                    if (animation) {
                        animate(AppleBloom, imageIndex);
                    }
                }
                if (Statics.getLevel() == 3) {
                    if (animation) {
                        animate(LusterDawn, imageIndex);
                    }
                }
                break;
        }
    }
    
    private void animate(GreenfootImage[] ponyName, int index){
        setImage(ponyName[index]);
    }
}