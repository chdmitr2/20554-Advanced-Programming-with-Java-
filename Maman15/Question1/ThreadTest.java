package threadtest;

/**
 * class ThreadTest class start test and check threads community together
 *
 * @author Dmitriy Chudnovsky 324793900
 * @since 24/05/19
 */
import java.util.Scanner;

public class ThreadTest {

    public static void main(String[] args) {
        int numOfThreads = 0;
        int numOfLoops = 0;
        boolean check = false;

        Scanner scan = new Scanner(System.in);
        //Enter number of thread
        System.out.println("Please enter the number of threads :\n");
        numOfThreads = scan.nextInt();
        System.out.println("\n");
        while (!check) {
            try {
                if (numOfThreads == (int) numOfThreads) {
                    check = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a number !!!\n");
            }
        }
        check = false;
        //Enter number of loops
        System.out.println("Please enter the number of loops :\n");
        numOfLoops = scan.nextInt();
        System.out.println("\n");
        while (!check) {
            try {
                if (numOfLoops == (int) numOfLoops) {
                    check = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a number !!!\n");
            }
        }
        //Built threads
        Threads threads = new Threads(numOfThreads);
        //start process of threads
        for (int i = 0; i <= numOfLoops; i++) //One time more for representing initial state
        {
            threads.refresh(i);
            threads.shutdownAndAwaitTermination();
        }
    }
}
