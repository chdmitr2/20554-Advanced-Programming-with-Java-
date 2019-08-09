
package testsharedata4;

import java.util.ArrayList;
import java.util.List;

/**
 * class TwoThreadsx4 creates the MoveThread and GetThread
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
public class TwoThreadsx4 {

    //list of move threads
    private List<MoveThread> mList;
    // list of get threads
    private List<GetThread> gList;
    // number of milliseconds for thread to sleep 
    public static final int Pause = 500;
    public static final int Couples = 10;

    /**
     * constructor
     *
     * @param sharedData SharedData object - NOT NULL
     * @throws NullPointerException in case sharedData is null
     */
    public TwoThreadsx4(SharedData data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("SharedData NULL!");
        }

        //create lists
        mList = new ArrayList<>();
        gList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mList.add(new MoveThread(data));
        }
        for (int i = 0; i < 4; i++) {
            gList.add(new GetThread(data));
        }

    }

    /**
     * methods to start the threads
     */
    public void startThreads() {
        //start the threads
        for (int i = 0; i < 4; i++) {
            gList.get(i).start();
            mList.get(i).start();
        }
    }

}
