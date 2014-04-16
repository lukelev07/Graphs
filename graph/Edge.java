package graph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
public class Edge {
    protected WUGraph myGraph;
    protected Edge partner;
    protected boolean directed;
    protected Vertex from;
    protected Vertex to;
    
    /*
    this is an edge constructor for an edge that has no partner
    */
    
    public Edge(Vertex to, Vertex from, boolean directed, WUGraph myGraph) {
        partner = null;
        this.to = to;
        this.from = from;
        this.directed = directed;
        this.myGraph = myGraph;
    }
    
    /*
    this is an edge constructor for an edge that has a partner
    */
    
    public Edge(Vertex to, Vertex from, Edge partner, boolean directed, WUGraph myGraph) {
        this.partner = partner;
        this.to = to;
        this.from = from;
        this.directed = directed;
        this.myGraph = myGraph;
    }
    
    public Vertex getTo() {
        return this.to;
    }
    
    public Vertex getFrom() {
        return this.from;
    }
    
    public boolean hasPartner() {
        if (partner != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public Edge getPartner() {
        if (hasPartner()) {
            return partner;
        }
        else {
            return null;
        }
    }
    
    public boolean isDirected() {
        return directed;
    }
    
    public void setPartner(Edge partner) {
        if (hasPartner()) {
            System.out.println("has a partner");
            return;
        }
        this.partner = partner;
    }
    
    public void setGraph(WUGraph myGraph) {
        this.myGraph = myGraph;
    }
}
