package org.example;
import java.util.Random;

public class Main {
    private static final int NUM_CUSTOMERS = 100;
    private static final int NUM_WINDOWS = 3;

    private static final int YOUNG_CATEGORY = 1;
    private static final int ELDERLY_CATEGORY = 2;
    private static final int BUSINESS_CATEGORY = 3;

    private static boolean[] windowBusy = new boolean[NUM_WINDOWS];
    private static int[] customerCount = new int[NUM_WINDOWS];
    private static int[] angryCustomers = new int[NUM_WINDOWS];

    public static void main(String[] args) {
        Thread[] customers = new Thread[NUM_CUSTOMERS];

        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            customers[i] = new Thread(() -> {
                int category = getRandomCategory();
                int window = getRandomWindow(category);

                if (window != -1) {
                    windowBusy[window] = true;
                    customerCount[window]++;
                    System.out.println("Клиент в категории" + category + " подошел к окну " + window);
                    try {
                        Thread.sleep(2000); // Simulating service time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    windowBusy[window] = false;
                } else {
                    System.out.println("Разгневанный клиент в категории " + category + " не удается найти подходящее окно");
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

        System.out.println("Процент разгневанных клиентов:");
        System.out.println("Молодые: " + (double) angryCustomers[YOUNG_CATEGORY - 1] / NUM_CUSTOMERS * 100 + "%");
        System.out.println("Пожилые: " + (double) angryCustomers[ELDERLY_CATEGORY - 1] / NUM_CUSTOMERS * 100 + "%");
        System.out.println("Бизнес тюбики: " + (double) angryCustomers[BUSINESS_CATEGORY - 1] / NUM_CUSTOMERS * 100 + "%");
    }

    private static int getRandomCategory() {
        Random random = new Random();
        return random.nextInt(NUM_WINDOWS) + 1;
    }

    private static int getRandomWindow(int category) {
        for (int i = 0; i < NUM_WINDOWS; i++) {
            if (!windowBusy[i] && (category == YOUNG_CATEGORY
                    || (category == ELDERLY_CATEGORY && i == 1)
                    || (category == BUSINESS_CATEGORY && i == 2))
            ) {
                return i;
            }
        }
        return -1;
    }
}