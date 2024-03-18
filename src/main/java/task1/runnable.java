package task1;

public class runnable implements Runnable {
    public void run() {
    long IDThread = Thread.currentThread().getId();
    System.out.println(IDThread);
    }
}