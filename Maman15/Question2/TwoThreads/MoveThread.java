package testsharedata;

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
    // random number 
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
    @Override
    public void run() {
        // num of times
        for (int i = 0; i < TwoThreads.Couples; i++) {
            //generate two random numbers 
            int x = rand.nextInt(Range);
            int y = rand.nextInt(Range);
            System.out.println("First number " + x + " Second number " + y);
            //move the numbers in the SharedObject instance
            data.move(x, y);
            //put the thread to sleep for 
            try {
                Thread.sleep(TwoThreads.Pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
