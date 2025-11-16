package com.photostudio.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляє тип фотосесії (наприклад, портрет, весілля).
 * Відповідає діаграмі класу SessionType.
 */
public class SessionType {

    // Атрибути
    private String id;
    private String name;
    private double basePrice;

    public SessionType(String id, String name, double basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    // Public "гетер"
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Статичний ($) метод для отримання списку всіх доступних типів.
     * (Information Expert)
     */
    public static List<SessionType> getAvailableSessionTypes() {
        // Заглушка: повертаємо демонстраційний список
        List<SessionType> types = new ArrayList<>();
        types.add(new SessionType("p1", "Portrait", 100.0));
        types.add(new SessionType("w1", "Wedding", 500.0));
        return types;
    }

    public String getName() {
        return name;
    }
}