/* Vertex.java */

package graph;
import list.*;
import dict.*;

public class Vertex {
    protected DList edges;
    protected WUGraph myGraph;

    /** 
     *  Constructs a vertex with a reference to the graph it is part of.
     *  Constructs an empty Dlist to contain edges.
     *  @param graph is the WUGraph vertex belongs to.
     **/
    public Vertex(WUGraph graph) {
        this.myGraph = graph;
        edges = new DList();
    }
    
    /** 
     *  Returns the edges of this Vertex.
     **/
    public DList getEdges() {
        return edges;
    }
}