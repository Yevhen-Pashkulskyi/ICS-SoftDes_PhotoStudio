package com.photostudio.model;

import java.sql.Time;
import java.util.Date;

/**
 * Представляє слот у розкладі, пов'язаний з фотографом.
 * Відповідає діаграмі класу Schedule.
 */
public class Schedule {

    // Атрибути
    private Date date;
    private Time timeSlot;
    private boolean isAvailable;
    private Photographer photographer; // "Адреса" фотографа

    public Schedule(Photographer photographer, Date date, Time timeSlot) {
        this.photographer = photographer;
        this.date = date;
        this.timeSlot = timeSlot;
        this.isAvailable = true; // За замовчуванням слот доступний
    }

    /**
     * Перевіряє доступність цього слоту.
     * (Information Expert)
     */
    public boolean checkAvailability(Date date, Time time) {
        System.out.println("Schedule: Checking availability for " + date + " " + time);

        // Заглушка: Перевіряємо, чи збігається дата/час І чи доступний слот
        boolean dateMatch = this.date.equals(date);
        boolean timeMatch = this.timeSlot.equals(time);

        // Також перевіряємо, чи фотограф взагалі працює в цей день
        boolean photographerAvailable = photographer.getPhotographerAvailability().contains(date);

        return this.isAvailable && dateMatch && timeMatch && photographerAvailable;
    }

    /**
     * Резервує цей слот (змінює внутрішній стан).
     * (Information Expert)
     */
    public boolean reserveSlot() {
        if (this.isAvailable) {
            this.isAvailable = false;
            System.out.println("Schedule: Slot at " + this.date + " reserved successfully.");
            return true;
        }
        System.out.println("Schedule: FAILED to reserve slot. Already taken.");
        return false;
    }
}