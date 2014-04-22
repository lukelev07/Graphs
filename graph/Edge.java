/* Edge.java */


package graph;
import list.*;
import dict.*;


public class Edge {

    // fields of an Edge
    protected WUGraph myGraph;
    protected Edge partner;
    protected VertexPair ends;
    protected int weight;
    protected DListNode parent;

    /**
     * Constructs a new Edge to be in myGraph. Will assign the correct weight and
     * corresponding parameters.
     *
     * @param vp the vertex pair passed in to make this edge
     * @param weight the weight to be assigned to this edge
     * @param myGraph the graph this edge belongs to (encapsulation)
     */
    public Edge(VertexPair vp, int weight, WUGraph myGraph) {
        partner = null;
        this.ends = vp;
        this.weight = weight;
        this.myGraph = myGraph;
    }

    /**
     * returns the vertexPair associated with this Edge
     **/
    public VertexPair getEdgePair() {
        return this.ends;
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
    public Edge getPartner() {
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
    public void setPartner(Edge partner) {
        if (hasPartner()) {
            System.out.println("has a partner");
            return;
        }
        this.partner = partner;
    }

    /**
     * sets graph to a given graph
     * @param myGraph is the graph which this edge belongs to
     **/
    public void setGraph(WUGraph myGraph) {
        this.myGraph = myGraph;
    }

    /**
     * sets weight of this edge to newVal
     * @param newVal is the weight this edge will obtain
     **/
    public void setWeight(int newVal) {
        this.weight = newVal;
    }


    /**
     * getWeight() returns the weight of this edge
     **/
    public int getWeight() {
        return this.weight;
    }

}
