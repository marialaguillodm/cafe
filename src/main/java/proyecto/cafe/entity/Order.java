package proyecto.cafe.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una orden en el sistema.
 * Esta entidad almacena la información de una orden,
 * incluyendo el cliente, los items y el total.
 * 
 * @author Maria
 * @version 1.5
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    private Double total;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    /**
     * Constructor por defecto.
     * Inicializa la fecha de creación con la fecha y hora actual.
     */
    public Order() {
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Constructor con todos los campos.
     * @param id Identificador único de la orden
     * @param customer Cliente que realiza la orden
     * @param items Lista de items en la orden
     */
    public Order(Integer id, Customer customer, List<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Calcula el total de la orden.
     * Multiplica el precio unitario por la cantidad de cada item
     * y suma todos los subtotales.
     */
    public void calculateTotal() {
        if (items != null) {
            this.total = items.stream()
                .filter(item -> item.getPrecio() != null)
                .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                .sum();
        } else {
            this.total = 0.0;
        }
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
     * Obtiene el cliente de la orden.
     * @return Cliente que realizó la orden
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Establece el cliente de la orden.
     * @param customer Nuevo cliente de la orden
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Obtiene la lista de items de la orden.
     * @return Lista de items en la orden
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Establece la lista de items de la orden.
     * @param items Nueva lista de items
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
        calculateTotal();
    }

    /**
     * Obtiene el total de la orden.
     * @return Total de la orden
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Establece el total de la orden.
     * @param total Nuevo total de la orden
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Obtiene la fecha de creación de la orden.
     * @return Fecha y hora de creación
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Establece la fecha de creación de la orden.
     * @param creationDate Nueva fecha y hora de creación
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}