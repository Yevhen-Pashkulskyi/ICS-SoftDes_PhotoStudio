import java.time.LocalDateTime;

// Приклад використання
public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();

        // Пункт 1: Створення замовлення
        Order order = orderManager.newOrder();
        System.out.println("Замовлення створено з ID: " + order.getId());

        // Пункт 2: Перевірка типу сесії
        boolean isTypeAvailable = orderManager.isSessionTypeAvailable("Portrait");
        System.out.println("Тип сесії доступний: " + isTypeAvailable);

        // Пункт 3: Перевірка слоту
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        boolean isSlotAvailable = orderManager.isSlotAvailable(dateTime);
        System.out.println("Слот доступний: " + isSlotAvailable);

        // Пункт 4: Розрахунок вартості
        double cost = orderManager.getCost(2, "Classic");
        System.out.println("Орієнтовна вартість: " + cost);

        // Пункт 5: Фіналізація
        boolean isFinalized = orderManager.finalizeOrder(order);
        System.out.println("Замовлення фіналізовано: " + isFinalized);
    }
}