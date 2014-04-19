/* ListSorts.java */
package graphalg;
import graphalg.queues.*;

public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue outer = new LinkedQueue();
    while (!q.isEmpty()) {
        try {
            LinkedQueue inner = new LinkedQueue();
            inner.enqueue(q.dequeue());
            outer.enqueue(inner);
        }
        catch (QueueEmptyException e) {
            System.out.println("wat");
        }
    }
    return outer;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
    public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
        // Replace the following line with your solution.
        LinkedQueue toReturn = new LinkedQueue();
        //System.out.println("called");
        try {
            while (!q1.isEmpty() && !q2.isEmpty()) {
                Comparable one = (Comparable) q1.front();
                Comparable two = (Comparable) q2.front();
                if (one.compareTo(two) >= 0) {
                    toReturn.enqueue(two);
                    q2.dequeue();
                } else {
                    toReturn.enqueue(one);
                    q1.dequeue();
                }
            }
        } catch (QueueEmptyException e) {
            System.out.println("wat2");
        }
        if (!q1.isEmpty()) {
            toReturn.append(q1);
        } else {
            toReturn.append(q2);
        }
        return toReturn;
    }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
    public static void partition(LinkedQueue qIn, Comparable pivot,
            LinkedQueue qSmall, LinkedQueue qEquals,
            LinkedQueue qLarge) {
        while (qIn.size() != 0) {
            try {
                Object thing = qIn.dequeue();
                if (((Comparable)thing).compareTo(pivot) < 0) {
                    qSmall.enqueue(thing);
                } else if (((Comparable)thing).compareTo(pivot) > 0) {
                    qLarge.enqueue(thing);
                } else {
                    qEquals.enqueue(thing);
                }
            } catch (QueueEmptyException e) {
                System.out.println("asdf");
            }
        }

    }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
      LinkedQueue broken = makeQueueOfQueues(q);
      //System.out.println("broken " + broken);
      LinkedQueue one = null;
      LinkedQueue two = null;
      while (broken.size() > 1) {
          try {
            one = (LinkedQueue) broken.dequeue();
            two = (LinkedQueue) broken.dequeue();
            LinkedQueue reInsert = mergeSortedQueues(one, two);
            broken.enqueue(reInsert);
          }
          catch (QueueEmptyException e) {
            System.out.println("idkman");
          }
      }
      try {
      q.append((LinkedQueue)broken.front());
      }
      catch (QueueEmptyException e1) {
          System.out.println("here24");
      }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    if (q.size() <= 1) {
        return;
    }
    int rand = (int)((Math.random() * q.size()) + 1);
    Comparable pivot = (Comparable)q.nth(rand);
    LinkedQueue left = new LinkedQueue();
    LinkedQueue same = new LinkedQueue();
    LinkedQueue right = new LinkedQueue();
    partition(q, pivot, left, same, right);
    quickSort(left);
    quickSort(right);
    q.append(left);
    q.append(same);
    q.append(right);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }


}
