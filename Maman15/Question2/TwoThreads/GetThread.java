package testsharedata;

/**
 * class GetThread is a thread which gets the number from a SharedData
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
class GetThread extends Thread {

    private SharedData data1;

    /**
     * constructor
     *
     * @param sharedData to be shared between threads
     */
    GetThread(SharedData data) {

        this.data1 = data;
    }

    @Override
    public void run() {
        // num of times
        for (int i = 0; i < TwoThreads.Couples; i++) {
            //get value
            data1.get();
            //print value
            System.out.println("Threads after moving:\n" + data1.toString() + "\n");

            //put the thread to sleep for 
            try {
                Thread.sleep(TwoThreads.Pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
