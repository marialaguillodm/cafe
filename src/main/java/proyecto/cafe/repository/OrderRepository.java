package proyecto.cafe.repository;

import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Order;

import java.util.*;

@Repository
public class OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();

    public Collection<Order> findAll() {
        return orders.values();
    }

    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> findByCustomerId(Integer customerId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomerId().equals(customerId)) {
                result.add(order);
            }
        }
        return result;
    }

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }
}
