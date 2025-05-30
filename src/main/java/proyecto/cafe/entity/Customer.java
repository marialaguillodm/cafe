package proyecto.cafe.entity;

import jakarta.persistence.*;

/**
 * Representa un cliente en el sistema.
 * Esta entidad almacena la información básica de un cliente,
 * incluyendo su nombre y correo electrónico.
 * 
 * @author Maria
 * @version 1.3
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Constructor por defecto.
     */
    public Customer() {
    }

    /**
     * Constructor con todos los campos.
     * @param id Identificador único del cliente
     * @param name Nombre del cliente
     * @param email Correo electrónico del cliente
     */
    public Customer(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del cliente.
     * @param name Nuevo nombre del cliente
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return Correo electrónico del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     * @param email Nuevo correo electrónico del cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
