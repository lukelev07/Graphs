package graphalg;

/**
 * Created by Gurjit on 4/18/2014.
 */
public class KruskalVertex {

    protected Object myObj;
    protected  int value;

    public KruskalVertex(int value, Object vertex){
        myObj = vertex;
        this.value = value;
    }
    /*
     * method to return the int value associated with this object
     * @return int value
     */
    public int getValue(){
        return value;
    }

    /*
     * method to return the object associated in this Vertex
     * @return Object myObj
     */
    public Object getObject(){
        return myObj;
    }
}
