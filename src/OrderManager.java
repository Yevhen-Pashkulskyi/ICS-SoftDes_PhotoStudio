import java.time.LocalDateTime;

public // Клас OrderManager як контролер (Controller, GRASP)
class OrderManager {
    private OrderCollection orderCollection = new OrderCollection();

    public Order newOrder() {
        Order order = new Order();
        order.createOrder();
        orderCollection.saveOrder(order);
        return order;
    }

    public boolean isSessionTypeAvailable(String type) {
        Order order = new Order();
        return order.isSessionTypeAvailable(type);
    }

    public boolean isSlotAvailable(LocalDateTime dateTime) {
        Order order = new Order();
        return order.isSlotAvailable(dateTime);
    }

    public double getCost(int quantity, String style) {
        Order order = new Order();
        return order.getCost(quantity, style);
    }

    public boolean finalizeOrder(Order order) {
        return order.finalizeOrder();
    }
}