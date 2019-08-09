
package calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * class CalcPanel,consists of a methods of JPanel
 * and operations of calculator
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 16/4/2019
 */
public class CalcPanel extends JPanel {

    private JButton multiply, division, plus, minus, negative, point, equal, reset;
    private JButton zero, one, two, three, four, five, six, seven, eight, nine;
    private JLabel label1, label2;
    private JTextField txt = new JTextField(20);

    public CalcPanel() {
        txt.setText("0");
        txt.setBounds(50, 100, 200, 30);
        add(txt, BorderLayout.NORTH);
        // line1
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        division = new JButton("/");
        reset = new JButton("C");
        // line2
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        multiply = new JButton("*");
        negative = new JButton("+/-");
        // line3
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        minus = new JButton("-");
        plus = new JButton("+");
        // line4
        zero = new JButton("0");
        point = new JButton(".");
        equal = new JButton("=");
        label1 = new JLabel("           C   A");
        label2 = new JLabel("S   I   O");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 5, 10, 10));
        //  adding the button to panel 
        // line1
        panel2.add(seven);
        panel2.add(eight);
        panel2.add(nine);
        panel2.add(multiply);
        panel2.add(reset);
        // line2
        panel2.add(four);
        panel2.add(five);
        panel2.add(six);
        panel2.add(division);
        panel2.add(negative);
        // line3
        panel2.add(one);
        panel2.add(two);
        panel2.add(three);
        panel2.add(minus);
        panel2.add(plus);
        // line4        
        panel2.add(zero);
        panel2.add(point);
        panel2.add(equal);
        panel2.add(label1);
        panel2.add(label2);
        add(panel2, BorderLayout.CENTER);
        // create new ButtonListener for button event press
        ButtonListener press = new ButtonListener();
        seven.addActionListener(press);
        eight.addActionListener(press);
        nine.addActionListener(press);
        division.addActionListener(press);
        reset.addActionListener(press);

        four.addActionListener(press);
        five.addActionListener(press);
        six.addActionListener(press);
        multiply.addActionListener(press);
        negative.addActionListener(press);

        one.addActionListener(press);
        two.addActionListener(press);
        three.addActionListener(press);
        minus.addActionListener(press);
        plus.addActionListener(press);

        zero.addActionListener(press);
        point.addActionListener(press);
        equal.addActionListener(press);
    }

    void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //inner class for ButtonListener for button event handling
    private class ButtonListener implements ActionListener {

        String a = "0", b = "", lastAct = "";
        int counter = -1;
        String cmdOper1 = "", cmdOper2 = ""; //keeps operator command

        public void actionPerformed(ActionEvent event) {
            // button "1" pressed
            if ((event.getActionCommand().equals("1")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "1");
            } else if (event.getActionCommand().equals("1")) {
                txt.setText("1");
            }

            // button "2" pressed
            if ((event.getActionCommand().equals("2")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "2");
            } else if (event.getActionCommand().equals("2")) {
                txt.setText("2");
            }

            // button "3" pressed
            if ((event.getActionCommand().equals("3")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "3");
            } else if (event.getActionCommand().equals("3")) {
                txt.setText("3");
            }

            // button "4" pressed
            if ((event.getActionCommand().equals("4")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "4");
            } else if (event.getActionCommand().equals("4")) {
                txt.setText("4");
            }

            // button "5" pressed
            if ((event.getActionCommand().equals("5")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "5");
            } else if (event.getActionCommand().equals("5")) {
                txt.setText("5");
            }

            // button "6" pressed
            if ((event.getActionCommand().equals("6")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "6");
            } else if (event.getActionCommand().equals("6")) {
                txt.setText("6");
            }

            // button "7" pressed
            if ((event.getActionCommand().equals("7")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "7");
            } else if (event.getActionCommand().equals("7")) {
                txt.setText("7");
            }

            // button "8" pressed
            if ((event.getActionCommand().equals("8")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "8");
            } else if (event.getActionCommand().equals("8")) {
                txt.setText("8");
            }

            // button "9" pressed
            if ((event.getActionCommand().equals("9")) && (!txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "9");
            } else if (event.getActionCommand().equals("9")) {
                txt.setText("9");
            }

            // button "0" pressed
            if ((event.getActionCommand().equals("0")) && !(txt.getText().equals("0"))) {
                txt.setText(txt.getText() + "0");
            }

            // button "+/-" pressed
            if (event.getActionCommand().equals("+/-")) {
                String num1 = txt.getText();
                Double num2 = Double.parseDouble(num1);
                num2 = (num2) * (-1);
                num1 = Double.toString(num2);
                txt.setText(num1);
            }

            lastAct = event.getActionCommand();

            // button "c" pressed
            if (event.getActionCommand().equals("C")) {
                txt.setText("0");
                counter = -1;
                a = "";
                b = "";
            }

            // button "." pressed
            if (event.getActionCommand().equals(".")) {
                String c = txt.getText();
                if (!(c.indexOf(txt.getText()) == '.')) {
                    txt.setText(txt.getText() + ".");
                }
                lastAct = event.getActionCommand();
            }

            // button '+'/'-'/'*'/'/' pressed
            if (((event.getActionCommand().equals("+")) || 
                    (event.getActionCommand().equals("-"))
                    || (event.getActionCommand().equals("*")) || 
                    (event.getActionCommand().equals("/")) || 
                    (event.getActionCommand().equals("="))) && (counter == -1)) {
                cmdOper1 = event.getActionCommand();
                a = txt.getText();
                txt.setText("");
                counter = 0;
                lastAct = event.getActionCommand();
            } else if (((event.getActionCommand().equals("+")) ||
                    (event.getActionCommand().equals("-"))
                    || (event.getActionCommand().equals("*")) ||
                    (event.getActionCommand().equals("/")) || 
                    (event.getActionCommand().equals("="))) && (counter == 0)) {
                cmdOper2 = event.getActionCommand();
                b = txt.getText();
                txt.setText("");
                a = calcOpr(a, b, cmdOper1);
                if (cmdOper2.equals("=")) {
                    txt.setText(a);
                } else {
                    counter = 1;
                }
                lastAct = event.getActionCommand();
            } else if (((event.getActionCommand().equals("+")) ||
                    (event.getActionCommand().equals("-"))
                    || (event.getActionCommand().equals("*")) ||
                    (event.getActionCommand().equals("/")) || 
                    (event.getActionCommand().equals("="))) && (counter == 1)) {
                cmdOper1 = event.getActionCommand();
                b = txt.getText();
                txt.setText("");
                a = calcOpr(a, b, cmdOper2);
                if (cmdOper1.equals("=")) {
                    txt.setText(a);
                } else {
                    counter = 0;
                }
                lastAct = event.getActionCommand();
            }
        } // end of actionPerformed method

        private Exception IOException() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        private String calcOpr(String a, String b, String cmd) {
            double numA = Double.parseDouble(a);
            double numB = Double.parseDouble(b);

            if (cmd.equals("+")) {
                numA = numA + numB;
                a = Double.toString(numA);
            }
            if (cmd.equals("-")) {
                numA = numA - numB;
                a = Double.toString(numA);
            }
            if (cmd.equals("*")) {
                numA = numA * numB;
                a = Double.toString(numA);
            }
            if (cmd.equals("/")) {
                if (numB != 0) {
                    numA = numA / numB;
                    a = Double.toString(numA);
                }
            }

            if (cmd.equals("=")) {
                txt.setText(a);
                System.out.println("The equal answer is: " + a);
            }
            return a;
        }
    }
}
