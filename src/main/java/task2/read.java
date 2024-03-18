package task2;

import java.util.concurrent.CopyOnWriteArrayList;

public class read implements Runnable{
    private CopyOnWriteArrayList<Integer> listOfNumber;

    public read(CopyOnWriteArrayList<Integer> listOfNumbers){
        this.listOfNumber = listOfNumbers;
    }
    public void run() {
        try{
            while(true) {
                for (int i = 1; i < 20; i++) {
                    System.out.println("number read: " + i);}
                Thread.sleep(1000);}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
