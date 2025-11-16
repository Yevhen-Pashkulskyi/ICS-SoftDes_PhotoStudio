package com.photostudio.app;

import com.photostudio.model.Order;
import com.photostudio.model.SessionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Тестовий Драйвер (Test Driver)
 * для виконання тест-кейсу 'TC_WB_Order_01'.
 */
public class TestDriver {

    // Створюємо "заглушений" список типів сесій (Mock Object)
    static List<SessionType> mockSessionTypes = new ArrayList<>();

    /**
     * Це "заглушка" (Stub/Mock) класу Order.
     * Ми перевизначаємо метод, що тестується, щоб "інжектувати"
     * наш контрольований список 'mockSessionTypes'
     * замість виклику реального SessionType.getAvailableSessionTypes().
     */
    static class TestableOrder extends Order {

        @Override
        public boolean isSessionTypeAvailable(String typeName) {
            // (1) Вхід
            // (2) Отримання списку.
            // ЗАМІСТЬ SessionType.getAvailableSessionTypes()
            // МИ ВИКОРИСТОВУЄМО НАШ MOCK-СПИСОК.
            List<SessionType> availableTypes = TestDriver.mockSessionTypes;

            // (3) Предикат #1 (for)
            for (SessionType st : availableTypes) {
                // (4) Предикат #2 (if)
                if (st.getName().equalsIgnoreCase(typeName)) {
                    // (5) "True" блок
                    // setSessionType(typeName); // Не можемо викликати private, але імітуємо
                    System.out.println("Order: Session type set to '" + typeName + "'");
                    // (6) Вихід (true)
                    return true;
                }
            }
            // (7) Блок після циклу
            System.out.println("Order: Session type '" + typeName + "' is NOT available.");
            // (8) Вихід (false)
            return false;
            // (9) Кінець
        }
    }


    public static void main(String[] args) {
        System.out.println("--- Запуск Тест-кейсу 'TC_WB_Order_01' ---");
        runTestCase();
    }

    /**
     * Метод, що виконує тестові варіанти з тест-кейсу.
     */
    public static void runTestCase() {

        TestableOrder order;
        boolean result;

        // --- Тестовий Варіант 1 (Шлях 1) ---
        System.out.println("\n--- Тест 1: Список порожній ---");
        mockSessionTypes.clear(); // Вхідні дані
        order = new TestableOrder();
        result = order.isSessionTypeAvailable("Portrait"); // Дія
        printResult(result, false); // Перевірка

        // --- Тестовий Варіант 2 (Шлях 2) ---
        System.out.println("\n--- Тест 2: Збігів не знайдено ---");
        mockSessionTypes.clear();
        mockSessionTypes.add(new SessionType("p1", "Portrait", 100.0)); // Вхідні дані
        mockSessionTypes.add(new SessionType("f1", "Family", 150.0));

        order = new TestableOrder();
        result = order.isSessionTypeAvailable("Wedding"); // Дія
        printResult(result, false); // Перевірка

        // --- Тестовий Варіант 3 (Шлях 3) ---
        System.out.println("\n--- Тест 3: Збіг знайдено ---");
        mockSessionTypes.clear();
        mockSessionTypes.add(new SessionType("p1", "Portrait", 100.0)); // Вхідні дані
        mockSessionTypes.add(new SessionType("w1", "Wedding", 500.0));

        order = new TestableOrder();
        result = order.isSessionTypeAvailable("Wedding"); // Дія
        printResult(result, true); // Перевірка
    }

    /**
     * Допоміжний метод для виводу результатів тесту.
     */
    private static void printResult(boolean actual, boolean expected) {
        System.out.println("Очікуваний результат: " + expected + ". Фактичний: " + actual);
        if (actual == expected) {
            System.out.println("Статус: PASSED");
        } else {
            System.out.println("Статус: FAILED");
        }
    }
}