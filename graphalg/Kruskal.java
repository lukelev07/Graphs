/* Kruskal.java */

package graphalg;

import graph.*;
import graphalg.queues.LinkedQueue;
import list.DList;
import list.List;
import set.*;


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
        for(Object o:vertices){
            myGraph.addVertex(o);
            Neighbors neighbors = g.getNeighbors(o);
            Object[] neighborObjects = neighbors.neighborList;
            int[] weights = neighbors.weightList;
            for(int i = 0; i < neighborObjects.length; i++){
                Edge2 newEdge = new Edge2(o,neighborObjects[i],weights[i]);
                edgeList.enqueue(newEdge);
            }
        }
        ListSorts.quickSort(edgeList);
        return myGraph;
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
