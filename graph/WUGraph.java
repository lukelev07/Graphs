/* WUGraph.java */

package graph;
import list.*;
import dict.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
  protected HashTableChained vertexRef;
  protected HashTableChained edgeRef;
  protected DList vertices;
  protected int vCount;
  protected int eCount;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph() {
      vertexRef = new HashTableChained();
      edgeRef = new HashTableChained();
      vertices = new DList();
  }
  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
    return vCount;
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
      return eCount;
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
    Object[] toReturn = new Object[vCount];
    int index = 0;
    DListNode curr = (DListNode)vertices.front();
    try {
        while (curr.isValidNode()) {
            toReturn[index] = curr.item();
            curr = (DListNode)curr.next();
            index++;

    }}
    catch (InvalidNodeException e1) {
        // End of vertex list
    }
    return toReturn;
  }
  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {

    //check if this vertex is already in the graph
    if (vertexRef.find(vertex) != null) {
        return;
    }
    //initialize vertex with name, insert into DList  
    Vertex toAdd = new Vertex(vertex, this);
    vertices.insertBack((Object)toAdd);

    //hash in table and point to internal DList
    vertexRef.insert(vertex, vertices.back());
    vCount++;
  }


  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) {
      if (!isVertex(vertex)) {
          return;
      }

      // must do the following:
      // 1. Find the vertex to remove internally
      // 2. Iterate through its edges and delete them along with their partner edges
      // 3. Remove the Vertex from vertexRef table; update vCount

      // find internal representation of Vertex
      Vertex toRemove = getVertex(vertex);

      // iterate through the internal edges and remove them along with partners
      DList internalEdges = toRemove.getEdges();
      DListNode curr = (DListNode) internalEdges.front();
      try {
          while (curr.isValidNode()) {

              // update references to edge and partner
              Edge edge = (Edge) curr.item();
              VertexPair currEdges = edge.getEdgePair();
              Object remove1 = currEdges.object1;
              Object remove2 = currEdges.object2;
              removeEdge(remove1, remove2);

              curr = (DListNode) curr.next();
          }
      }
      catch (InvalidNodeException e3) {
          System.err.println(e3); // back to Sentinel
      }

      // remove hash from vertexRef
      vertexRef.remove(vertex);
      vCount--;
  }

  /**
   * getVertex() takes in the applications Vertex and find the associated internal representation
   * of that Vertex object. Returns null if such a Vertex is not a part of this graph.
   * @param vertex
   * @return the internal representation of a Vertex in this graph
   */
  private Vertex getVertex(Object vertex) {
      if (vertexRef.find(vertex) == null) {
          return null;
      }
      // find the vertex in vertexRef and return its value
      Entry internalVertex = vertexRef.find(vertex);
      Vertex internalValue = (Vertex) internalVertex.value();

      return internalValue;
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    // decide if vertex is a Valid vertex in this graph.
    if (vertexRef.find(vertex) != null) {
        return true;
    }
    return false;
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
      if (!isVertex(vertex)) {
          return 0;
      }
      return ((Vertex)vertex).getEdges().length();
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex) {
      Neighbors toReturn = new Neighbors();
      Vertex insert = (Vertex)this.vertexRef.find(vertex).value();
      int i = 0;
      toReturn.neighborList = new Object[insert.edges.length()];
      toReturn.weightList = new int[insert.edges.length()];
      DListNode curr = (DListNode) insert.edges.front();
      while (curr.isValidNode()) {
          try {
            toReturn.neighborList[i] = curr.item();
            VertexPair findEdge = new VertexPair(vertex, (Vertex)curr.item());
            Edge weightCheck = (Edge)this.edgeRef.find(findEdge).value();
            toReturn.weightList[i] = weightCheck.weight;
            i++;
            curr = (DListNode) curr.next();
          }
          catch (InvalidNodeException e) {
              System.out.println("broken while loop dude");
          }
      }
      return toReturn;
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) {
      if (isEdge(u, v)) {
          return;
      }
      else {
        if (!isVertex(u) || !isVertex(v)) {
            return;
        }
        VertexPair toInsert = new VertexPair(u, v);
        Edge value = new Edge(toInsert, weight, this);
        Edge second = new Edge(toInsert, weight, this);
        value.
        this.edgeRef.insert(toInsert, value);
        eCount++;
      }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v) {
      return;
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
      // case 1: either u or v is not a vertex of this graph
      if (!isVertex(u) || !isVertex(v)) {
          return false;
      }

      // case 2: both vertices exist: check edgeRef for edge (u, v) and (v, u)
      VertexPair possibleEdge = new VertexPair(u, v);
      if (edgeRef.find(possibleEdge) != null) {
          return true;
      }
      return false;
  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
      if (!isEdge(u, v)) {
          return 0;
      }
      // need to get internal representation of the edge
      VertexPair internalEdge = new VertexPair(u, v);
      Edge edge = (Edge) edgeRef.find(internalEdge).value();

      // find its associated weight
      int edgeWeight = edge.getWeight();
      return edgeWeight;
  }

}
