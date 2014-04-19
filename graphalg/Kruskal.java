/* Kruskal.java */

package graphalg;

import graph.*;
import graphalg.queues.LinkedQueue;
import graphalg.queues.QueueEmptyException;
import list.DList;
import list.List;
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
        KruskalVertex[] kVertices = new KruskalVertex[vertices.length];
        HashTableChained myTable = new HashTableChained(vertices.length);
        for(int j = 0; j < vertices.length; j++){
            Object o = vertices[j];
            KruskalVertex k = new KruskalVertex(j,o);
            myTable.insert(o,k);
            myGraph.addVertex(o);
        }
        for(Object o:vertices){
            Neighbors neighbors = g.getNeighbors(o);
            Object[] neighborObjects = neighbors.neighborList;
            int[] weights = neighbors.weightList;
            KruskalVertex k = (KruskalVertex) myTable.find(o).value();
            for(int i = 0; i < neighborObjects.length; i++){
                KruskalVertex v = (KruskalVertex) myTable.find(neighborObjects[i]).value();
                Edge2 newEdge = new Edge2(k,v,weights[i]);
                edgeList.enqueue(newEdge);
            }
        }
        ListSorts.quickSort(edgeList);
        placeEdges(edgeList,myGraph,vertices.length);
        return myGraph;
    }

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

    public static void test(){
        LinkedQueue newQueue = new LinkedQueue();
        newQueue.enqueue(new Edge2(new Integer(1),new Integer(3), 5));
        newQueue.enqueue(new Edge2(new Integer(6),new Integer(9), 3));
        newQueue.enqueue(new Edge2(new Integer(5),new Integer(0), 12));
        ListSorts.quickSort(newQueue);
        System.out.println(newQueue.toString());

    }



    public static void main(String[] args){
        //test();
    }

}
