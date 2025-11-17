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

        // (1) Вхід, ініціалізація
        double baseCost = 0.0;

        // (2) Предикат #1 (Розгалуження)
        // Додано для задоволення умов завдання
        if (details.getQuantity() > 0) {

            // (3) Блок "True"
            baseCost = details.getQuantity() * 10.0; // 10 за фото

            // (4) Предикат #2 (Цикл)
            for (PricingRule rule : rules) {
                // (5) Блок в циклі
                baseCost *= rule.getMultiplier();
            }
        }
        // (Неявний "else" веде до вузла 6)

        // (6) Вузол виходу
        return baseCost;
        // (7) Кінець
    }
}