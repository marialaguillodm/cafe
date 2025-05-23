package proyecto.cafe.entity;

/**
 * Representa un producto de café en el sistema.
 * Esta entidad almacena la información básica de un café, id, nombre,
 * descripción y precio.
 * @author Maria
 * @version 1.0
 */
public class Cafe {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;

    /**
     * Constructor por defecto.
     */
    public Cafe() {
    }

    /**
     * Constructor con todos los campos.
     * @param id Identificador único del café
     * @param nombre Nombre del café
     * @param descripcion Descripción detallada del café
     * @param precio Precio del café
     */
    public Cafe(Integer id, String nombre, String descripcion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Obtiene el identificador único del café.
     * @return ID del café
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del café.
     * @param id Nuevo ID del café
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del café.
     * @return Nombre del café
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del café.
     * @param nombre Nuevo nombre del café
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción detallada del café.
     * @return Descripción del café
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción detallada del café.
     * @param descripcion Nueva descripción del café
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
}
