package utils;

public class DriverUtils {

    public static void wait(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
