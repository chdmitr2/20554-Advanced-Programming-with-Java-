
package testsharedata4;

/**
 * class TestShareData4 start test and check two threads every four times and
 * community together
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class TestShareData4 {

    public static void main(String args[]) {

        SharedData data = new SharedData();
        //create the two threads and run every four times  to share the sharedData
        TwoThreadsx4 threads = new TwoThreadsx4(data);
        //start the threads
        threads.startThreads();
    }

}
