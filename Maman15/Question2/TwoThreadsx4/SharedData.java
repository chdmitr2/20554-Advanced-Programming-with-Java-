package testsharedata4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * class ShareData is shared between several get and move threads
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class SharedData {

    // instance variables
    private int x = 0;
    private int y = 0;
    // this lock will lock an instance for the use of a single move thread or several get threads
    private ReentrantReadWriteLock l;

    /**
     * constructor
     */
    public SharedData(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor
     */
    public SharedData() {
        l = new ReentrantReadWriteLock();
    }

    /**
     * creating the new SharedData of the two move numbers
     *
     * @return the new SharedData move() won't execute until this one is
     * finished
     */
    public SharedData get() {

        l.readLock().lock();
        try {
            //return the new SharedData of the two numbers
            return (new SharedData(x, y));
        } finally {
            l.readLock().unlock();
        }
    }

    /**
     * move two numbers
     *
     * @param x the first to move
     * @param y the second to move
     */
    public void move(int dx, int dy) {

        l.writeLock().lock();
        try {
            this.x = x + dx;
            this.y = y + dy;
        } finally {
            l.writeLock().unlock();
        }
    }

    /**
     * Returns a string representation of GetThread;
     *
     * @return string representation of GetThread.
     */
    public String toString() {

        return "First number " + this.x + " Second number " + this.y;
    }

}
