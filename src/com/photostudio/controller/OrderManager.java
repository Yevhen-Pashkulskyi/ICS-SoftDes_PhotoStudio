package com.photostudio.controller;

import com.photostudio.model.Order; // Імпортуємо наш клас Order

import java.sql.Time;
import java.util.Date;

/**
 * Контролер (GRASP Controller), що слугує точкою входу для UI.
 * Координує процес створення замовлення, делегуючи всю логіку
 * доменному об'єкту Order.
 * Відповідає діаграмі класу OrderManager.
 */
public class OrderManager {

    // Атрибути
    /**
     * "Адреса" (посилання) на поточне замовлення, що обробляється.
     */
    private Order activeOrder;

    /**
     * Ініціює створення нового замовлення (Діаграма 1).
     * Створює екземпляр Order і зберігає його в activeOrder.
     */
    public void newOrder() {
        // Патерн Creator: OrderManager створює Order
        this.activeOrder = new Order();
        System.out.println("OrderManager: New order process started. Active order ID: " + activeOrder.getId());
    }

    /**
     * Делегує перевірку доступності типу сесії (Діаграма 2).
     */
    public boolean isSessionTypeAvailable(String type) {
        if (activeOrder == null) {
            System.err.println("OrderManager Error: No active order. Call newOrder() first.");
            return false;
        }
        // Делегування роботи до Information Expert (Order)
        return this.activeOrder.isSessionTypeAvailable(type);
    }

    /**
     * Делегує перевірку доступності слоту (Діаграма 3).
     */
    public boolean isSlotAvailable(Date date, Time time) {
        if (activeOrder == null) {
            System.err.println("OrderManager Error: No active order.");
            return false;
        }
        // Делегування
        return this.activeOrder.isSlotAvailable(date, time);
    }

    /**
     * Делегує розрахунок вартості (Діаграма 4).
     */
    public double getCost(int quantity, String style) {
        if (activeOrder == null) {
            System.err.println("OrderManager Error: No active order.");
            return -1.0;
        }
        // Делегування
        return this.activeOrder.getCost(quantity, style);
    }

    /**
     * Делегує фіналізацію замовлення (Діаграма 5).
     * Після фіналізації, посилання на замовлення скидається.
     */
    public boolean finalizeOrder() {
        if (activeOrder == null) {
            System.err.println("OrderManager Error: No active order to finalize.");
            return false;
        }

        // Делегування
        boolean success = this.activeOrder.finalizeOrder();

        if (success) {
            System.out.println("OrderManager: Process complete for order " + activeOrder.getId());
            this.activeOrder = null; // Скидаємо посилання, замовлення завершено
        } else {
            System.err.println("OrderManager: Failed to finalize order " + activeOrder.getId());
        }
        return success;
    }
}