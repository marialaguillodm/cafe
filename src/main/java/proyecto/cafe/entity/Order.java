package proyecto.cafe.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the system.
 * This entity stores order information,
 * including customer, items and total.
 * @author Maria
 * @version 1.0
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
     */
    public Order() {
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Constructor con todos los campos.
     * @param id Identificador único de la orden
     * @param customer Cliente de la orden
     * @param items Lista de items en la orden
     */
    public Order(Integer id, Customer customer, List<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Calcula el total de la orden multiplicando el precio unitario por la cantidad de cada item.
     * El precio unitario se obtiene del café y la cantidad se especifica en el item.
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
        calculateTotal();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}