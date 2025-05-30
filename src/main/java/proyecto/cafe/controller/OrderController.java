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

/**
 * Controlador REST que maneja las operaciones relacionadas con órdenes.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Order.
 * Endpoints disponibles:
 * - GET /orders: Obtener todas las órdenes
 * - POST /orders: Crear una nueva orden
 * - DELETE /orders/{id}: Eliminar una orden
 * - GET /orders/{id}: Obtener una orden por su ID
 * - GET /orders/customer/{customerId}: Obtener órdenes por cliente
 * @author Maria
 * @version 1.5
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Obtiene todas las órdenes registradas.
     * @param page Número de página (comienza en 0)
     * @param size Tamaño de la página
     * @return ResponseEntity con la lista paginada de órdenes o mensaje de error
     */
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

    /**
     * Obtiene una orden por su ID.
     * @param id ID de la orden a obtener
     * @return ResponseEntity con la orden encontrada o mensaje de error
     */
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

    /**
     * Crea una nueva orden.
     * @param order Datos de la orden a crear
     * @return ResponseEntity con la orden creada o mensaje de error
     */
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

    /**
     * Elimina una orden por su ID.
     * @param id ID de la orden a eliminar
     * @return ResponseEntity con mensaje de éxito o error
     */
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

    /**
     * Obtiene todas las órdenes asociadas a un cliente.
     * @param customerId ID del cliente
     * @return Lista de órdenes del cliente o mensaje de error
     */
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