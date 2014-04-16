/* HashTableChained.java */

package dict;

import java.util.logging.Level;
import java.util.logging.Logger;
import list.*;


/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  
  public DList[] table;
  public int size = 0;
  public int bigN = 0;
  


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
      if (bigN <= 0) {
          //System.out.println("here");
          bigN += sizeEstimate;
      }
      while (!primeNumber(bigN)) {
          bigN++; //increment by one until prime
      }
      table = new DList[bigN]; //bunch of DList2s
      for (int i = 0; i < table.length; i++) {
          //System.out.println( "hello " + i);
          DList insertion = new DList();
          this.table[i] = insertion;
      }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
     this(103);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/
  
  public boolean primeNumber(int j) {
      for (int i = 2; i < (j/2); i++) { //halfway trick
          if (j%i == 0) { //exactly divisible, not prime
              return false;
          }
      }
      return true;
  }

  int compFunction(int code) {
    // Replace the following line with your solution.
    long toReturn;
      toReturn = ((127 * code + 65993) % 1999993) % table.length; //double modulus method
        if (toReturn < 0)
            toReturn += table.length;
        return (int) toReturn;  
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry toInsert = new Entry();
    toInsert.key = key;
    toInsert.value = value;
    int hash = compFunction(key.hashCode());
    //System.out.println("hash " + hash);
    this.table[hash].insertFront(toInsert);
    size++;
    if(size/bigN > .75) { //make a new one! takes forever lol
        HashTableChained newOne = new HashTableChained(2*this.size);
        for (DList dlist : table) {
            DListNode current = (DListNode)dlist.front();
            int i = 0;
            while (current.isValidNode()) {
                try {
                    //System.out.println("count " + i);
                    newOne.insert(((Entry)(current.item())).key, ((Entry)(current.item())).value);
                    current = (DListNode)current.next();
                    i++;
                } catch (InvalidNodeException ex) {
                    //System.out.println("TOO FAR");
                }
            }
        }
    }
    return toInsert; //why...
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int correctIndex = compFunction(key.hashCode());
    DList correctDList = this.table[correctIndex];
    if (correctDList == null) {
        return null;
    }
    else if (correctDList != null) {
        DListNode current = (DListNode)correctDList.front();
        try{
            while (!((Entry)(current.item())).key().equals(key)) {
                current = (DListNode)current.next();
            }
            return (Entry)current.item();
        }
        catch (InvalidNodeException e1) {
            //System.out.println("NOT FOUND");
            return null;
        }
    }
    else {
        return null;
    }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int correctIndex = compFunction(key.hashCode());
    DList correctDList = this.table[correctIndex];
    if (correctDList == null) {
        return null;
    }
    else if (correctDList != null) {
        try {
            DListNode current = (DListNode)correctDList.front();
            while(!((Entry)(current.item())).key().equals(key)) {
                current = (DListNode)current.next();
            }
            if (current == null) {
                return null;
            }
            Entry toReturn = (Entry)current.item();
            current.remove();
            size--;
            return toReturn;
            }
        catch (InvalidNodeException e3) {
            //System.out.println("INVALID NODE");
            return null;
        }
    }
    else{
        return null;
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
      for (int i = 0; i < table.length; i++) {
          //System.out.println( "hello " + i);
          DList insertion = new DList();
          this.table[i] = insertion;
      }
      this.size = 0;
  }
  
  public String toString() {
      String toReturn = "";
      for (int i = 0; i < this.table.length; i++) {
          if (this.table[i] instanceof DList) {
            toReturn += "\n" + i + " " +this.table[i].toString();
          }
          else{
              toReturn += "\n" + i + " ";
          }
      }
      return toReturn;
  }
  
  public static void main(String[] argv) {
	  	HashTableChained table = new HashTableChained();
	  	table.insert("test1", "hi");
	  	table.insert("wolf", "howl");
	  	table.insert("fox", "idkbro");
	  	table.insert("test2", "bye");
	  	table.insert(12, 10);
	  	Entry a = table.find("test1");
	  	Entry b = table.find("wolf");
	  	table.remove("fox");
	  	Entry c = table.find("fox");
                System.out.println(table);
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                table.insert("test", "test1");
                System.out.println(table);
                table.remove("test");
                System.out.println(table);
	  	System.out.println("pls be size 4: "+table.size());
  }


}
