package com.photostudio.app;

import com.photostudio.model.CostCalculator;
import com.photostudio.model.PhotoDetails;
import com.photostudio.model.PricingRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Тестовий Драйвер (Test Driver)
 * для виконання тест-кейсу 'TC_WB_CostCalculator_01'.
 *
 * Цей драйвер НЕ ПОТРЕБУЄ "заглушок" (mocks), оскільки
 * CostCalculator є stateless і отримує всі залежності
 * через параметри.
 */
public class TestDriver {

    public static void main(String[] args) {
        System.out.println("--- Запуск Тест-кейсу 'TC_WB_CostCalculator_01' ---");
        runTestCase();
    }

    /**
     * Метод, що виконує тестові варіанти з тест-кейсу.
     */
    public static void runTestCase() {

        PhotoDetails details;
        List<PricingRule> rules;
        double result;

        // --- Тестовий Варіант 1 (Шлях 1) ---
        System.out.println("\n--- Тест 1: Кількість <= 0 ---");
        details = new PhotoDetails(0, "Test"); // 1. Вхідні дані
        rules = new ArrayList<>();
        result = CostCalculator.calculateCost(details, rules); // 6. Дія
        printResult(result, 0.0); // 8. Перевірка

        // --- Тестовий Варіант 2 (Шлях 2) ---
        System.out.println("\n--- Тест 2: Кількість > 0, список правил порожній ---");
        details = new PhotoDetails(10, "Test"); // 1. Вхідні дані
        rules = new ArrayList<>();
        result = CostCalculator.calculateCost(details, rules); // 6. Дія
        printResult(result, 100.0); // 8. Перевірка (10 * 10.0)

        // --- Тестовий Варіант 3 (Шлях 3) ---
        System.out.println("\n--- Тест 3: Кількість > 0, список правил НЕ порожній ---");
        details = new PhotoDetails(10, "Retouch"); // 1. Вхідні дані
        rules = new ArrayList<>();
        rules.add(new PricingRule("Retouch", 1.5));
        rules.add(new PricingRule("Urgent", 2.0));

        result = CostCalculator.calculateCost(details, rules); // 6. Дія
        // (10 * 10.0) * 1.5 * 2.0 = 100.0 * 1.5 * 2.0 = 300.0
        printResult(result, 300.0); // 8. Перевірка
    }

    /**
     * Допоміжний метод для виводу результатів тесту.
     */
    private static void printResult(double actual, double expected) {
        System.out.println("Очікуваний результат: " + expected + ". Фактичний: " + actual);
        if (actual == expected) {
            System.out.println("Статус: PASSED");
        } else {
            System.out.println("Статус: FAILED");
        }
    }
}