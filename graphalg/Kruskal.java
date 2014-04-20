/* Kruskal.java */

package graphalg;

import graph.*;
import graphalg.queues.LinkedQueue;
import graphalg.queues.QueueEmptyException;
import set.*;
import dict.*;


/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

    /**
     * minSpanTree() returns a WUGraph that represents the minimum spanning tree
     * of the WUGraph g.  The original WUGraph g is NOT changed.
     *
     * @param g The weighted, undirected graph whose MST we want to compute.
     * @return A newly constructed WUGraph representing the MST of g.
     */
    public static WUGraph minSpanTree(WUGraph g){
        WUGraph myGraph = new WUGraph();
        LinkedQueue edgeList = new LinkedQueue();
        Object[] vertices = g.getVertices();
        HashTableChained myTable = new HashTableChained(vertices.length);
        int j = 0;
        for(int x = 0; x < vertices.length;x++){
            Object o = vertices[x];
            Object k = myTable.find(o);
            if ( k == null){
                k = new KruskalVertex(j,o);
                myTable.insert(o,k);
                j++;
            }
            else{
                k = ((Entry) k).value();
            }
            myGraph.addVertex(o);
            Neighbors neighbors = g.getNeighbors(o);
            Object[] neighborObjects = neighbors.neighborList;
            int[] weights = neighbors.weightList;
            for(int i = 0; i < neighborObjects.length; i++){
                Object v = myTable.find(neighborObjects[i]);
                Object other;
                if (v == null){
                    other = new KruskalVertex(j,neighborObjects[i]);
                    myTable.insert(neighborObjects[i],other);
                    j++;
                }
                else{
                    other = ((Entry) v).value();
                }
                Edge2 newEdge = new Edge2(k,other,weights[i]);
                edgeList.enqueue(newEdge);
            }
        }
        ListSorts.quickSort(edgeList);
        placeEdges(edgeList,myGraph,vertices.length);
        return myGraph;
    }
    /**
     * @param edges the sorted LinkedQueue of possible edges
     * @param g the WUGraph to add the edges to
     * @param numElem the number of vertices
     */
    private static void placeEdges(LinkedQueue edges, WUGraph g, int numElem){
        DisjointSets mySet = new DisjointSets(numElem);
        try{
            while (!edges.isEmpty()){
                Edge2 currEdge = (Edge2) edges.dequeue();
                KruskalVertex kVertex = (KruskalVertex) currEdge.getv1();
                KruskalVertex otherVertex = (KruskalVertex) currEdge.getv2();
                int kInt = mySet.find(kVertex.getValue());
                int otherInt = mySet.find(otherVertex.getValue());
                if (kInt == otherInt){
                    continue;
                }
                else{
                    g.addEdge(kVertex.getObject(),otherVertex.getObject(),currEdge.getWeight());
                    mySet.union(kInt,otherInt);
                }

            }
        }
        catch (QueueEmptyException e){System.out.println("oh no");}

    }

}
