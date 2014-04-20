/* Edge.java */


package graphalg;




public class Edge2 implements Comparable {
    protected Edge2 partner;
    protected Object v1;
    protected Object v2;
    protected int weight;
    
    /**
    * Constructor for an edge without a partner in an undirected weighted graph
    * @param v1 is the first object in this edge
    * @param v2 is the second object in this edge
    * @param weight is the weight of this edge
    **/
    public Edge2(Object v1, Object v2, int weight) {
        partner = null;
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
    
    /**
    * Constructor for an edge with a partner in a weighted undirected graph.
    * @param to is the vertex which this edge points 
    * @param from is the vertex which this edge began
    * @param partner is the edge that may or may not be a partner of this edge 
    * @param weight is the weight of this edge
    **/
    public Edge2(Object to, Object from, Edge2 partner, int weight) {
        this.partner = partner;
        this.v1 = to;
        this.v2 = from;
        this.weight = weight;
    }

    /**
    * returns where edge points  
    **/
    public Object getv2() {
        return this.v2;
    }

    /**
    * returns where edge came from
    **/
    public Object getv1() {
        return this.v1;
    }

    public int getWeight(){
        return this.weight;
    }

    /**
    * boolean returns true if a partner exists 
    **/
    public boolean hasPartner() {
        if (partner != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
    * returns partner if such exists 
    **/
    public Edge2 getPartner() {
        if (hasPartner()) {
            return partner;
        }
        else {
            return null;
        }
    }

    /**
    * sets partner to given edge
    * @param partner is the edge to be set to this edges partner 
    **/
    public void setPartner(Edge2 partner) {
        if (hasPartner()) {
            System.out.println("has a partner");
            return;
        }
        this.partner = partner;
    }

    /**
    * sets weight of this edge to newVal
    * @param newVal is the weight this edge will obtain
    **/
    public void setWeight(int newVal) {
        this.weight = newVal;
    }

    public int compareTo(Object o){
        return this.weight - (((Edge2) o).weight);
    }

    public String toString(){
        return "e( " + v1.toString() + ", " + v2.toString() +"): Weight: " + weight;
    }

}
