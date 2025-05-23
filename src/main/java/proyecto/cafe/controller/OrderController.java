package proyecto.cafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Order;
import proyecto.cafe.service.OrderService;

/**
 * Controlador REST que maneja las operaciones relacionadas con órdenes.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Order.
 * Endpoints disponibles:
 * - GET /orders: Obtener todas las órdenes
 * - GET /orders/{id}: Obtener una orden por ID
 * - GET /orders/customer/{customerId}: Obtener órdenes por cliente
 * - POST /orders: Crear una nueva orden
 * @author Maria
 * @version 1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructor que inyecta el servicio de órdenes.
     * @param orderService Servicio que maneja la lógica de negocio de órdenes
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Obtiene todas las órdenes registradas.
     * @return ResponseEntity con la lista de órdenes o mensaje si no hay registros
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodasLasOrdenes() {
        return orderService.obtenerTodasLasOrdenes();
    }

    /**
     * Obtiene una orden específica por su ID.
     * @param id ID de la orden a buscar
     * @return ResponseEntity con la orden encontrada o mensaje de error
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    /**
     * Obtiene todas las órdenes de un cliente específico.
     * @param customerId ID del cliente
     * @return ResponseEntity con la lista de órdenes del cliente o mensaje de error
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable Integer customerId) {
        return orderService. obtenerOrdenesPorCliente(customerId);
    }

    /**
     * Crea una nueva orden.
     * Valida que el cliente exista y que todos los cafés sean válidos.
     * @param order Datos de la orden a crear
     * @return ResponseEntity con la orden creada o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return orderService.crearOrden(order);
    }

}