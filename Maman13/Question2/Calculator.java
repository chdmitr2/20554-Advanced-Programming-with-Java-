
package calculator;

import javax.swing.JFrame;

/**
 * A Calculator
 * After every operation need to do reset with button "C"
 * After that then press "=" need reset "C"
 * Example: 6/2*3=9.0 press "C" and continue with new expression 
 * If want negative number,need in begin press number and when "+/-"
 * Example:(-6)*56 press 6 then "+/-" and then *56
 * @author Dmitriy Chudnovsky 324793900
 * @since 16/4/2019
 */
public class Calculator {
    public static void main(String[] args) {
        //built frame for calculator and adding panels with CalcPanel();
        CalcPanel panel = new CalcPanel();
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(380,225);
        frame.setVisible(true);
    }
    
}
