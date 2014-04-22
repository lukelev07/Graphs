/* HashTableChained.java */

package dict;

import java.util.logging.Level;
import java.util.logging.Logger;
import list.*;



/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
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
     *  entries.
     **/

    public HashTableChained(int sizeEstimate) {
        if (bigN <= 0) {
            bigN += sizeEstimate;
        }
        while (!primeNumber(bigN)) {
            bigN++;
        }
        table = new DList[bigN];
        for (int i = 0; i < table.length; i++) {
            DList insertion = new DList();
            this.table[i] = insertion;
        }
    }

    /**
     *  Construct a new empty hash table with a default size.
     **/

    public HashTableChained() {
        this(103);
    }

    /**
     *  Returns a boolean signifying the "primeness" of the given number
     **/

    public boolean primeNumber(int j) {
        for (int i = 2; i < (j/2); i++) {
            if (j%i == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     **/

    public int compFunction(int code) {
        long toReturn;
        toReturn = ((127 * code + 65993) % 1999993) % table.length;
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
        return size;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        return size==0;
    }

    /**
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        Entry toInsert = new Entry();
        toInsert.key = key;
        toInsert.value = value;
        int hash = compFunction(key.hashCode());
        this.table[hash].insertFront(toInsert);
        size++;
        if(size/bigN > .75) {
            HashTableChained newOne = new HashTableChained(2*this.size);
            for (DList dlist : table) {
                DListNode current = (DListNode)dlist.front();
                while (current.isValidNode()) {
                    try {
                        newOne.insert(((Entry)(current.item())).key, ((Entry)(current.item())).value);
                        current = (DListNode)current.next();
                    } catch (InvalidNodeException ex) {
                        System.out.println("TOO FAR");
                    }
                }
            }
        }
        return toInsert;
    }

    /**
     *  Search for an entry with the specified key.
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
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
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     *  Remove an entry with the specified key.
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {    int correctIndex = compFunction(key.hashCode());
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
        for (int i = 0; i < table.length; i++) {
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

}
