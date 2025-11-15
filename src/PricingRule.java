import java.util.ArrayList;
import java.util.List;

public // Клас PricingRule як сутність (Information Expert)
class PricingRule {
    private String id = "PR" + System.currentTimeMillis();
    private String ruleDescription;
    private double multiplier = 1.0;

    public List<PricingRule> getApplicablePricingRules() {
        List<PricingRule> rules = new ArrayList<>();
        rules.add(new PricingRule("Base Rate", 1.0));
        rules.add(new PricingRule("Extra Style", 1.2));
        return rules;
    }

    public PricingRule() {
    }

    public PricingRule(String ruleDescription, double multiplier) {
        this.ruleDescription = ruleDescription;
        this.multiplier = multiplier;
    }

    public double getMultiplier() { return multiplier; }
}