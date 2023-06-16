/**
 * Node, the building block of map
 * 
 * @author Xuanxi Jiang & George (refactor)
 * @version 1.0
 */
public class Node{
    //enemy may be not used (if based on rng)
    private int type;
    
    public Node(int type){
        this.type = type;
    }
    
    public int getType(){
        return type;
    }
    
    public void setType(int type){
        this.type = type;
    }
}
