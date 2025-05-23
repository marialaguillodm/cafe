package proyecto.cafe.entity;

/**
 * Representa un item individual dentro de una orden.
 * Esta entidad almacena la información de un producto específico en una orden,
 * incluyendo el id del café, el precio y  la cantidad.
 * @author Maria
 * @version 1.0
 */
public class OrderItem {
    private Integer cafeId;
    private Double precio;
    private int cantidad;

    /**
     * Constructor por defecto.
     */
    public OrderItem() {
    }

    /**
     * Constructor con todos los campos.
     * @param cafeId Identificador del café
     * @param precio Precio del café
     * @param cantidad Cantidad del café
     */
    public OrderItem(Integer cafeId, Double precio, int cantidad) {
        this.cafeId = cafeId;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el identificador del café.
     * @return Identificador del café
     */
    public Integer getCafeId() {
        return cafeId;
    }

    /**
     * Establece el identificador del café.
     * @param cafeId Nuevo identificador del café
     */
    public void setCafeId(Integer cafeId) {
        this.cafeId = cafeId;
    }

    /**
     * Obtiene el precio del café.
     * @return Precio del café
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del café.
     * @param precio Nuevo precio del café
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad del café.
     * @return Cantidad del café
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del café.
     * @param cantidad Nueva cantidad del café
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
