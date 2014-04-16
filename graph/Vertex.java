/* Vertex.java */

package Graph;

public class Vertex {
    protected Dlist edges;
    protected WUGraph myGraph;

    /** 
     *  Constructs a vertex with a reference to the graph it is part of.
     *  Constructs an empty Dlist to contain edges.
     *  @param graph is the WUGraph vertex belongs to.
     **/
    public Vertex(WUGraph graph) {
        this.myGraph = graph;
        edges = new Dlist();
    }
    
    /** 
     *  Returns the edges of this Vertex.
     **/
    public Dlist getEdges() {
        return edges;
    }
}