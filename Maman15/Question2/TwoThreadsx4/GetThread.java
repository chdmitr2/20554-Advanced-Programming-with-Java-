
package testsharedata4;

/**
 * class GetThread is a thread which gets the number from a SharedData
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
class GetThread extends Thread {

    //SharedData object
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
        for (int i = 0; i < TwoThreadsx4.Couples; i++) {
            //get value
            data1.get();
            //print  value
            System.out.println("Threads after moving:\n" + data1.toString() + "\n");

            //put the thread to sleep 
            try {
                Thread.sleep(TwoThreadsx4.Pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
