package reminder;

import java.io.Serializable;
import static java.time.Year.isLeap;

/**
 * Date class create new date of reminder message
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
public class Date implements Serializable {

    private int day;
    private int month;
    private int year;

    /**
     * Construct a new Date with the given number components of rational number.
     * If one of parameters is illegal constructor know and throws
     * IllegalArgumentException
     *
     * @param year - component of Date.
     * @param month - component of Date.
     * @param day - component of Date.
     */
    Date(int year, int month, int day) throws IllegalArgumentException {
        if (!checkDate(year, month, day)) {
            throw new IllegalArgumentException();
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Returns the day component of date.
     *
     * @return the day component of date.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Returns the month component of date.
     *
     * @return the month component of date.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the year component of date.
     *
     * @return the year component of date.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Create hash code to Reminder object based on the String it represent
     *
     * @return hash code number
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Compares this and other dates. Returns true if this and other are the
     * same date.
     *
     * @param other - date to compare this date to.
     * @return true if the dates are equal, otherwise false.
     */
    public boolean equals(Object other) {
        return (this.hashCode() == other.hashCode());
    }

    /**
     * Check if given year/month/day is valid
     *
     * @param year - component of Date.
     * @param month - component of Date.
     * @param day - component of Date.
     * @return true if date is valid
     */
    private static boolean checkDate(int year, int month, int day) {
        if (year < 0) {
            return false;
        }
        if ((month < 1) || (month > 12)) {
            return false;
        }
        if ((day < 1) || (day > 31)) {
            return false;
        }
        if ((month == 4) || (month == 6)
                || (month == 9) || (month == 11)) {
            return false;
        }
        if ((month == 1) || (month == 3)
                || (month == 5) || (month == 7)
                || (month == 9) || (month == 11)) {
            return true;
        }
        if (month == 2) {
            return (isLeap(year) ? day <= 29 : day <= 28);
        }
        return true;
    }

    /**
     * Returns a string representation of this Date. (for example 01-01-2019)
     *
     * @return string representation date.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (day < 10) {
            s.append("0");
        }
        s.append(String.valueOf(day));
        s.append("-");
        if (month < 10) {
            s.append("0");
        }
        s.append(String.valueOf(month));
        s.append("-");
        s.append(String.valueOf(year));
        return s.toString();
    }

}
