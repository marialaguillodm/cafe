package proyecto.cafe.entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa una orden en el sistema.
 * Esta entidad almacena la información de una orden, incluyendo el cliente que la realizó,
 * los items de la orden y el total calculado.
 * @author Maria
 * @version 1.0
 */
public class Order {
    private Integer id;
    private Integer customerId;
    private List<OrderItem> items;
    private double total;
    private LocalDateTime fechaCreacion;

    /**
     * Constructor por defecto.
     */
    public Order() {
    }

    /**
     * Constructor con todos los campos.
     * @param id Identificador único de la orden
     * @param customerId Cliente que realizó la orden
     * @param items Lista de items en la orden
     */
    public Order(Integer id, Integer customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.fechaCreacion = LocalDateTime.now();
        calculateTotal();
    }

    public void calculateTotal() {
        this.total = items.stream().mapToDouble(i -> i.getPrecio() * i.getCantidad()).sum();
    }

    /**
     * Obtiene el identificador único de la orden.
     * @return ID de la orden
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la orden.
     * @param id Nuevo ID de la orden
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el cliente que realizó la orden.
     * @return Cliente de la orden
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Establece el cliente que realizó la orden.
     * @param customerId Nuevo cliente de la orden
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Obtiene la lista de items en la orden.
     * @return Lista de items de la orden
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Establece la lista de items en la orden.
     * @param items Nueva lista de items de la orden
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    /**
     * Obtiene el total calculado de la orden.
     * @return Total de la orden
     */
    public double getTotal() {
        return total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación de la orden.
     * @param fechaCreacion Nueva fecha de creación
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
