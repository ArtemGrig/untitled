package task1;
public class main {
    public static void main(String[] args) {
    runnable idthread = new runnable();

    Thread thread1 = new Thread(idthread);
    Thread thread2 = new Thread(idthread);
    Thread thread3 = new Thread(idthread);

    thread1.start();
    thread2.start();
    thread3.start();
    }
}
