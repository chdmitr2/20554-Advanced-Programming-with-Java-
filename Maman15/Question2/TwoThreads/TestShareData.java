package testsharedata;

/**
 * class TestShareData start test and check two threads and community together
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class TestShareData {

    public static void main(String args[]) {

        SharedData data = new SharedData();
        //create the two threads and run them to share the sharedData
        TwoThreads threads = new TwoThreads(data);
        //start the threads
        threads.startThreads();
    }

}
