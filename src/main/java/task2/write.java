package task2;

import java.util.concurrent.CopyOnWriteArrayList;
class write implements Runnable {
    private CopyOnWriteArrayList<Integer> listOfNumber;
    public write(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumber = listOfNumbers;
    }
    public void run() {
        try{
            while(true) {
            for (int i = 1; i < 20; i++) {
                listOfNumber.add(i);
                System.out.println("number write: " + i);}
            Thread.sleep(2000);}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
