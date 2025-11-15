import java.util.ArrayList;
import java.util.List;

public // Клас SessionType як сутність (Information Expert)
class SessionType {
    private String id = "ST" + System.currentTimeMillis();
    private String name;
    private double basePrice;

    public List<SessionType> getAvailableSessionTypes() {
        List<SessionType> types = new ArrayList<>();
        types.add(new SessionType("Portrait", 100.0));
        types.add(new SessionType("Family", 150.0));
        types.add(new SessionType("Wedding", 300.0));
        return types;
    }

    public SessionType() {
    }

    public SessionType(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
}
