package com.photostudio.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Колекція (Репозиторій) для зберігання об'єктів Order.
 * Відповідає діаграмі класу OrderCollection.
 */
public class OrderCollection {

    // Атрибути
    private List<Order> ordersList;

    public OrderCollection() {
        this.ordersList = new ArrayList<>();
    }

    /**
     * Зберігає замовлення у колекції.
     * (Information Expert)
     * @param order "Адреса" (посилання) на об'єкт Order, який треба зберегти.
     */
    public void saveOrder(Order order) {
        if (order != null) {
            this.ordersList.add(order);
            System.out.println("OrderCollection: Order " + order.getId() + " saved successfully.");
        }
    }
}