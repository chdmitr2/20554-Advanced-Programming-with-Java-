
package rational;

/**
 * class Rational class test rational numbers and doing various methods like
 * add,equals, etc.
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 14/03/19
 */
import java.util.Scanner;

public class RationalMain {

    //testings of rationals numbers
    public static void main(String[] args) {
        int den1 = 0;
        int den2 = 0;
        Scanner scan = new Scanner(System.in);
        Rational Rational1, Rational2, Rational3, Rational4, RationalZero;
        System.out.println("Please enter the numerator1 "
                + "and denomerator1 for your first rational number:");
        int num1 = scan.nextInt();
        while (den1 == 0) {
            den1 = scan.nextInt();
            if (den1 == 0) {
                System.out.println("Enter The Correct Number ! ");
            }
        }
        Rational1 = new Rational(num1, den1);
        System.out.println("Your first rational number is: "
                + Rational1.toString());
        System.out.println("Please enter the numerator2 "
                + "and denomerator2 for your second rational number:");
        int num2 = scan.nextInt();
        while (den2 == 0) {
            den2 = scan.nextInt();
            if (den2 == 0) {
                System.out.println("Enter The Correct Number ! ");
            }
        }
        Rational2 = new Rational(num2, den2);
        System.out.println("Your second rational number is: "
                + Rational2.toString());
        RationalZero = new Rational();
        System.out.println(RationalZero.toString());
        Rational4 = new Rational(Rational1);
        System.out.println(Rational4.getNumerator());
        System.out.println(Rational4.getDenominator());
        Rational3 = Rational1.plus(Rational2).reduce();
        System.out.println(Rational1.toString() + " + "
                + Rational2.toString() + " = "
                + Rational3.toString());

        Rational3 = Rational1.minus(Rational2).reduce();
        System.out.println(Rational1.toString() + " - "
                + Rational2.toString() + " = "
                + Rational3.toString());

        Rational3 = Rational1.multiply(Rational2).reduce();
        System.out.println(Rational1.toString() + " * "
                + Rational2.toString() + " = "
                + Rational3.toString());

        if (Rational1.greaterThan(Rational2)) {
            System.out.println(Rational1.toString() + " > "
                    + Rational2.toString());
        } else {
            System.out.println(Rational1.toString() + " !> "
                    + Rational2.toString());
        }
        if (Rational1.equals(Rational2)) {
            System.out.println(Rational1.toString() + " = "
                    + Rational2.toString());
        } else {
            System.out.println(Rational1.toString() + " != "
                    + Rational2.toString());
        }
        if (Rational1.equals(Rational4)) {
            System.out.println(Rational1.toString() + " = "
                    + Rational4.toString());
        } else {
            System.out.println(Rational1.toString() + " != "
                    + Rational2.toString());
        }

    }

}//end class RationalMain
