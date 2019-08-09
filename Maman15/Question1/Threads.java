package threadtest;

/**
 * Class Threads class have a constructors one of them built a current thread
 * and change his value if need and second constructor built array list of all
 * threads.Have a methods by shutdowns of executor then work current thread and
 * update his value and refresh output
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Threads {

    private static ArrayList<MyThread> _myThreads;
    private MyThread _myCurrent;

    /**
     * Constructor of my current thread which if need to change a value of
     * thread and update his data
     *
     * @param _myCurrent - current thread.
     *
     */
    public Threads(MyThread myCurrent) throws Exception {

        this._myCurrent = myCurrent;
        // Update data of thtreads
        int lIndex = _myCurrent.getLeft();
        int cIndex = _myCurrent.getCurrent();
        int rIndex = _myCurrent.getRight();
        int random = _myCurrent.getRandom();
        if (random > _myThreads.get(lIndex).getRandom()
                && random > _myThreads.get(rIndex).getRandom()) {
            _myThreads.get(cIndex).setRandom(random - 1);
        } else if (random < _myThreads.get(lIndex).getRandom()
                && random < _myThreads.get(rIndex).getRandom()) {
            _myThreads.get(cIndex).setRandom(random + 1);
        }

    }

    /**
     * Threads constructor built array list of all threads of process
     *
     * @param _myThreads - array list of all threads(random numbers).
     *
     */
    public Threads(int numOfThreads) {
        _myThreads = new ArrayList<>();
        Random random = new Random();
        if (numOfThreads < 3) {
            System.out.println("ERROR Input! number of Threads must be at least 3.\n"
                    + "Automatically will create 3 threads.");
            for (int i = 0; i < 3; i++) {
                _myThreads.add(i, new MyThread(i - 1, i, i + 1, random.nextInt(101), 3));
            }
        } else {
            for (int i = 0; i < numOfThreads; i++) {
                _myThreads.add(i, new MyThread(i - 1, i, i + 1, random.nextInt(101), numOfThreads));
            }
        }
    }

    /**
     * The following method shuts down an ExecutorService in two phases, first
     * by calling shutdown to reject incoming tasks, and then calling
     * shutdownNow, if necessary, to cancel any lingering tasks
     */
    public void shutdownAndAwaitTermination() {
        ExecutorService executor = Executors.newFixedThreadPool(_myThreads.size());

        for (MyThread myCurrent : _myThreads) {
            // Submits a Runnable task for execution and returns a Future representing that task.  
            executor.submit(myCurrent);
        }
        try {
            executor.shutdown();// Disable new tasks from being submitted
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Cancel if current thread also interrupted");
        } finally {
            //if not all tasks have completed following shut down.
            if (!executor.isTerminated()) {
                System.err.println("Cancel  not all tasks have completed following shut down");
            }
            executor.shutdownNow();//Attempts to stop all actively executing tasks
        }
    }

    /**
     * This method refresh output after every loop and prints new values of
     * threads
     */
    public void refresh(int loops) {

        if (_myThreads == null || _myThreads.size() == 0) {
            System.out.println("No Threads");
        } else {
            String res = "";
            for (int i = 0; i < _myThreads.size(); i++) {
                res += _myThreads.get(i).getRandom() + "\t";
            }
            if (loops == 0) {
                System.out.println("\nStart Threads is:\n");
                System.out.println(res);
            } else {
                System.out.println("\n Threads after loop #" + loops + ":\n");
                System.out.println(res);
            }

        }
    }
}
