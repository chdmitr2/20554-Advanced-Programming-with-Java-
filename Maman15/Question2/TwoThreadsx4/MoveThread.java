
package testsharedata4;

import java.util.Random;

/**
 * class MoveThread is a thread which generates random numbers and move in a
 * SharedData
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
class MoveThread extends Thread {

    // range of numbers 
    private static final int Range = 100;
    private SharedData data;
    private Random rand;

    /**
     * constructor
     *
     * @param data SharedData instance to be shared
     */
    MoveThread(SharedData data) {
        this.data = data;
        rand = new Random();
    }

    /**
     * This method is executed when start() is called
     */
    public void run() {

        for (int i = 0; i < TwoThreadsx4.Couples; i++) {
            //generate two random  numbers
            int x = rand.nextInt(Range);
            int y = rand.nextInt(Range);
            System.out.println("First number " + x + " Second number " + y);
            //move the numbers in the SharedObject instance
            data.move(x, y);
            //put the thread to sleep
            try {
                Thread.sleep(TwoThreadsx4.Pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
