
package CountryGuess;

import java.util.Scanner;

/**
 * class Game,consists of a methods of the game
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 14/3/2019
 */
public class Game {

    private static int numberOfAllGuesses;
    private static int numberOfRightGuess;
    private char[] _country;
    private String _letters;

    //constructor that initialize game
    public Game(char[] country) {
        this._country = country;
        this._letters = "a b c d e f g h i j k l m n o p q r s t u v w x y z";

    }

    //method get the random country and start game
    public void startGame() {

        Scanner input = new Scanner(System.in);
        boolean[] mask = new boolean[_country.length];
        numberOfRightGuess = 0;
        numberOfAllGuesses = 0;
        while (numberOfRightGuess != _country.length) {
            System.out.print("Choose letter for your guess: ");
            System.out.print(_letters + "\n");
            System.out.print("      Enter a letter in word: ");

            // Print out result
            for (int j = 0; j < _country.length; j++) {

                if (mask[j]) {
                    System.out.print(" " + _country[j] + " ");
                } else {
                    System.out.print(" _ ");
                }

            }

            System.out.print(" -> ");
            char guess = input.next().charAt(0);
            char ch = guess;
            String newStr = _letters.replace(ch, '\0');
            _letters = newStr;
            numberOfAllGuesses++;
            // Checking
            boolean wrong = true;
            boolean dublicate = false;

            for (int j = 0; j < _country.length; j++) {
                if (_country[j] == guess) {
                    if (mask[j] != true) {
                        mask[j] = true;
                        numberOfRightGuess++;
                    } else {
                        dublicate = true;
                        break;
                    }
                    wrong = false;
                }
            }
            if (wrong) {

            }
            if (dublicate) {
                System.out.println(guess + " is already in the word");

            }

        }

    }

    //static method to get static numberOfAllGuesses value
    public static int getNumberOfAllGuesses() {
        return numberOfAllGuesses;

    }
}//end class Game
