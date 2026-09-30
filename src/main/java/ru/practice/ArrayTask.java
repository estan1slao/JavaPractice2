package ru.practice;

import java.util.Arrays;
import java.util.Random;

public class ArrayTask {

    public static void run() {
        int n = 10;
        int[] numbers = new int[n];

        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(101) - 50;
        }

        int sum = 0;
        for (int number : numbers) {
            if (number > 0) {
                sum += number;
            }
        }

        System.out.println("Задание №1");
        System.out.println("Массив: " + Arrays.toString(numbers));
        System.out.println("Сумма положительных элементов: " + sum);
    }
}