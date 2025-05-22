package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.entity.Order;
import proyecto.cafe.entity.OrderItem;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.repository.CafeRepository;
import proyecto.cafe.repository.CustomerRepository;
import proyecto.cafe.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CafeRepository cafeRepository;

    public ResponseEntity<?> createOrder(Order order) {
        if (order.getCustomerId() == null || customerRepository.findById(order.getCustomerId()) == null) {
            return ResponseEntity.badRequest().body("Cliente no válido o inexistente.");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body("El pedido no puede estar vacío.");
        }

        List<OrderItem> validatedItems = new ArrayList<>();
        double total = 0;

        for (OrderItem item : order.getItems()) {
            Cafe cafe = cafeRepository.findById(item.getCafeId()).orElse(null);;
            if (cafe == null) {
                return ResponseEntity.badRequest().body("Café con ID " + item.getCafeId() + " no existe.");
            }
            if (item.getCantidad() <= 0) {
                return ResponseEntity.badRequest().body("Cantidad debe ser mayor a 0 para café ID: " + item.getCafeId());
            }
            item.setPrecio(cafe.getPrecio());
            validatedItems.add(item);
            total += item.getPrecio() * item.getCantidad();
        }

        order.setItems(validatedItems);

        return ResponseEntity.status(201).body(orderRepository.save(order));
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public ResponseEntity<?> getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElse(null);;
        if (order == null) {
            return ResponseEntity.status(404).body("Pedido no encontrado.");
        }
        return ResponseEntity.ok(order);
    }

    public List<Order> getOrdersByCustomerId(Integer customerId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.getCustomerId().equals(customerId)) {
                result.add(order);
            }
        }
        return result;
    }
}
