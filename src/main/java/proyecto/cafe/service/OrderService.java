package proyecto.cafe.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.entity.Order;
import proyecto.cafe.entity.OrderItem;
import proyecto.cafe.repository.CafeRepository;
import proyecto.cafe.repository.CustomerRepository;
import proyecto.cafe.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Servicio que maneja la lógica de negocio relacionada con las órdenes.
 * Proporciona operaciones CRUD y validaciones para la entidad Order.
 * Gestiona las relaciones entre órdenes, clientes y cafés.
 * @author Maria
 * @version 1.0
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CafeRepository cafeRepository;

    /**
     * Constructor que inyecta los repositorios necesarios.
     * @param orderRepository Repositorio para acceder a los datos de órdenes
     * @param customerRepository Repositorio para acceder a los datos de clientes
     * @param cafeRepository Repositorio para acceder a los datos de cafés
     */
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, CafeRepository cafeRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.cafeRepository = cafeRepository;
    }

    /**
     * Obtiene todas las órdenes registradas.
     * @return ResponseEntity con la lista de órdenes o mensaje si no hay registros
     */
    public ResponseEntity<?> obtenerTodasLasOrdenes() {
        Collection<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("No hay órdenes registradas.");
        }
        return ResponseEntity.ok(orders);
    }

    /**
     * Obtiene todas las órdenes de un cliente específico.
     * @param customerId ID del cliente
     * @return ResponseEntity con la lista de órdenes o mensaje de error
     */
    public ResponseEntity<?> obtenerOrdenesPorCliente(Integer customerId) {
        ResponseEntity<?> validationResponse = validateCustomerExists(customerId);
        if (validationResponse != null) {
            return validationResponse;
        }
        Collection<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("No hay órdenes para este cliente.");
        }
        return ResponseEntity.ok(orders);
    }

    /**
     * Crea una nueva orden.
     * Valida los campos requeridos y la existencia del cliente antes de guardar.
     * @param order La orden a crear
     * @return ResponseEntity con la orden creada o mensaje de error
     */
    public ResponseEntity<?> crearOrden(Order order) {
        ResponseEntity<?> validationResponse = validateOrderFields(order);
        if (validationResponse != null) {
            return validationResponse;
        }
        validationResponse = validateCustomerExists(order.getCustomerId());
        if (validationResponse != null) {
            return validationResponse;
        }
        for (OrderItem item : order.getItems()) {
            Cafe cafe = cafeRepository.findById(item.getCafeId()).get();
            item.setPrecio(cafe.getPrecio());
        }
        order.setId(generateNewId());
        order.setFechaCreacion(LocalDateTime.now());
        order.calculateTotal();
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /**
     * Obtiene una orden por su ID.
     * @param id ID de la orden a buscar
     * @return ResponseEntity con la orden encontrada o mensaje de error
     */
    public ResponseEntity<?> getOrderById(Integer id) {
        ResponseEntity<?> validationResponse = validateOrderExists(id);
        if (validationResponse != null) {
            return validationResponse;
        }
        return ResponseEntity.ok(findOrderById(id));
    }

    /**
     * Valida todos los campos requeridos de una orden.
     * @param order Orden a validar
     * @return ResponseEntity con mensaje de error si hay campos inválidos, null si es válido
     */
    private ResponseEntity<?> validateOrderFields(Order order) {
        if (order == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("La orden no puede ser nula.");
        }
        if (order.getCustomerId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El cliente es obligatorio.");
        }
        if (order.getItems() == null || order.getItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("La orden debe tener al menos un item.");
        }
        return validateOrderItems(order.getItems());
    }

    /**
     * Valida los items de una orden.
     * Verifica que cada item tenga un café válido y cantidad positiva.
     * @param items Lista de items a validar
     * @return ResponseEntity con mensaje de error si hay items inválidos, null si son válidos
     */
    private ResponseEntity<?> validateOrderItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            if (item.getCafeId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El ID del café es obligatorio en cada item.");
            }
            if (!cafeRepository.existsById(item.getCafeId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Hay uno o mas cafes que no existen");
            }
            if (item.getCantidad() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cantidad debe ser mayor a 0 para el café con ID " + item.getCafeId());
            }
        }
        return null;
    }

    /**
     * Valida que el cliente exista.
     * @param customerId ID del cliente a validar
     * @return ResponseEntity con error si el cliente no existe, null si es válido
     */
    private ResponseEntity<?> validateCustomerExists(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Cliente no encontrado.");
        }
        return null;
    }

    /**
     * Valida que una orden exista en el repositorio.
     * @param id ID de la orden a validar
     * @return ResponseEntity con mensaje de error si no existe, null si existe
     */
    private ResponseEntity<?> validateOrderExists(Integer id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El ID de la orden es obligatorio.");
        }
        if (findOrderById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Orden no encontrada.");
        }
        return null;
    }

    /**
     * Busca una orden por su ID.
     * @param id ID de la orden a buscar
     * @return Orden encontrada o null si no existe
     */
    private Order findOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Genera un nuevo ID para una orden.
     * Busca el ID más alto existente y le suma 1.
     * @return Nuevo ID generado
     */
    private Integer generateNewId() {
        return orderRepository.findAll().stream()
            .mapToInt(Order::getId)
            .max()
            .orElse(0) + 1;
    }
}