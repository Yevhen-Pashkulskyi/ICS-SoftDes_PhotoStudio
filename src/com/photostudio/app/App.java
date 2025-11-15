package com.photostudio.app;

// Імпортуємо наш контролер
import com.photostudio.controller.OrderManager;

// Імпортуємо класи моделі, які нам потрібні для App
import com.photostudio.model.Photographer;
import com.photostudio.model.Schedule;

import java.sql.Time;
import java.util.Date;

/**
 * Головний клас для запуску програми.
 * Імітує дії "Рецепціоніста" (Актора)
 * відповідно до прецеденту "ВВ1: Реєстрація замовлення на фотосесію".
 */
public class App {

    public static void main(String[] args) {

        // --- Початок сценарію ---
        System.out.println("--- Сценарій: Реєстрація замовлення на фотосесію ---");
        System.out.println("Актор: Рецепціоніст");

        // Рецепціоніст починає роботу з системою через контролер
        OrderManager manager = new OrderManager();

        // [Крок 1: Клієнт підходить... Рецепціоніст відкриває нове замовлення]
        System.out.println("\n[Крок 1: Ініціалізація замовлення]");
        manager.newOrder(); // Створюється activeOrder

        // [Крок 2: Клієнт повідомляє тип... Рецепціоніст вводить дані]
        System.out.println("\n[Крок 2: Вибір типу сесії]");
        String selectedType = "Portrait";
        System.out.println("Receptionist: Перевірка доступності типу: '" + selectedType + "'");

        boolean typeOK = manager.isSessionTypeAvailable(selectedType);

        if (!typeOK) {
            System.err.println("System: Тип сесії недоступний. Сценарій перервано.");
            return; // Завершуємо, якщо тип не знайдено
        }
        System.out.println("System: Тип '" + selectedType + "' успішно обрано.");


        // [Крок 3: Клієнт повідомляє дату/час... Рецепціоніст вводить дані]
        System.out.println("\n[Крок 3: Перевірка доступності слоту]");

        // Ми імітуємо вибір дати і часу.
        // УВАГА: Наша демонстраційна версія Order.java створює ОДИН слот
        // у конструкторі з поточним часом. Ця перевірка, найімовірніше,
        // поверне 'false', оскільки мілісекунди не збігатимуться.
        // Це очікувана поведінка для демонстраційних даних.
        Date targetDate = new Date(); // Сьогодні
        Time targetTime = new Time(14, 0, 0); // 14:00

        System.out.println("Receptionist: Перевірка слоту на " + targetDate + " " + targetTime);
        boolean slotOK = manager.isSlotAvailable(targetDate, targetTime);
        System.out.println("System: Слот доступний? " + slotOK);

        if (!slotOK) {
            System.err.println("System: Слот на цей час недоступний.");
            // У реальному сценарії рецепціоніст запропонував би інший час.
            // Для демонстрації ми зупинимо сценарій.
            System.out.println("--- Сценарій перервано на Кроці 3 ---");
            return;
        }
        System.out.println("System: Слот успішно обрано.");


        // [Крок 4: Клієнт повідомляє кількість... Розрахунок вартості]
        System.out.println("\n[Крок 4: Розрахунок вартості]");
        int quantity = 25;
        String style = "Standard retouch";
        System.out.println("Receptionist: Запит вартості для " + quantity + " фото, стиль '" + style + "'");

        double cost = manager.getCost(quantity, style);
        System.out.println("System: Розрахована вартість: " + cost);
        System.out.println("Receptionist: Повідомляє клієнту вартість: " + cost);


        // [Крок 5: Клієнт погоджується... Рецепціоніст фіксує]
        System.out.println("\n[Крок 5: Фіналізація замовлення]");
        System.out.println("Receptionist: Клієнт погодився. Фіксуємо замовлення...");

        boolean finalOK = manager.finalizeOrder();

        if (finalOK) {
            System.out.println("System: Замовлення успішно зарезервовано та збережено.");
            System.out.println("Receptionist: Видає клієнту підтвердження.");
        } else {
            System.err.println("System: Помилка фіналізації. Слот міг бути зайнятий.");
        }

        System.out.println("\n--- Сценарій завершено ---");
    }
}