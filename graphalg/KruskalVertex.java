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

    public int getValue(){
        return value;
    }

    public Object getObject(){
        return myObj;
    }
}
