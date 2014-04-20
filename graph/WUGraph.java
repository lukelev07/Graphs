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
            toReturn[index] = ((Vertex)curr.item()).name;
            curr = (DListNode)curr.next();
            index++;

    }}
    catch (InvalidNodeException e1) {
        // End of vertex list
        System.out.println("hi");
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
    toAdd.parent = (DListNode)vertices.back();
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
          System.out.println("hey this isnt a vertex");
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
              curr = (DListNode) curr.next();
              removeEdge(remove1, remove2);
              
              
          }
      }
      catch (InvalidNodeException e3) {
          System.err.println(e3); // back to Sentinel
      }

      // remove hash from vertexRef
      vertexRef.remove(vertex);
      try {
        toRemove.parent.remove();
      }
      catch (InvalidNodeException e) {
          System.out.println("wat");
      }
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
      Vertex internalValue = null;
      try {
        internalValue = (Vertex) (((DListNode)internalVertex.value()).item());
      }
      catch (InvalidNodeException e) {
          System.out.println("vertex dlist poitner has  no value");
      }
      if (internalValue == null) {
          System.out.println("this is bad");
      }
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
      Vertex toDegree = null;
      try {
        toDegree = (Vertex)((DListNode)vertexRef.find(vertex).value()).item();
      }
      catch (InvalidNodeException e) {
          System.out.println("here2");
      }
      if (toDegree == null) {
          System.out.println("no pls");
      }
      return toDegree.getEdges().length();
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
      Vertex insert = null;
      
      if (!isVertex(vertex)) {
          return null;
      }
      try {
          
        insert = (Vertex)((DListNode)this.vertexRef.find(vertex).value()).item();
      }
      catch (InvalidNodeException e) {
          System.out.println("nope");
      }
      int i = 0;
      if (insert == null) {
          System.out.println("not null pls");
      }
      toReturn.neighborList = new Object[insert.edges.length()];
      toReturn.weightList = new int[insert.edges.length()];
      DListNode curr = (DListNode) insert.edges.front();
      boolean  entered = false;
      while (curr.isValidNode()) {
          entered = true;
          try {
            if (((Edge)curr.item()).ends.object1 != vertex) {
                toReturn.neighborList[i] = ((Edge)curr.item()).ends.object1;
            }
            else {
                toReturn.neighborList[i] = ((Edge)curr.item()).ends.object2;
            }
            VertexPair findEdge = ((Edge)curr.item()).ends;
            Edge weightCheck = (Edge)this.edgeRef.find(findEdge).value();
            toReturn.weightList[i] = weightCheck.weight;
            i++;
            curr = (DListNode) curr.next();
          }
          catch (InvalidNodeException e) {
              System.out.println("broken while loop dude");
          }
      }
      if (!entered) {
          return null;
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
          Edge modify = ((Edge)edgeRef.find(new VertexPair(u, v)).value());
          modify.setWeight(weight);
          return;
      }
      else if (u == v) {
          VertexPair toInsert = new VertexPair(u, v);
          Edge value = new Edge(toInsert, weight, this);
          this.edgeRef.insert(toInsert, value);
          Vertex list = null;
          try {
            list = (Vertex)((DListNode)(vertexRef.find(u).value())).item();
          }
          catch (InvalidNodeException e) {
              System.out.println("nopee");
          }
          if (list == null) {
              System.out.println("SHOULDNT BE NULL");
          }
          list.edges.insertFront(value);
          value.parent = (DListNode)list.edges.front();
          eCount++;
          return;
      }
      else {
        if (!isVertex(u) || !isVertex(v)) {
            return;
        }
        try {
        VertexPair toInsert = new VertexPair(u, v);
        Edge value = new Edge(toInsert, weight, this);
        Edge second = new Edge(toInsert, weight, this);
        value.partner = second;
        second.partner = value;
        this.edgeRef.insert(toInsert, value);
        Vertex list = (Vertex)((DListNode)(vertexRef.find(u).value())).item();
        list.edges.insertFront(value);
        value.parent = (DListNode)list.edges.front();
        Vertex list2 = (Vertex)((DListNode)(vertexRef.find(v).value())).item();
        list2.edges.insertFront(second);
        second.parent = (DListNode)list2.edges.front();
        eCount++;
        }
        catch (InvalidNodeException e) {
            //you returned a node from the giant dlist without an item somehow
            System.out.println("hello");
        }
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
      if (isEdge(u, v)) {
        if (!isVertex(u) || !isVertex(v)) {
            System.out.println("this isnt an edge");
            return;
        }
        VertexPair findThis = new VertexPair(u, v);
        Edge found = (Edge) edgeRef.find(findThis).value();
        if (found.parent == null) {
            System.out.println("error");
            return;
        }
        if (found.hasPartner()) {
            if (found.partner.parent == null) {
                System.out.println("error2");
                return;
            }
            try {
                ((DListNode)found.partner.parent).remove();
                ((DListNode)found.parent).remove();
                edgeRef.remove(findThis);
            }
            catch (InvalidNodeException e) {
                System.out.println("hi, shouldnt happen");
                System.err.println(e);
            }
        }
        else {
            try {
                ((DListNode)found.parent).remove();
                edgeRef.remove(findThis);
            }
            catch (InvalidNodeException e) {
                System.out.println("hi2, shouldnt happen");
            }
        }
      }
      else {
          return;
      }
      eCount--;
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
