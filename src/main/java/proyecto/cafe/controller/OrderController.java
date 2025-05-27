package proyecto.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.entity.Order;
import proyecto.cafe.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Order> orders = orderService.getAllOrders(PageRequest.of(page, size));
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        try {
            return orderService.getOrderById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            if (order == null || order.getCustomer() == null || order.getItems() == null || order.getItems().isEmpty()) {
                return ResponseEntity.badRequest().body("La orden debe incluir un cliente y al menos un item");
            }
            Order savedOrder = orderService.crearOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la orden: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la orden: " + e.getMessage());
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable Integer customerId) {
        try {
            Customer customer = new Customer();
            customer.setId(customerId);
            List<Order> orders = orderService.getOrdersByCustomer(customer);
            if (orders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron órdenes para el cliente");
            }
            return ResponseEntity.ok(orders);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las órdenes: " + e.getMessage());
        }
    }
}