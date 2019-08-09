package reminder;
import javax.swing.*;
/**
 * Reminder class create application that built reminder wherein you can save
 * your comments in specific date.
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
public class Reminder {

    public static void main(String[] args) {
        ReminderFrame reminderApp = new ReminderFrame();
        reminderApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reminderApp.setSize(400,500);
        reminderApp.setVisible(true);
    }
}