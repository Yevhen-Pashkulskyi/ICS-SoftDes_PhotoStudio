package com.photostudio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Представляє фотографа та його доступність.
 * Відповідає діаграмі класу Photographer.
 */
public class Photographer {

    // Атрибути
    private String id;
    private String name;
    private List<Date> availability;

    public Photographer(String id, String name) {
        this.id = id;
        this.name = name;
        this.availability = new ArrayList<>();
        // Заглушка: додамо демонстраційні дати доступності
        this.availability.add(new Date()); // Сьогодні
    }

    /**
     * Public метод для отримання графіка фотографа.
     * (Information Expert)
     */
    public List<Date> getPhotographerAvailability() {
        System.out.println("Photographer: Returning availability for " + this.name);
        return this.availability;
    }

    public String getName() {
        return name;
    }
}