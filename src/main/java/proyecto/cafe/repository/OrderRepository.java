package proyecto.cafe.repository;

import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Order;
import proyecto.cafe.entity.OrderItem;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Repositorio que maneja el almacenamiento y recuperación de órdenes.
 * Implementa un almacenamiento en memoria usando un Map.
 * Mantiene las relaciones entre órdenes, clientes y cafés.
 * @author Maria
 * @version 1.0
 */
@Repository
public class OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();

    /**
     * Constructor que inicializa el repositorio con datos de ejemplo.
     * Crea tres órdenes predefinidas con diferentes clientes y cafés.
     */
    public OrderRepository() {
        // Orden 1: Juan Pérez - 2 Espressos
        Order order1 = new Order();
        order1.setId(1);
        order1.setCustomerId(1);
        List<OrderItem> items1 = new ArrayList<>();
        items1.add(new OrderItem(1, 2.50, 2));
        order1.setItems(items1);
        order1.setFechaCreacion(LocalDateTime.now().minusDays(2));
        orders.put(1, order1);

        // Orden 2: María García - 1 Latte y 1 Cappuccino
        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomerId(2);
        List<OrderItem> items2 = new ArrayList<>();
        items2.add(new OrderItem(2, 3.75, 1));
        items2.add(new OrderItem(3, 3.50, 1));
        order2.setItems(items2);
        order2.setFechaCreacion(LocalDateTime.now().minusDays(6));
        orders.put(2, order2);

        // Orden 3: Carlos López - 3 Americanos
        Order order3 = new Order();
        order3.setId(3);
        order3.setCustomerId(3);
        List<OrderItem> items3 = new ArrayList<>();
        items3.add(new OrderItem(4, 2.00, 3));
        order3.setItems(items3);
        order3.setFechaCreacion(LocalDateTime.now().minusDays(20));
        orders.put(3, order3);
    }

    /**
     * Obtiene todas las órdenes almacenadas.
     * @return Colección con todas las órdenes
     */
    public Collection<Order> findAll() {
        return orders.values();
    }

    /**
     * Busca una orden por su ID.
     * @param id ID de la orden a buscar
     * @return Optional que contiene la orden si existe, o vacío si no existe
     */
    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(orders.get(id));
    }

    /**
     * Busca todas las órdenes de un cliente específico.
     * @param customerId ID del cliente
     * @return Lista de órdenes del cliente
     */
    public List<Order> findByCustomerId(Integer customerId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomerId().equals(customerId)) {
                result.add(order);
            }
        }
        return result;
    }

    /**
     * Guarda una orden en el repositorio.
     * Si la orden ya existe, actualiza sus datos.
     * @param order Orden a guardar
     * @return La orden guardada
     */
    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    /**
     * Elimina una orden del repositorio.
     * @param id ID de la orden a eliminar
     */
    public void delete(Integer id) {
        orders.remove(id);
    }
}
