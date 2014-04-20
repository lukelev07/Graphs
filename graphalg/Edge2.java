/* Edge.java */


package graphalg;




public class Edge2 implements Comparable {
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
        this.v1 = v1;
        this.v2 = v2;
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
    * sets weight of this edge to newVal
    * @param newVal is the weight this edge will obtain
    **/
    public void setWeight(int newVal) {
        this.weight = newVal;
    }

    /**
     *
     * @param o the other object to compare to
     * @return either 0 or a postive or negative int, the difference
     * between the two edges' weight
     */
    public int compareTo(Object o){
        return this.weight - (((Edge2) o).weight);
    }

    /**
     * Override toString
     * @return string representation of an edge
     */
    public String toString(){
        return "e( " + v1.toString() + ", " + v2.toString() +"): Weight: " + weight;
    }

}
