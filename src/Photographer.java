import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public // Клас Photographer як сутність (Information Expert)
class Photographer {
    private String id = "PHT" + System.currentTimeMillis();
    private String name;
    private List<LocalDateTime> availability = new ArrayList<>();

    public List<LocalDateTime> getPhotographerAvailability() {
        availability.add(LocalDateTime.now().plusDays(1));
        availability.add(LocalDateTime.now().plusDays(2));
        return availability;
    }
}
