package ru.practice;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamTask {

    public static void run() {

        ArrayList<Employee> employees = new ArrayList<>(
                Arrays.asList(
                        new Employee(
                                "Иванов Иван Иванович",
                                25,
                                "Разработка",
                                85000.0
                        ),

                        new Employee(
                                "Петров Петр Петрович",
                                32,
                                "Разработка",
                                125000.0
                        ),

                        new Employee(
                                "Сидорова Анна Сергеевна",
                                29,
                                "Тестирование",
                                95000.0
                        ),

                        new Employee(
                                "Кузнецов Алексей Олегович",
                                41,
                                "Аналитика",
                                110000.0
                        ),

                        new Employee(
                                "Смирнова Мария Андреевна",
                                27,
                                "Дизайн",
                                78000.0
                        )
                )
        );

        boolean hasHighSalary = employees.stream()
                .anyMatch(employee ->
                        employee.getSalary() > 100000.00
                );

        System.out.println("Задание №3");

        System.out.println("Список сотрудников:");

        employees.forEach(System.out::println);

        System.out.println(
                "Есть сотрудник с зарплатой более 100000.00: "
                        + hasHighSalary
        );
    }
}