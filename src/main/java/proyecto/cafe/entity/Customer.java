package proyecto.cafe.entity;

/**
 * Representa un cliente en el sistema.
 * Esta entidad almacena la información básica de un cliente, id y Usuario
 * @author Maria
 * @version 1.0
 */
public class Customer {
    private Integer id;
    private String usuario;

    /**
     * Constructor por defecto.
     */
    public Customer() {
    }

    /**
     * Constructor con todos los campos.
     * @param id Identificador único del cliente
     * @param usuario Usuario del cliente
     */
    public Customer(Integer id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    /**
     * Obtiene el identificador único del cliente.
     * @return ID del cliente
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del cliente.
     * @param id Nuevo ID del cliente
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el Usuario del cliente.
     * @return usuario del cliente
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre del cliente.
     * @param usuario Nuevo Usuario del cliente
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
