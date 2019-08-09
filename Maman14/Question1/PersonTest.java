
package persontest;

import java.util.Iterator;

/**
 * class PersonTest class test persons and doing various methods
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
public class PersonTest {

    public static void main(String[] args) {
        int t1, t2;
        SortedGroup<Person> sGroup = new SortedGroup<>();
        Person p1 = new Person("Oliver", "10001", 183);
        Person p2 = new Person("Sophia", "10002", 169);
        Person p3 = new Person("Emily", "10003", 172);
        Person p4 = new Person("Oscar", "10004", 185);
        Person p5 = new Person("Lily ", "10005", 165);
        Person p6 = new Person("Harry ", "10006", 175);
        Person p7 = new Person("Charlie", "10007", 173);
        Person p8 = new Person("Jack ", "10008", 177);
        Person limit = new Person(175);
        sGroup.add(p1);
        sGroup.add(p2);
        sGroup.add(p3);
        sGroup.add(p5);
        sGroup.add(p4);
        sGroup.add(p5);
        sGroup.add(p6);
        sGroup.add(p7);
        sGroup.add(p8);
        System.out.println("Sorted group:");
        printGroup(sGroup);
        System.out.println("\n");
        t1 = sGroup.remove(p4);
        System.out.println("Person " + p4.toString() + " was removed "
                + t1 + " times from list");
        t2 = sGroup.remove(p5);
        System.out.println("Person " + p5.toString() + " was removed "
                + t2 + " times from list");
        System.out.println("\n");
        System.out.println("Sorted group after removing "
                + p4.toString() + " and " + p5.toString() + " : ");
        printGroup(sGroup);
        System.out.println("\n");
        SortedGroup<Person> sGroup2;
        sGroup2 = NewSortedGroup.reduce(sGroup, limit);
        System.out.println("Sorted group with height limit:");
        printGroup(sGroup2);
    }

    private static void printGroup(SortedGroup<Person> item) {
        for (Iterator<Person> it = item.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }
}
