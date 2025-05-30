package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.entity.Order;
import proyecto.cafe.entity.OrderItem;
import proyecto.cafe.repository.CafeRepository;
import proyecto.cafe.repository.CustomerRepository;
import proyecto.cafe.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de órdenes en el sistema.
 * Proporciona métodos para realizar operaciones CRUD y consultas
 * sobre la entidad Order, incluyendo la validación de clientes y cafés.
 * 
 * @author Maria
 * @version 1.5
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CafeRepository cafeRepository;

    /**
     * Obtiene todas las órdenes registradas en el sistema de forma paginada.
     * 
     * @param pageable Configuración de la paginación (número de página, tamaño, ordenamiento)
     * @return Página de órdenes
     */
    @Transactional(readOnly = true)
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    /**
     * Busca una orden por su ID.
     * 
     * @param id ID de la orden a buscar
     * @return Optional que puede contener la orden si existe
     */
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    /**
     * Crea una nueva orden en el sistema.
     * Valida que el cliente y los cafés existan, y establece los precios actuales.
     * 
     * @param order Orden a crear
     * @return Orden creada con su ID asignado
     * @throws IllegalArgumentException si el cliente o algún café no existe
     * @throws IllegalStateException si hay un error al guardar la orden
     */
    @Transactional
    public Order crearOrder(Order order) {
        // Validar que el cliente existe
        Customer customer = customerRepository.findById(order.getCustomer().getId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Validar que todos los cafés existen y establecer el precio actual
        for (OrderItem item : order.getItems()) {
            Cafe cafe = cafeRepository.findById(item.getCafe().getId())
                .orElseThrow(() -> new IllegalArgumentException("Café no encontrado: " + item.getCafe().getId()));
            item.setCafe(cafe);
            item.setPrecio(cafe.getPrecio());
        }

        // Establecer el cliente y calcular el total
        order.setCustomer(customer);
        order.setCreationDate(LocalDateTime.now());
        order.calculateTotal();

        // Guardar la orden
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getId() == null) {
            throw new IllegalStateException("Error al guardar la orden");
        }

        return savedOrder;
    }

    /**
     * Elimina una orden del sistema por su ID.
     * Valida que la orden exista antes de eliminarla.
     * 
     * @param id ID de la orden a eliminar
     * @throws IllegalArgumentException si la orden no existe
     */
    @Transactional
    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Orden no encontrada");
        }
        orderRepository.deleteById(id);
    }

    /**
     * Obtiene todas las órdenes asociadas a un cliente específico.
     * Valida que el cliente exista antes de buscar sus órdenes.
     * 
     * @param customer Cliente del cual se desean obtener las órdenes
     * @return Lista de órdenes del cliente
     * @throws IllegalArgumentException si el cliente no existe
     */
    public List<Order> getOrdersByCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        return orderRepository.findByCustomer(customer);
    }
}