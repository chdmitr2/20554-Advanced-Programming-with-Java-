
package persontest;

/**
 * class Person class create person and doing various methods like compare
 * heights,print information about object etc. consists of a name,id,heights
 * which defines person
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
public class Person implements Comparable<Person> {

    private String name;
    private String id;
    private double height;

    /**
     * Construct a new Person
     *
     * @param name - The component of person.
     * @param id - The component of person.
     * @param height - The component of person.
     */
    public Person(String name, String id, double height) {
        this.name = name;
        this.id = id;
        this.height = height;
    }
    
    /**
     * Construct a new Person only for method of
     * limited height
     * @param height - The component of person.
     */
    public Person(double height) {
        this.height = height;
    }
    /**
     * Compares this and other heights persons. Returns 0 if this and other
     * are the same,1 if this great,-1 if other great.
     *
     * @param other - The person height to compare this person height.
     
     */
    public int compareTo(Person other) {
        if (this.height > other.height) {
            return 1;
        } else if (this.height < other.height) {
            return -1;
        }
        return 0;
    }
     /**
     * Returns a string representation of person. 
     * For example (Sophia id: 10002 height: 169.0)
     * @return string representation of person.
     */
    public String toString() {

        return name + " id: " + id + " height: " + height;
    }

}
