
package rational;

/**
 * class Rational,consists of a numerator and denominator which defines rational
 * number and methods of rational numbers.
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 14/03/19
 */
public class Rational {

    private int _num;
    private int _denom;

    /**
     * Construct a zero Rational number.
     */
    public Rational() {
        this._num = 0;
        this._denom = 1;
    }

    /**
     * Construct a new Rational with the given number components of rational
     * number. If denominator is illegal constructor know to built it right
     *
     * @param num - The component of rational number.
     * @param denom - The component of rational number..
     */
    public Rational(int num, int denom) {

        if (denom == 0) {
            this._denom = 1;
        }

        if (denom < 0) {
            this._num = num * -1;
            this._denom = denom * -1;
        }
        this._num = num;
        this._denom = denom;
    }

    /**
     * Construct a new Rational which is a copy of the given rational number.
     * Assumes the given color is not null.
     *
     * @param other - The Rational to copy.
     */
    public Rational(Rational other) {
        this._num = other._num;
        this._denom = other._denom;

    }

    /**
     * Compares this and other rational number. Returns true if this and other
     * are the same rational number.
     *
     * @param other - The rational number to compare this rational number to.
     * @return true if the rational numbers are equal, otherwise false.
     */
    public boolean equals(Rational other) {

        if (this._num * other._denom == this._denom * other._num) {
            return true;
        }

        return false;
    }

    /**
     * Compares this and other rational number. Returns true if other rational
     * number greater than this rational number.
     *
     * @param other - The rational number to compare this rational number to.
     * @return true other greater than this, otherwise false.
     */
    public boolean greaterThan(Rational other) {

        if (this._num * other._denom > this._denom * other._num) {
            return true;
        }

        return false;
    }

    /*
     * add two rational numbers
     */
    public Rational plus(Rational other) {
        int num = (this._num * other._denom) + (this._denom * other._num);
        int denom = this._denom * other._denom;
        return new Rational(num, denom);
    }

    /*
     * minus two rational numbers
     */
    public Rational minus(Rational other) {
        int num = (this._num * other._denom) - (this._denom * other._num);
        int denom = this._denom * other._denom;
        return new Rational(num, denom);
    }

    /*
     * multiply two rational numbers
     */
    public Rational multiply(Rational other) {
        int num = this._num * other._num;
        int denom = this._denom * other._denom;
        return new Rational(num, denom);
    }

    /**
     * Returns the numerator component of rational number.
     *
     * @return The numerator component of rational number.
     */
    public int getNumerator() {
        return this._num;
    }

    /**
     * Returns the denominator component of rational number.
     *
     * @return The denominator component of rational number.
     */
    public int getDenominator() {
        return this._denom;
    }

    /**
     * Returns a string representation of this RGB triplet. For example
     * (2/3,56/78)
     *
     * @return string representation of rational number.
     */
    public String toString() {
        if (this._denom == 1 || this._num == 0) {
            return "" + this._num;
        }
        return this._num + "/" + this._denom;
    }

    /*
     * return the reduce representation of Rational number
     */
    public Rational reduce() {
        int gcd = gcd(this._denom, this._num);

        return new Rational(this._num / Math.abs(gcd), this._denom / Math.abs(gcd));
    }

    /*
     * find the gcd of two integer numbers 
     */
    private int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
