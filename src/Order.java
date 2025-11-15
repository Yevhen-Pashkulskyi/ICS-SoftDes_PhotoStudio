import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public // Клас Order як центральна сутність (Information Expert, GRASP)
class Order {
    private String id = "ORD" + System.currentTimeMillis();

    private String sessionTypeId;
    private LocalDateTime dateTime;
    private double estimatedCost;
    private PhotoDetails photoDetails;
    private List<Schedule> schedules = new ArrayList<>();


    public void createOrder() {
        // Ініціалізація замовлення
    }

    public String getId() {
        return id;
    }

    public boolean isSessionTypeAvailable(String type) {
        SessionType sessionType = new SessionType();
        List<SessionType> availableTypes = sessionType.getAvailableSessionTypes();
        for (SessionType st : availableTypes) {
            if (st.getName().equals(type)) {
                this.sessionTypeId = st.getId();
                return true;
            }
        }
        return false;
    }

    public void setSessionType(String type) {
        if (sessionTypeId != null) this.sessionTypeId = type;
    }

    public boolean isSlotAvailable(LocalDateTime dateTime) {
        Schedule schedule = new Schedule();
        boolean isAvailable = schedule.checkAvailability(dateTime);
        if (isAvailable) {
            schedules.add(schedule);
            setDateTime(dateTime);
        }
        return isAvailable;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getCost(int quantity, String style) {
        photoDetails = new PhotoDetails(quantity, style);
        PricingRule pricingRule = new PricingRule();
        List<PricingRule> rules = pricingRule.getApplicablePricingRules();
        CostCalculator calculator = new CostCalculator();
        double cost = calculator.calculateCost(photoDetails, rules);
        setEstimatedCost(cost);
        return cost;
    }

    public void setEstimatedCost(double cost) {
        this.estimatedCost = cost;
    }

    public boolean finalizeOrder() {
        for (Schedule schedule : schedules) {
            schedule.reserveSlot();
        }
        return true; // Припустимо, що фіналізація вдалася
    }

    public void saveOrder() {
        // Логіка збереження (у реальному випадку — до бази даних)
    }
}