/* Vertex.java */

package graph;
import list.*;
import dict.*;

public class Vertex {
    protected DList edges;
    protected WUGraph myGraph;
    protected Object name; 
    protected DListNode parent;

    /** 
    * Constructs a vertex with a reference to the graph it is part of.
    * Constructs an empty DList to contain edges.
    * @param graph is the WUGraph vertex belongs to.
    **/
    public Vertex(Object name, WUGraph graph) {
        this.name = name;
        this.myGraph = graph;
        edges = new DList();
    }
    
    /**
    * returns the edges of this vertex 
    **/
    public DList getEdges() {
        return edges;
    }

    /**
    * returns the graph this vertex belongs to
    **/
    public WUGraph getGraph() {
        return this.myGraph;
    }
}