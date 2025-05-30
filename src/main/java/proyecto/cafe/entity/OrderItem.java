package proyecto.cafe.entity;

import jakarta.persistence.*;

/**
 * Representa un item individual dentro de una orden.
 * Esta entidad almacena la información de un producto específico en una orden,
 * incluyendo el café, la cantidad y el precio unitario.
 * 
 * @author Maria
 * @version 1.5
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
     * @param id Identificador único del item
     * @param cafe Café seleccionado
     * @param cantidad Cantidad del café
     * @param precio Precio unitario del café
     */
    public OrderItem(Integer id, Cafe cafe, Integer cantidad, Double precio) {
        this.id = id;
        this.cafe = cafe;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Obtiene el identificador único del item.
     * @return ID del item
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del item.
     * @param id Nuevo ID del item
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el café del item.
     * @return Café seleccionado
     */
    public Cafe getCafe() {
        return cafe;
    }

    /**
     * Establece el café del item.
     * @param cafe Nuevo café seleccionado
     */
    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    /**
     * Obtiene la cantidad del café.
     * @return Cantidad seleccionada
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del café.
     * @param cantidad Nueva cantidad seleccionada
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del café.
     * @return Precio unitario
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del café.
     * @param precio Nuevo precio unitario
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}