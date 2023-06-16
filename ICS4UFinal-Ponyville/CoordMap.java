import java.util.*;
import java.io.*;
/**
 * Map, contains map and various methods that a map needs
 * Created when WarWorld is created, reflects map.
 * 
 * @author Xuanxi Jiang
 * @version 1.0
 */
public class CoordMap{
    // instance variables - replace the example below with your own
    
    //Change by George, change Node from private to public
    private Node[][] mp;
    private int gridX, gridY; //number of grids
    private int xpix, ypix; //number of pixels in the x and y axis
    private int pixX, pixY; //Number of pixels per grid
    private int topOffset; //Offset to prevent bottom to be not covered by grid.
    
    //Constructor: map id, map
    public CoordMap(int mapId, int xsiz, int ysiz, int xpix, int ypix){
        this.gridX = xsiz; this.gridY = ysiz;
        this.xpix = xpix; this.ypix = ypix;
        pixX = xpix/xsiz; pixY = ypix/ysiz;
        mp = new Node[ysiz][xsiz];
        //to prevent top from offset.
        topOffset = 4;
        /**
         * Writing maps:
         * 
         * format:
         *      1 2 1 1 1 3 
         *      1 2 2 1 1 2
         *      1 3 2 1 1 1
         * excetera.
         * 
         * Corresponding values:
         * 0   - clear
         * 1   - main character spawn point
         * 2   - blocked
         * 3+n - item, numbered starting from n=0 (if 3 -> item 0, etc.)
         * item 0: portal
         */
        try{
            FastReader fr = new FastReader(new FileReader("./maps/"+mapId+"-1.txt"));
            for(int i=0; i<ysiz; i++)
                for(int j=0; j<xsiz; j++)
                    mp[i][j] = new Node(fr.nextInt());
        }catch(FileNotFoundException e){
            System.out.println("Unexpected Exception! Your program is probably corrupt.");
        }
    }
    
    public int[] getSz(){
        return new int[]{pixX, pixY};
    }
    
    public Node[][] getNodeMap(){
        return mp;
    }
    
    public void setNode(Node in, int[] cood){
        mp[cood[0]][cood[1]] = in;
    }
    
    public Node getNode(int[] in){
        return mp[in[1]][in[0]];
    }
    
    public int[] getMaxes(){
        return new int[]{gridX, gridY};
    }
    
    //input value is zero-indexed, get pixel position from grid value
    public int[] getPixes(int[] coord){
        return new int[]{coord[0]*pixX + pixX/2, coord[1]*pixY + pixY/2 + topOffset};
    }
    
    //given a pixel x and y, return corresponding x and y of grid, 0-positioned
    public int[] getMaps(int[] coord){
        return new int[]{coord[0]/pixX, (coord[1]-topOffset)/pixY < 0 ? 0:(coord[1]-topOffset)/pixY};
    }
    
    //Fastreader
    private static class FastReader {
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
