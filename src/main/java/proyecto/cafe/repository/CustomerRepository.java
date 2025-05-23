package proyecto.cafe.repository;

import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Customer;
import java.util.*;

/**
 * Repositorio que maneja el almacenamiento y recuperación de clientes.
 * Implementa un almacenamiento en memoria usando un Map.
 * @author Maria
 * @version 1.0
 */
@Repository
public class CustomerRepository {
    private final Map<Integer, Customer> customers = new HashMap<>();

    /**
     * Constructor que inicializa el repositorio con datos de ejemplo.
     * Crea cuatro clientes predefinidos con diferentes nombres.
     */
    public CustomerRepository() {
        customers.put(1, new Customer(1, "María Laguillo"));
        customers.put(2, new Customer(2, "Agustín Martínez"));
        customers.put(3, new Customer(3, "Carmen del Moral"));
        customers.put(4, new Customer(4, "Charo Bravo"));
    }

    /**
     * Obtiene todos los clientes almacenados.
     * @return Colección con todos los clientes
     */
    public Collection<Customer> findAll() {
        return customers.values();
    }

    /**
     * Busca un cliente por su ID.
     * @param id ID del cliente a buscar
     * @return Optional que contiene el cliente si existe, o vacío si no existe
     */
    public Optional<Customer> findById(Integer id) {
        return Optional.ofNullable(customers.get(id));
    }

    /**
     * Guarda un cliente en el repositorio.
     * Si el cliente ya existe, actualiza sus datos.
     * @param customer Cliente a guardar
     * @return El cliente guardado
     */
    public Customer save(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    /**
     * Elimina un cliente del repositorio.
     * @param id ID del cliente a eliminar
     */
    public void delete(Integer id) {
        customers.remove(id);
    }

    /**
     * Verifica si existe un cliente con el ID especificado.
     * @param id ID del cliente a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existsById(Integer id) {
        return customers.containsKey(id);
    }
}

