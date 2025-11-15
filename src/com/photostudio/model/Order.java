package com.photostudio.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Центральний клас-суть, "Інформаційний Експерт" (Information Expert).
 * Керує всім життєвим циклом замовлення та координує інші об'єкти.
 * Відповідає діаграмі класу Order.
 */
public class Order {

    // Атрибути стану
    private String id;
    private Date date;
    private Time time;
    private double estimatedCost;

    // Атрибути-посилання ("Адреси") на інші об'єкти
    private SessionType sessionType;
    private Schedule scheduleSlot;
    private PhotoDetails details;

    // Атрибути-посилання ("Адреси") на сервіси
    // У реальному проекті вони б впроваджувались (DI), а не створювались тут.
    private CostCalculator costCalculator; // Цей клас не має стану, тому посилання не обов'язкове
    private OrderCollection orderCollection;

    /**
     * Public конструктор.
     * Ініціалізує ID та "адреси" на сервіси.
     */
    public Order() {
        this.id = UUID.randomUUID().toString().substring(0, 8); // Генеруємо ID
        System.out.println("Order: Created new order with id: " + this.id);

        // Ініціалізуємо посилання на сервіси (заглушка для DI)
        this.orderCollection = new OrderCollection();

        // Ініціалізуємо посилання на фотографа і слот (заглушка)
        Photographer defaultPhotographer = new Photographer("pht-01", "John Doe");
        this.scheduleSlot = new Schedule(defaultPhotographer, new Date(), new Time(System.currentTimeMillis()));
    }

    // --- Public Методи (API класу) ---

    /**
     * Перевіряє доступність типу сесії та зберігає його.
     * (Indirection)
     */
    public boolean isSessionTypeAvailable(String typeName) {
        List<SessionType> availableTypes = SessionType.getAvailableSessionTypes();
        for (SessionType st : availableTypes) {
            if (st.getName().equalsIgnoreCase(typeName)) {
                setSessionType(typeName); // Виклик private методу
                return true;
            }
        }
        System.out.println("Order: Session type '" + typeName + "' is NOT available.");
        return false;
    }

    /**
     * Перевіряє доступність слоту та зберігає дату/час.
     * (Indirection)
     */
    public boolean isSlotAvailable(Date date, Time time) {
        // Ми використовуємо "адресу" scheduleSlot для делегування
        boolean isAvailable = this.scheduleSlot.checkAvailability(date, time);

        if (isAvailable) {
            setDateTime(date, time); // Виклик private методу
        }
        return isAvailable;
    }

    /**
     * Координує розрахунок вартості.
     * (Creator, Information Expert)
     */
    public double getCost(int quantity, String style) {
        // 1. Створюємо PhotoDetails (Creator)
        this.details = createPhotoDetails(quantity, style);

        // 2. Отримуємо правила
        List<PricingRule> rules = PricingRule.getApplicablePricingRules();

        // 3. Делегуємо розрахунок сервісу (Pure Fabrication)
        // Передаємо "адреси" об'єктів у параметрах (Low Coupling)
        double cost = CostCalculator.calculateCost(this.details, rules);

        // 4. Зберігаємо результат (High Cohesion)
        setEstimatedCost(cost);
        return cost;
    }

    /**
     * Координує фіналізацію замовлення.
     * (Indirection)
     */
    public boolean finalizeOrder() {
        // 1. Делегуємо резервування слоту (використовуємо збережену "адресу")
        boolean slotReserved = this.scheduleSlot.reserveSlot();

        if (slotReserved) {
            // 2. Делегуємо збереження (використовуємо "адресу" колекції)
            // Передаємо "адресу" на самого себе (this)
            this.orderCollection.saveOrder(this);
            System.out.println("Order: Finalized order " + this.id);
            return true;
        }

        System.out.println("Order: FAILED to finalize order " + this.id);
        return false;
    }

    // --- Private Методи (Внутрішня логіка) ---

    /**
     * Встановлює тип сесії.
     * (High Cohesion)
     */
    private void setSessionType(String type) {
        // У реальній логіці тут би зберігався об'єкт SessionType
        // this.sessionType = ...;
        System.out.println("Order: Session type set to '" + type + "'");
    }

    /**
     * Встановлює дату і час.
     * (High Cohesion)
     */
    private void setDateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
        System.out.println("Order: Date and time set to " + date + " " + time);
    }

    /**
     * Створює об'єкт PhotoDetails.
     * (Creator)
     */
    private PhotoDetails createPhotoDetails(int quantity, String style) {
        System.out.println("Order: Creating photo details...");
        return new PhotoDetails(quantity, style);
    }

    /**
     * Встановлює розраховану вартість.
     * (High Cohesion)
     */
    private void setEstimatedCost(double cost) {
        this.estimatedCost = cost;
        System.out.println("Order: Estimated cost set to " + cost);
    }

    // --- Гетери для доступу ---

    public String getId() {
        return id;
    }
}