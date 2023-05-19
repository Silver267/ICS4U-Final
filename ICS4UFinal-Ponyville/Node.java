/**
 * Node, the building block of map
 * 
 * @author Thomas & George (refactor)
 * @version 1.0
 */
public class Node{
    //enemy may be not used (if based on rng)
    private int type, item, enemy;
    
    public Node(int type){
        this.type = type;
    }

    public int getItem(){
        return item;
    }
    
    public void setItem(){
        this.item = item;
    }
    
    public int getType(){
        return type;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getT(){
        return 0;
    }
}
