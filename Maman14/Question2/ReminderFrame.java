package reminder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * ReminderFrame class create sectors in frame like button sector,date
 * sector,instruction sector and etc. This class have methods to open and save
 * file.
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 10/05/19
 */
class ReminderFrame extends JFrame {

    private HashMap<Date, String> reminder = new HashMap<>();
    private JComboBox<Integer> bDay;
    private JComboBox<String> bMonth;
    private JComboBox<Integer> bYear;
    private JTextArea textArea;
    private JLabel label1, label2, label3, label4, label5;
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;

    ReminderFrame() {
        super("Reminder");
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();
        // create the menu bar
        JMenuBar menu = buildMenuBar();
        setJMenuBar(menu);

        // create the instruction sector
        JPanel labels = new JPanel();
        label1 = new JLabel("1.Select a date that you need,write reminder message in note");
        label2 = new JLabel("2.Click on button Save and your reminder message will be saved");
        label3 = new JLabel("3.Select a date,click Load to read reminder message from note");
        label4 = new JLabel("4.Click on File to save or load file or open new reminder message");
        label5 = new JLabel("5.For Exit you need to save reminder it will be saved in file.ser");
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);
        labels.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Instruction"));
        constraints.weightx = 1;
        constraints.weighty = 40;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(labels, 0, 0, 1, 1);

        // create the date sector
        JPanel datePanel = buildDatePanel();
        datePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Date"));
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(datePanel, 1, 0, 1, 1);

        // create the note sector
        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Note"));
        constraints.weightx = 1;
        constraints.weighty = 80;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(textArea, 2, 0, 1, 1);

        // create the buttons sector
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0;
        JPanel buttonsPanel = buildButtonsPanel();
        addComponent(buttonsPanel, 3, 0, 1, 1);
    }

    /**
     * Add component to the Grid Bag Layout
     *
     * @param row - component of Grid Bag Layout.
     * @param column - component of Grid Bag Layout.
     * @param width - component of Grid Bag Layout.
     * @param height - component of Grid Bag Layout.
     */
    private void addComponent(Component component, int row, int column, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        add(component);
    }

    /**
     * Open Serialized file and load it to a HashMap object
     */
    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser(); // open file chooser window with serialized files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized File", "ser");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = fileChooser.getSelectedFile();
        if (file != null && Files.exists(file.toPath())) {
            try {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()));
                reminder = (HashMap<Date, String>) ois.readObject();
                textArea.setText("");
            } catch (IOException | ClassNotFoundException ioException) {
                JOptionPane.showMessageDialog(null, "Error opening file. Please try again",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Save the HashMap to a Serialized file
     */
    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                return;
            }
            if (!file.getName().toLowerCase().endsWith(".ser")) {
                file = new File(file.getParentFile(), file.getName() + ".ser");
            }
            try {
                FileOutputStream fout = new FileOutputStream(file.getName());
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(reminder);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Create date panel with day, month and year
     *
     * @return JPanel
     */
    private JPanel buildDatePanel() {
        JPanel panel = new JPanel();
        Integer[] oneOfDays = new Integer[31];
        for (int i = 0; i < oneOfDays.length; i++) {
            oneOfDays[i] = i + 1;
        }
        bDay = new JComboBox<>(oneOfDays);
        String[] oneOfMonths = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
        bMonth = new JComboBox<>(oneOfMonths);
        Integer[] oneOfYears = new Integer[31];
        // the year picker is between 2019 - 2049
        for (int i = 0; i < oneOfYears.length; i++) {
            oneOfYears[i] = i + 2019;
        }
        bYear = new JComboBox<>(oneOfYears);

        panel.add(bDay);
        panel.add(bMonth);
        panel.add(bYear);
        return panel;
    }

    /**
     * Create buttons panel with save and load buttons for reminder messages
     *
     * @return JPanel
     */
    private JPanel buildButtonsPanel() {
        JPanel panel = new JPanel();
        JButton saveButton = new JButton("Save");
        panel.add(saveButton);
        // ActionListener when clicking on the save buttons
        saveButton.addActionListener(e -> {
            try {
                // create reminder object with the selected date
                Date reminderDate = new Date(Integer.parseInt(bYear.getSelectedItem().toString()),
                        (bMonth.getSelectedIndex() + 1),
                        Integer.parseInt(bDay.getSelectedItem().toString()));
                // store this date and message on the reminder HashMap and show the user a message
                reminder.put(reminderDate, textArea.getText());
                JOptionPane.showMessageDialog(null, "Your reminder was saved!\n " + textArea.getText(),
                        reminderDate.toString(), JOptionPane.PLAIN_MESSAGE);
                // if illegal date was picked show the user a message
            } catch (IllegalArgumentException illegalexc) {
                JOptionPane.showMessageDialog(null, "Invalid date", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }
        );
        JButton loadButton = new JButton("Load");
        panel.add(loadButton);
        // ActionListener when clicking on the load buttons
        loadButton.addActionListener(e -> {
            try {
                // create reminder object with the selected date
                Date reminderDate = new Date(Integer.parseInt(bYear.getSelectedItem().toString()),
                        (bMonth.getSelectedIndex() + 1),
                        Integer.parseInt(bDay.getSelectedItem().toString()));
                // show the comment that related to this reminder
                textArea.setText(reminder.get(reminderDate));
            } catch (NullPointerException ex) { // handle situation that the user load non .ser file
                JOptionPane.showMessageDialog(null, "Please make sure you opened '.ser' file only",
                        "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }
        );
        return panel;
    }

    /**
     * Create menu bar with file section that contain new, save, load and exit
     * option
     *
     * @return JPanel
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); //create file menu
        JMenuItem newOption = new JMenuItem("New");
        fileMenu.add(newOption); // add save to file menu
        newOption.addActionListener(e -> {
            // show warning to the user when clicking on "New" option
            int message = JOptionPane.showConfirmDialog(null, "Save current project?");
            if (message == JOptionPane.YES_OPTION) {
                saveToFile();
            }
            if (message == JOptionPane.NO_OPTION || message == JOptionPane.YES_OPTION) {
                textArea.setText("");
                reminder = new HashMap<>();
            }
        });
        JMenuItem saveOption = new JMenuItem("Save");
        fileMenu.add(saveOption); // add save to file menu
        saveOption.addActionListener(
                e -> saveToFile()
        );
        JMenuItem loadOption = new JMenuItem("Load");
        fileMenu.add(loadOption); // add save to file menu
        loadOption.addActionListener(e -> loadFile()
        );
        fileMenu.addSeparator();
        JMenuItem exitOption = new JMenuItem("Exit");
        fileMenu.add(exitOption); // add exit to file menu
        exitOption.addActionListener(
                e -> System.exit(0)
        );

        menu.add(fileMenu);
        return menu;
    }

}
