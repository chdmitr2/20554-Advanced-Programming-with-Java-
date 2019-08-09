
package CountryGuess;

/**
 * A Guess Country game
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 14/3/2019
 */
import java.security.SecureRandom;
import java.util.Scanner;

public class CountryGuess {

    public static void main(String[] args) {

        SecureRandom randomNumbers = new SecureRandom();
        Scanner input = new Scanner(System.in);
        String resumption;
        //loop do-while restart game while not pressed <n>
        do {
            // get random number 1-35
            int i = randomNumbers.nextInt(35);
            Countries[] countries = Countries.values();
            char[] country = countries[i].getCountry().toCharArray();
            //create a game
            Game game = new Game(country);
            System.out.print("Let's start to play your need guess a country\n");
            game.startGame();
            System.out.println("The word is " + countries[i].getCountry()
                    + ".You WON!!! You guessings " + Game.getNumberOfAllGuesses() + " times");
            System.out
                    .print("If you want continue a game press < y > for exit press < n >  -> ");
            resumption = input.next();
        } while ((resumption.charAt(0) != 'n'));

    }

}//end class CountryGuess


