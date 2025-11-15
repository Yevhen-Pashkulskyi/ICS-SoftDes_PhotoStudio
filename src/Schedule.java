import java.time.LocalDateTime;

public // Клас Schedule як сутність (Information Expert)
class Schedule {
    private String id = "SCH" + System.currentTimeMillis();
    private String photographerId;
    private LocalDateTime dateTime;
    private boolean isAvailable = true;

    public boolean checkAvailability(LocalDateTime dateTime) {
        // Проста логіка: слот доступний, якщо не зайнято
        return isAvailable;
    }

    public void reserveSlot() {
        this.isAvailable = false;
    }
}
