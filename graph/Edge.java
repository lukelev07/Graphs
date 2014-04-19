/* Edge.java */


package graph;
import list.*;
import dict.*;


public class Edge {
    protected WUGraph myGraph;
    protected Edge partner;
    protected VertexPair ends;
    protected int weight;
    protected DListNode parent;
    /**
    * Constructor for an edge without a partner in an undirected weighted graph
    * @param to is the vertex which this edge points 
    * @param from is the vertex which this edge began
    * @param weight is the weight of this edge 
    * @param myGraph is the graph this edge belongs to 
    **/
    public Edge(Object to, Object from, int weight, WUGraph myGraph) {
        partner = null;
        this.ends = new VertexPair(to, from);
        this.weight = weight;
        this.myGraph = myGraph;
    }
    
    public Edge(VertexPair vp, int weight, WUGraph myGraph) {
        partner = null;
        this.ends = vp;
        this.weight = weight;
        this.myGraph = myGraph;
    }
    
    /**
    * Constructor for an edge with a partner in a weighted undirected graph.
    * @param to is the vertex which this edge points 
    * @param from is the vertex which this edge began
    * @param partner is the edge that may or may not be a partner of this edge 
    * @param weight is the weight of this edge 
    * @param myGraph is the graph this edge belongs to 
    **/
    public Edge(Object to, Object from, Edge partner, int weight, WUGraph myGraph) {
        this.partner = partner;
        this.ends = new VertexPair(to, from);
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

    public void removeThis() {
        // removes this edge from the internal representation of this graph
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
