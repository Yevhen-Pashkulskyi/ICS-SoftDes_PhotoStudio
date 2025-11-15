import java.util.ArrayList;
import java.util.List;

public // Клас OrderCollection як колекція (Information Expert)
class OrderCollection {
    private List<Order> ordersList = new ArrayList<>();
    private int size = 0;

    public void saveOrder(Order order) {
        ordersList.add(order);
        size++;
    }

    public int getSize() { return size; }
}
