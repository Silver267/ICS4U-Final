import java.util.*;
import greenfoot.*;
/**
 * SparkleEngine - Very portable & reusable engine that wrapps various functions.
 * All static methods.
 * Rooted from grade 11 final project's engine, developed largely since then.
 * Making the engine an actor to prevent repeatitively call dt = new Date().
 * 
 * @author Xuanxi Jiang
 * @version 0.3
 */
public class SparkleEngine{
    //Sets the brightness of an image according to brt, valid values are [0 to 100].
    public static GreenfootImage setBrightness(GreenfootImage img, int brt){
        GreenfootImage ret = new GreenfootImage(img);
        if(brt==50)
            return ret;
        else if(brt>50 && brt<=100)
            return brighten((brt-50)/50.0, ret);
        else if(brt>=0)
            return shade(brt/50.0, ret);
        return ret;
    }
    
    //Turns an image to red and returns a new image. perc stands for percentage of change, ranges are [0 100].
    public static GreenfootImage turnRed(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed(), dr = (r+((255-r)*perc)/100);
                int g = cc.getGreen(), dg = (g*perc)/100;
                int b = cc.getBlue(), db = (b*perc)/100;
                int a = cc.getAlpha();
                ret.setColorAt(x, y, new Color(dr, dg, db, a));
            }
        }
        return ret;
    }

    //Turns an image to blue and returns a new image. perc stands for percentage of change, ranges are [0 100].
    public static GreenfootImage turnBlue(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed(), dr = (r*perc)/100;
                int g = cc.getGreen(), dg = (g*perc)/100;
                int b = cc.getBlue(), db = (b+((255-b)*perc)/100);
                int a = cc.getAlpha();
                ret.setColorAt(x, y, new Color(dr, dg, db, a));
            }
        }
        return ret;
    }
    
    //Reverses the color of an image and returns a new image. perc stands for percentage of inversion, ranges are [0 100].
    public static GreenfootImage revColor(GreenfootImage img, int perc){
        if(perc<=0 || perc>100)
            return img;
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed(), dr = (Math.abs(r-(255-r))*perc)/100;
                int g = cc.getGreen(), dg = (Math.abs(g-(255-g))*perc)/100;
                int b = cc.getBlue(), db = (Math.abs(b-(255-b))*perc)/100;
                int a = cc.getAlpha();
                int fr = r>(255-r) ? r-dr : r+dr;
                int fg = g>(255-g) ? g-dg : g+dg;
                int fb = b>(255-b) ? b-db : b+db;
                ret.setColorAt(x, y, new Color(fr, fg, fb, a));
            }
        }
        return ret;
    }
    
    //shadeFactor = [0, 1.0]:
    //Shades image by shadeFactor.
    //Usage: background = new GreenfootImage(ts.shade(0.5, background));
    public static GreenfootImage shade(double shadeFactor, GreenfootImage img){
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed();
                int g = cc.getGreen();
                int b = cc.getBlue();
                int a = cc.getAlpha();
                cc = new Color((int)(r*(shadeFactor)), (int)(g*(shadeFactor)), (int)(b*(shadeFactor)), a);
                ret.setColorAt(x, y, cc); 
            }
        }
        return ret;
    }
    
    //brightFactor = [0, 1.0]:
    //Brightens image by brightFactor.
    //Similar to shade.
    public static GreenfootImage brighten(double brightFactor, GreenfootImage img){
        GreenfootImage ret = new GreenfootImage(img);
        for(int x=0; x<ret.getWidth(); x++){
            for(int y=0; y<ret.getHeight(); y++){
                Color cc = ret.getColorAt(x, y);
                int r = cc.getRed();
                int g = cc.getGreen();
                int b = cc.getBlue();
                int a = cc.getAlpha();
                cc = new Color((int)(r+(255-r)*(brightFactor)), (int)(g+(255-g)*(brightFactor)), (int)(b+(255-b)*(brightFactor)), a);
                ret.setColorAt(x, y, cc); 
            }
        }
        return ret;
    }
    
    /*
     * Get force of friction of object in N
     * Helpful in calculating the needed force to maintain velocity, etc.
     * Remember: to maintain constant velocity: cva = getFrict()/mass
     * (cva = acceleration to maintain constant velocity)
     * Only works if y axis has no force other than gravity and normal force.
     * If y axis has force: real friction = getFrict() + yForce*u
    */
    public static double getFrict(double mass, double cof, double gravity){
        return mass*gravity*cof;
    }
    
    /*
     * delta velocity due to friction
     * F = m*a, Ff = mass*gravity*u (mass*gravity) = N, u = kinetic friction coefficient
     * vel, accel, time
     * xf (force on x axis) = 
     * usage: spd = spd - dvDueToFrict();
    */
    
    public static double dvDueToFrict(double vel, double accel, double mass, double cof, double gravity, double time){//function to calculate the effect of friction.
        double F = mass*accel;
        double frict = mass*gravity*cof;
        return ((F - frict)/mass)*time;
    }
    
    public static int getStringWidth (Font font, String text){//Borrowed & optimized code from Mr. Cohen to detect length of String.
        //Note: this method is optimized using binary search by me for better performance.
        int maxWidth = (int)(text.length() * (font.getSize()/1.20)), fontSize = font.getSize();
        int margin = fontSize/6, l = 0, r = maxWidth; //Skipped pixel; variables for binary search algorithm;
        GreenfootImage temp = new GreenfootImage (maxWidth+fontSize, fontSize);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);
        while(l<r){
            boolean found = false;
            int mid = (l+r)/2; //find middle (for binary search)
            //searching through a fontsize*fontsize space to find if font exsist
            for(int j=0; j<=fontSize-1 && !found; j+=margin)
                for (int i=0; i<=fontSize-1 && !found; i+=margin)
                    if (temp.getColorAt(mid+j, i).getAlpha() != 0)
                        found = true;
            if (found) //binary search case 1 (index too less)
                l=mid+1;
            else //binary search case 2 (index too much)
                r=mid;
        }
        return l;
    }
    
    public static String wordWrap(String in, Font f, int mxLength){//Method to convert a string to an automatically wraped string.
        int ind = 1; //current index
        String curWord = "", cur = in.charAt(0)+"", out = ""; //Initial strings
        while(ind<in.length()){ //Iterate through the text until the entire text is checked
            while(getStringWidth(f, cur)<mxLength){ //prevent overflow
                if(ind>=in.length()){//if index is more than the text size, break immediately
                    cur += curWord;
                    break;
                }
                if(in.charAt(ind)==' '){ //detect word
                    if(getStringWidth(f, cur+curWord)>=mxLength){//additional detection of overflow
                        ind -= curWord.length()-1;//if overflow, go back to the previous word (not including the "space" in [0]).
                        break;//break out of while loop
                    }
                    cur += curWord;//add current word to current string
                    curWord = "";//reset current word
                }else if(in.charAt(ind)=='\n'){//additional detection of new line
                    ind++; //if new line, go to next character and break the loop.
                    break;
                }
                curWord += in.charAt(ind);//add next letter to current word
                ind++;
            }
            out+=cur+"\n";//add current string to output string, and add an "\n" to represent next line.
            if(ind<in.length()){//if index is less than the text, continue to iterate.
                cur = in.charAt(ind)+""; 
                curWord = "";
                ind++;
            }
        }
        return out;
    }
    
    public static long getTime(){
        return System.currentTimeMillis();
    }
    
    public static GreenfootImage drawLable(Color c, Font f, int x, int y, String text, GreenfootImage in){
        GreenfootImage ret = new GreenfootImage(in);
        ret.setFont(f); ret.setColor(c);
        ret.drawString(text, x, y);
        return ret;
    }
    
    public static void formMap(Node[][] mp, int xsz, int ysz){
        /* 
         * Wall: 1;
         * Space: 2;
         * Gate: 3;
        */
        
    }
}
