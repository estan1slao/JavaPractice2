package ru.practice;

public class Main {
    public static void main(String[] args) {

        // Задание 1
        ArrayTask.run();
        System.out.println();

        // Задание 2
        InsertionSortTask.run();
        System.out.println();

        // Задание 3
        StreamTask.run();
        System.out.println();

        // Задание 4
        HttpJsonTask.run();
        System.out.println();

        // Задание 5
        System.out.println("Задание №5");
        PortScannerTask scanner =
                new PortScannerTask(
                        "127.0.0.1",
                        1,
                        1024,
                        100
                );

        scanner.start();

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        scanner.stop();

        try {

            Thread.sleep(200);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}