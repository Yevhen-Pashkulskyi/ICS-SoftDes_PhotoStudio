package com.photostudio.model;

import java.util.List;

/**
 * Сервісний клас (Pure Fabrication) для розрахунку вартості.
 * Не має стану (атрибутів).
 * Відповідає діаграмі класу CostCalculator.
 */
public class CostCalculator {

    /**
     * Статичний ($) метод, що виконує логіку розрахунку.
     * Отримує всі "адреси" (посилання) через параметри (Low Coupling).
     *
     * @param details Об'єкт з деталями (кількість, стиль).
     * @param rules Список правил для застосування.
     * @return Розрахована вартість.
     */
    public static double calculateCost(PhotoDetails details, List<PricingRule> rules) {
        // Заглушка: Демонстраційна логіка розрахунку
        System.out.println("CostCalculator: Calculating cost...");

        // Базова ціна може залежати від кількості фото
        double baseCost = details.getQuantity() * 10.0; // Припустимо, 10 за фото

        // Застосовуємо правила
        for (PricingRule rule : rules) {
            baseCost *= rule.getMultiplier();
        }

        System.out.println("CostCalculator: Calculated cost is " + baseCost);
        return baseCost;
    }
}