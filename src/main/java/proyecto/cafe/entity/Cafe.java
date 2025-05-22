package proyecto.cafe.entity;

public class Cafe {
    private Integer id;
    private String nombre;
    private String origen;
    private Double precio;


    public Cafe(Integer id, String nombre, String origen, double precio ) {
        this.id =id;
        this.nombre = nombre;
        this.precio = precio;
        this.origen = origen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
