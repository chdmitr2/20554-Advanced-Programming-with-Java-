package threadtest;

/**
 * class MyThread class built a new thread put in random number
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class MyThread extends Thread {

    private int _current;
    private int _left;
    private int _right;
    private int _rand;

    /**
     * Construct a new Thread with the given number components of rational
     * number. If denominator is illegal constructor know to built it right
     *
     * @param left - left neighboring index of random number.
     * @param current - index of random number.
     * @param right - right neighboring index of random number.
     * @param rand - random value of this thread.
     * @param size - size of array random numbers.
     *
     */
    public MyThread(int left, int current, int right, int rand, int size) {
        this._rand = rand;
        this._current = current;
        //Circle update
        if (left < 0) {
            this._left = size - 1;
            this._right = right;
        }
        if (right > size - 1) {
            this._right = 0;
            this._left = size - 2;
        } else if (left >= 0 && right < size) {
            this._right = right;
            this._left = left;
        }
    }

    /**
     * Returns the random number.
     *
     * @return random number.
     */
    public int getRandom() {
        return _rand;
    }

    /**
     * Returns index of current number.
     *
     * @return index of current number.
     */
    public int getCurrent() {
        return _current;
    }

    /**
     * Returns left neighboring index of current number.
     *
     * @return left neighboring index of current number.
     */
    public int getLeft() {
        return _left;
    }

    /**
     * Returns right neighboring index of current number.
     *
     * @return right neighboring index of current number.
     */
    public int getRight() {
        return _right;
    }

    /**
     * Sets the random value of this thread.
     *
     * @param num - Random value to set.
     */
    public void setRandom(int num) {
        this._rand = num;
    }
//Thread was constructed using a separate  run object

    public void run() {
        try {
            Threads threads = new Threads(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
