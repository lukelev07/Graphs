/* Edge.java */


package graph;
import list.*;
import dict.*;


public class Edge {
    protected WUGraph myGraph;
    protected Edge partner;
    protected Vertex v1;
    protected Vertex v2;
    protected int weight;
    
    /**
    * Constructor for an edge without a partner in an undirected weighted graph
    * @param to is the vertex which this edge points 
    * @param from is the vertex which this edge began
    * @param weight is the weight of this edge 
    * @param myGraph is the graph this edge belongs to 
    **/
    public Edge(Vertex to, Vertex from, boolean directed, int weight, WUGraph myGraph) {
        partner = null;
        this.v1 = to;
        this.v2 = from;
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
    public Edge(Vertex to, Vertex from, Edge partner, boolean directed, int weight, WUGraph myGraph) {
        this.partner = partner;
        this.v1 = to;
        this.v2 = from;
        this.weight = weight;
        this.myGraph = myGraph;
    }

    /**
    * returns where edge points  
    **/
    public Vertex getv2() {
        return this.v2;
    }

    /**
    * returns where edge came from
    **/
    public Vertex getv1() {
        return this.v1;
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
}
