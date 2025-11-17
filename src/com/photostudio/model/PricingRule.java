package com.photostudio.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляє правило ціноутворення.
 * Відповідає діаграмі класу PricingRule.
 */
public class PricingRule {

    // Атрибути
    private double multiplier;
    private String ruleDescription;

    // Приватний конструктор, щоб об'єкти створювались лише "фабричним" методом
    public PricingRule(String description, double multiplier) {
        this.ruleDescription = description;
        this.multiplier = multiplier;
    }

    // Public "гетер"
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Статичний ($) метод для отримання списку всіх застосовних правил.
     * (Information Expert)
     */
    public static List<PricingRule> getApplicablePricingRules() {
        // Заглушка: повертаємо демонстраційний список правил
        List<PricingRule> rules = new ArrayList<>();
        rules.add(new PricingRule("Standard retouch", 1.2));
        rules.add(new PricingRule("Urgent order", 1.5));
        return rules;
    }
}