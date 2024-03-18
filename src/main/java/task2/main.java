package task2;

import java.util.concurrent.CopyOnWriteArrayList;
public class main {
    public static void main(String[] args) {
    CopyOnWriteArrayList<Integer> listOfNumbers = new CopyOnWriteArrayList<>();

    read reader = new read(listOfNumbers);
    write writer = new write(listOfNumbers);

    Thread readerThread = new Thread(reader);
    Thread writerThread = new Thread(writer);

    writerThread.start();
    readerThread.start();
    }
}
