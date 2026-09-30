package ru.practice;

import java.util.ArrayList;
import java.util.Random;

public class InsertionSortTask {

    public static void run() {
        int n = 10;

        ArrayList<Double> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            double value = -100.0 + random.nextDouble() * 200.0;
            numbers.add(Math.round(value * 100.0) / 100.0);
        }

        ArrayList<Double> original = new ArrayList<>(numbers);

        insertionSort(numbers);

        System.out.println("Задание №2");
        System.out.println("Исходный список: " + original);
        System.out.println("Отсортированный список: " + numbers);
    }

    private static void insertionSort(ArrayList<Double> list) {

        for (int i = 1; i < list.size(); i++) {

            double key = list.get(i);

            int j = i - 1;

            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }
}