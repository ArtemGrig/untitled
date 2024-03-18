package task3;

import java.util.Random;

public class Main {
    private static final int colvo_customers = 20;
    private static final int windows = 3;

    private static final int younge = 1;
    private static final int elder = 2;
    private static final int busnesman = 3;

    private static boolean[] windowBusy = new boolean[windows];
    private static int[] customerCount = new int[windows];
    private static int[] angryCustomers = new int[windows];

    public static void main(String[] args) {
        Thread[] customers = new Thread[colvo_customers];

        for (int i = 0; i < colvo_customers; i++) {
            customers[i] = new Thread(() -> {
                int category = getRandomCategory();
                int window = getRandomWindow(category);

                if (window != -1) {
                    windowBusy[window] = true;
                    customerCount[window]++;
                    System.out.println("Гражданин в категории " + category + " у окна номер " + window);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    windowBusy[window] = false;
                } else {
                    System.out.println("Разгневанный гражданин в категории " + category + " не смог найти подходящее окно");
                    angryCustomers[category - 1]++;
                }
            });
            customers[i].start();
        }

        for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Процент разгневанных граждан:");
        System.out.println("Молодые: " + (double) angryCustomers[younge - 1] / colvo_customers * 100 + "%");
        System.out.println("Пожилые: " + (double) angryCustomers[elder - 1] / colvo_customers * 100 + "%");
        System.out.println("Бизнесмены: " + (double) angryCustomers[busnesman - 1] / colvo_customers * 100 + "%");
    }

    private static int getRandomCategory() {
        Random random = new Random();
        return random.nextInt(windows) + 1;
    }

    private static int getRandomWindow(int category) {
        for (int i = 0; i < windows; i++) {
            if (!windowBusy[i] && (category == younge
                    || (category == elder && i == 1)
                    || (category == busnesman && i == 2))
            ) {
                return i;
            }
        }
        return -1;
    }
}