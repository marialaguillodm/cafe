package proyecto.cafe.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Integer id;
    private Integer customerId;
    private List<OrderItem> items;
    private double total;
    private LocalDateTime fechaCreacion;

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

    // Getters y Setters...

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
