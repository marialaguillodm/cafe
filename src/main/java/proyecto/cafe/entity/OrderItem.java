package proyecto.cafe.entity;

import jakarta.persistence.*;

/**
 * Representa un item individual dentro de una orden.
 * Esta entidad almacena la información de un producto específico en una orden,
 * incluyendo el café, el precio y la cantidad.
 * @author Maria
 * @version 1.0
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;
    
    private Integer cantidad;
    private Double precio;

    /**
     * Constructor por defecto.
     */
    public OrderItem() {
    }

    /**
     * Constructor con todos los campos.
     * @param cafe Café del item
     * @param precio Precio del café
     * @param cantidad Cantidad del café
     */
    public OrderItem(Integer id, Cafe cafe, Integer cantidad, Double precio) {
        this.id = id;
        this.cafe = cafe;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}