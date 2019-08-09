
package hangman;

/**
 * A HangMan game
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 16/4/2019
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.border.Border;
import java.util.*;
import java.security.SecureRandom;

public class HangMan {

    HangManArea gallowsArea = null;
    //buttons and labels of application
    JButton exitButton = null;
    JButton newGameButton = null;

    JLabel wordArea = null;
    JLabel messageArea = null;
    java.util.List alphaButtonList = new ArrayList();
    Iterator alphaIterator = null;

    boolean reset = true;
    boolean disable = false;
    boolean dontWrap = false;
    boolean wrap = true;
    boolean verColumn = false;
    boolean gorColumn = false;
    boolean headDrawn = false;
    boolean bodyDrawn = false;
    boolean leftArmDrawn = false;
    boolean rightArmDrawn = false;
    boolean leftLegDrawn = false;
    boolean rightLegDrawn = false;

    String winnerMessage = "Congratulations!  You Win!";
    String losingPrefix = "Wrong!  You lose!  The answer was ";
    String currentGuess;//equals with targetWord
    String targetWord;//equals with currentGuess

    int numberWrong = 0;
    int numberOfBodyParts = 8;
    int next = 0;

    //take random word from txt.file 
    public String getRandomItem() {
        Scanner fileIn = null;
        SecureRandom randomNumbers = new SecureRandom();
        String temp = "";
        int r = randomNumbers.nextInt(35);
        int i = 0;

        try {
            fileIn = new Scanner(new FileInputStream("input/countries.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        while (i <= 35) {
            temp = fileIn.nextLine();

            if (i == r) {
                break;
            }

            i++;
        }

        fileIn.close();

        return temp;
    }

    // Start a new game of "Hangman".
    public void setUpNewGame() {
        numberWrong = 0;
        messageArea.setText("Guess country!");

        //Enable alphabet buttons
        Iterator alphaIterator = alphaButtonList.iterator();
        while (alphaIterator.hasNext()) {
            ((JButton) alphaIterator.next()).setEnabled(reset);
        }

        //Disable new game button
        newGameButton.setEnabled(disable);

        //Color the word area
        wordArea.setBackground(Color.lightGray);

        //Present the new word
        targetWord = getRandomItem();

        //Fill the word-to-guess with ???
        currentGuess = "_";
        for (int i = 0; i < targetWord.length() - 1; i++) {

            currentGuess = currentGuess.concat("_");

        }
        wordArea.setText(currentGuess);

        //Nothing is drawn yet
        verColumn = false;
        gorColumn = false;
        headDrawn = false;
        bodyDrawn = false;
        leftArmDrawn = false;
        rightArmDrawn = false;
        leftLegDrawn = false;
        rightLegDrawn = false;
        gallowsArea.repaint();

    }//start NewGame

    /**
     * Process a click on an alphabet button. Right or not?
     *
     * @param answer Will be "a", "b", "c", etc.
     */
    public void processAnswer(String answer) {
        char newCharacter = answer.charAt(0);

        // Equals characters
        // If the character matches the target, concat the new character.
        // If the character doesn't match the target, concat the character
        //    from the current guess.
        String nextGuess = "";
        boolean foundAMatch = false;
        for (int i = 0; i < targetWord.length(); i++) {
            char characterToMatch = targetWord.charAt(i);
            if (characterToMatch == newCharacter) {
                nextGuess = nextGuess.concat(String.valueOf(newCharacter));
                foundAMatch = true;
            } else {
                nextGuess = nextGuess.concat(String.valueOf(currentGuess.charAt(i)));
            }
        }//for each character
        currentGuess = nextGuess;
        wordArea.setText(currentGuess);

        // We have a winner?
        if (currentGuess.equals(targetWord)) {
            //Disable the buttons
            Iterator alphaIterator = alphaButtonList.iterator();
            while (alphaIterator.hasNext()) {
                ((JButton) alphaIterator.next()).setEnabled(disable);
            }
            messageArea.setText(winnerMessage);
            newGameButton.setEnabled(reset);
            exitButton.setEnabled(reset);
        } // Wrong Answer
        //   Set out a new body part to be drawn by repaint()
        else {
            if (!foundAMatch) {
                numberWrong++;
                switch (numberWrong) {
                    case 1: {
                        verColumn = true;
                        break;
                    }
                    case 2: {
                        gorColumn = true;
                        break;
                    }
                    case 3: {
                        headDrawn = true;
                        break;
                    }
                    case 4: {
                        bodyDrawn = true;
                        break;
                    }
                    case 5: {
                        leftArmDrawn = true;
                        break;
                    }
                    case 6: {
                        rightArmDrawn = true;
                        break;
                    }
                    case 7: {
                        leftLegDrawn = true;
                        break;
                    }
                    case 8: {
                        rightLegDrawn = true;
                        break;
                    }
                    default:
                        System.out.println("You lose this game!");
                }
                // Repaint the gallows area JPanel
                gallowsArea.repaint();
            }
            // Is the game over?
            if (numberWrong >= numberOfBodyParts) {
                //Disable the buttons
                Iterator alphaIterator = alphaButtonList.iterator();
                while (alphaIterator.hasNext()) {
                    ((JButton) alphaIterator.next()).setEnabled(disable);
                }
                messageArea.setText(losingPrefix + targetWord);
                newGameButton.setEnabled(reset);
                exitButton.setEnabled(reset);
            }
        }//if else
    }//processAnswer

    /**
     * Create the North pane of the BorderLayout used by the game. The returned
     * component is a JPanel with a single JLabel where the word prompts will be
     * displayed.
     *
     * @return JPanel for use displaying game words.
     */
    public Component createNorthPane() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        pane.add(Box.createHorizontalGlue());
        wordArea = new JLabel("");
        Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
        attributes.put(TextAttribute.TRACKING, 0.5);
        Font font = new Font("Helvetica", Font.PLAIN, 24);
        Font font2 = font.deriveFont(attributes);
        wordArea.setFont(font2);
        wordArea.setBackground(Color.lightGray);
        wordArea.setForeground(Color.black);
        pane.add(wordArea);
        pane.add(Box.createHorizontalGlue());
        return pane;
    }

    /**
     * Create the West pane of the BorderLayout used by the game. The returned
     * JPanel has 9 rows and 3 columns consisting of buttons for each letter in
     * the alphabet Also creates the ActionListener for the alphabet buttons.
     *
     * @return JPanel with alphabet buttons in it.
     */
    public Component createWestPane() {
        ActionListener alphabetButtonAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton buttonPushed = (JButton) e.getSource();
                buttonPushed.setEnabled(disable);
                processAnswer(buttonPushed.getText());
            }
        };

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        pane.setLayout(gridbag);
        c.fill = GridBagConstraints.BOTH;

        JButton button = new JButton("a");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("b");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("c");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("d");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("e");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("f");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("g");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("h");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("i");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("j");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("k");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("l");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("m");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("n");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("o");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 4;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("p");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("q");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 5;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("r");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 5;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("s");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 6;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("t");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 6;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("u");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 6;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("v");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 7;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("w");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 7;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("x");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 2;
        c.gridy = 7;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("y");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 8;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        button = new JButton("z");
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 8;
        gridbag.setConstraints(button, c);
        button.addActionListener(alphabetButtonAction);
        pane.add(button);
        alphaButtonList.add(button);

        alphaIterator = alphaButtonList.iterator();
        while (alphaIterator.hasNext()) {
            ((JButton) alphaIterator.next()).setEnabled(disable);
        }
        return pane;
    }

    /**
     * Create the South pane of the BorderLayout used by the game. The returned
     * component is a JPanel with a single JLabel where the winning and losing
     * messages will be displayed.
     *
     * @return JPanel for use displaying winning and losing messages.
     */
    public Component createSouthPane() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pane.add(Box.createHorizontalGlue());

        messageArea = new JLabel("Press New Game");
        messageArea.setFont(new Font("Helvetica", Font.PLAIN, 28));
        messageArea.setBackground(Color.lightGray);
        messageArea.setForeground(Color.red);
        pane.add(messageArea);
        pane.add(Box.createHorizontalGlue());
        return pane;
    }

    /**
     * Create the Center pane of the BorderLayout used by the game. The returned
     * component is a JPanel where the victim will be hanged.
     *
     * @return JPanel for use displaying gallows.
     */
    public Component createCenterPane() {
        // Pass the reference to this instance of the game so that
        //   the repaint() method can find out what to draw
        gallowsArea = new HangManArea(this);
        return gallowsArea;
    }

    /**
     * Create the East pane of the BorderLayout used by the game. The returned
     * JPanel contains New Game and Exit buttons arranged vertically in a
     * BoxLayout form.
     *
     * @return JPanel with control buttons set in a BoxLayout.
     */
    public Component createEastPane() {
        ActionListener controlButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton buttonPushed = (JButton) e.getSource();
                if (buttonPushed.getText().equals("New Game")) {
                    setUpNewGame();
                } else {
                    System.exit(0);
                }
            }//actionPerformed
        };//controlButtonListener

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(controlButtonListener);
        pane.add(newGameButton);

        pane.add(Box.createVerticalGlue());

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(controlButtonListener);
        pane.add(exitButton);

        return pane;
    }

    /**
     * Create the components for the game GUI. The returned JPanel will have all
     * the elements of the GUI arranged in a BorderLayout.
     *
     * @return JPanel with GUI elements arranged in a BorderLayout.
     */
    public Component createComponents() {
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        pane.setLayout(new BorderLayout());
        pane.add(createNorthPane(), BorderLayout.NORTH);
        pane.add(createWestPane(), BorderLayout.WEST);
        pane.add(createSouthPane(), BorderLayout.SOUTH);
        pane.add(createCenterPane(), BorderLayout.CENTER);
        pane.add(createEastPane(), BorderLayout.EAST);

        return pane;
    }

}
