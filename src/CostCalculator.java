import java.util.List;

public // Клас CostCalculator як штучний клас (Pure Fabrication, Strategy)
class CostCalculator {
    public double calculateCost(PhotoDetails pd, List<PricingRule> rules) {
        double baseCost = 100.0; // Приклад базової вартості
        double total = baseCost * pd.getQuantity();
        for (PricingRule rule : rules) {
            total *= rule.getMultiplier();
        }
        return total;
    }
}