package proyecto.cafe.entity;

public class OrderItem {
    private Integer cafeId;
    private double precio;
    private int cantidad;

    public OrderItem(Integer cafeId, double precio, int cantidad) {
        this.cafeId = cafeId;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Integer getCafeId() {
        return cafeId;
    }
    public void setCafeId(Integer cafeId) {
        this.cafeId = cafeId;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
