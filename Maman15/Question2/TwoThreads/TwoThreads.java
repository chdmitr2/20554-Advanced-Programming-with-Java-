package testsharedata;

import java.util.ArrayList;
import java.util.List;

/**
 * class TwoThreads creates the MoveThread and GetThread
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class TwoThreads {

    //list of store threads
    private List<MoveThread> mList;
    // list of calc threads
    private List<GetThread> gList;
    // number of milliseconds for thread to sleep a
    public static final int Pause = 1000;

    public static final int Couples = 10;

    /**
     * constructor
     *
     * @param sharedData SharedData object - NOT NULL
     * @throws NullPointerException in case sharedData is null
     */
    public TwoThreads(SharedData data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("SharedData NULL!");
        }

        //start the lists
        mList = new ArrayList<>();
        gList = new ArrayList<>();
        mList.add(new MoveThread(data));
        gList.add(new GetThread(data));

    }

    /**
     * methods to start the threads
     */
    public void startThreads() {
        //start the threads

        gList.get(0).start();
        mList.get(0).start();
    }

}
