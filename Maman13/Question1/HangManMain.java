
package hangman;
/**
 * A HangMan game
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 16/4/2019
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class HangManMain {
     public static void main(String[] args) {

        //Create the top-level container and add contents to it.
        JFrame frame = new JFrame("Hangman");
        frame.setSize(new Dimension(640, 480));
        HangMan hm = new HangMan();
        Component contents = hm.createComponents();

        frame.getContentPane().add(contents);

        //Finish setting up the frame, and show it.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
    
}
