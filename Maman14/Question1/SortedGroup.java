
package persontest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * class SortedGroup class create array list of persons and doing various
 * methods like add,remove etc.
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
public class SortedGroup<T extends Comparable<T>> {

    private ArrayList<T> arrayList;

    /**
     * Construct a new empty array list
     *
     * @param arrayList - object with infomation about each person.
     */
    public SortedGroup() {
        arrayList = new ArrayList<T>();
    }

    /**
     * Insert element to the array and sorted where
     *
     * @param element - enter in array
     */
    public void add(T element) {
        arrayList.add(element);
        Collections.sort(arrayList);
    }

    /**
     * Delete element from current array,if element repeat twice or more,remove
     * all variations
     *
     * @param element - person in array list
     */
    public int remove(T element) {
        int count = 0;
        ArrayList<T> arrayList2 = new ArrayList<>();;
        for (T arrayElement : arrayList) {
            if (element.equals(arrayElement)) {
                count++;
            } else {
                arrayList2.add(arrayElement);
            }
        }
        arrayList = arrayList2;
        return count;
    }

    /**
     * @return the iterator
     */
    public Iterator<T> iterator() {
        return arrayList.iterator();
    }

}
