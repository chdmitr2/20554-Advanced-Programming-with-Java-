
package persontest;

/**
 * class NewSortedGroup have method that great new sorted group
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
import java.util.Iterator;

/**
 * NewSortedGroup implements the Comparable interface and return the new group
 * which their elements of group great than given element
 *
 * @param element compare with other element of group
 * @return group of elements which great than given element
 */
public class NewSortedGroup {

    public static <T extends Comparable<T>> SortedGroup<T> reduce(SortedGroup<T> sGroup, T element) {
        SortedGroup<T> result = new SortedGroup<>();

        for (Iterator<T> it = sGroup.iterator(); it.hasNext();) {
            T current = it.next();
            if ((current.compareTo(element) == -1)
                    || (current.compareTo(element) == 0)) {

            } else {
                result.add(current);
            }
        }

        return result;
    }
}
