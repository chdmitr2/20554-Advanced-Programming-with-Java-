
package testsharedata;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class ShareData is  shared between several get and move threads
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class SharedData {
    // instance variables
    private int x = 0;
    private int y = 0;
    // this lock will lock an instance for the use of a single move thread or several get threads
    private Lock l;
    // condition for releasing the move lock
    private Condition c1;
    // condition for releasing the get lock
    private Condition c2;
    private boolean stop = false;

    /**
     * constructor
     */
    public SharedData(int x,int y){
        // instantiate locks and conditions
        this.x=x;
        this.y=y;
    }
    /**
     * constructor
     */
    public SharedData(){
        l = new ReentrantLock();
        c1 = l.newCondition();
        c2 = l.newCondition();
    }

    /**
     * creating the new SharedData of the two move numbers
     *
     * @return the new SharedData  move() won't execute until this one is finished
     */
    public SharedData get(){
        l.lock();
        try {
            while (!stop) {
                c2.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            stop = false;
            c1.signal();
            l.unlock();
            return (new SharedData(this.x,this.y));
        }
    }

    /**
     * move two numbers
     *
     * @param x the first to move
     * @param y the second to move
     */
    public void move(int dx, int dy){
        l.lock();
        try {
            while (stop) {
                c1.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.x = x + dx;
            this.y = y + dy;
            stop = true;
            c2.signal();
            l.unlock();
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

