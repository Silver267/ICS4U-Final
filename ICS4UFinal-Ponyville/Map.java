import java.util.*;
import java.io.*;
/**
 * Map, contains map and various methods that a map needs
 * Created when WarWorld is created, reflects map.
 * 
 * @author Thomas
 * @version 1.0
 */
public class Map{
    // instance variables - replace the example below with your own
    
    //Change by George, change Node from private to public
    private Node[][] mp;
    private int gridX, gridY; //number of grids
    private int xpix, ypix; //number of pixels in the x and y axis
    private int pixX, pixY; //Number of pixels per grid
    
    //Constructor: map id, map
    public Map(int mapId, int xsiz, int ysiz, int xpix, int ypix){
        //Example: 1.txt, 2.txt
        /*Terrain
         * v
           1 2 3
           2 2 3
           */
        this.gridX = xsiz; this.gridY = ysiz;
        this.xpix = xpix; this.ypix = ypix;
        //assume 20*9, pixX is 48, pixY is 45.
        pixX = xpix/xsiz; pixY = ypix/ysiz;
        //System.out.println(pixY);
        mp = new Node[ysiz][xsiz];
        /*
         * Change this part to read auto-generated stuff.
        try{
            FastReader fr = new FastReader(new FileReader("./maps/"+mapId+".txt"));
            for(int i=0; i<ysiz; i++){
                for(int j=0; j<xsiz; j++){
                    mp[i][j] = new Node(fr.nextInt());
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Unexpected Exception! Your program is probably corrupt.");
        }
        */
    }
    
    public Node[][] getNodeMap()
    {
        return mp;
    }

    public int getType(int x, int y){
        if(y>=gridY || x>=gridX)
            return -1;
        return mp[y][x].getT();
    }
    
    //The method below is added by George
    public Node getNode(int x, int y)
    {
        return mp[y][x];
    }
    
    public int getSlow(int x, int y){
        if(y>=gridY || x>=gridX)
            return -1;
        return mp[y][x].getT();
    }
    
    public int getMaxX(){
        return gridX;
    }
    
    public int getMaxY(){
        return gridY;
    }
    
    //input value is zero-indexed, get pixel x position from grid x value
    public int getPixX(int x){
        return pixX*x+pixX/2;
    }
    
    //input value is zero-indexed, get pixel y position from grid y value
    public int getPixY(int y){
        return pixY*y+pixY/2;
    }
    
    //given a pixel x, return corresponding x of grid, 0-positioned
    public int getMapX(int px){
        return px/pixX;
    }
    
    //given a pixel y, return corresponding y of grid, 0-positioned
    public int getMapY(int py){
        return py/pixY;
    }
    
    
    
    //getTerrian(getMapX(this.getX()), getMapY(this.getY()));
    
    
    
    //Fastreader
    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader(FileReader in) {
            br = new BufferedReader(in);
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
    
        long nextLong() {
            return Long.parseLong(next());
        }
    
        double nextDouble() {
            return Double.parseDouble(next());
        }
    
        String nextLine() {
            String str = null;
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
