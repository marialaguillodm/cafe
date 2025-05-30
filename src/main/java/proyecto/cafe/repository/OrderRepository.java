package proyecto.cafe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.entity.Order;
import java.util.List;

/**
 * Repositorio para la gestión de órdenes en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas
 * sobre la entidad Order.
 * 
 * @author Maria
 * @version 1.3
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * Busca todas las órdenes asociadas a un cliente específico.
     * 
     * @param customer Cliente del cual se desean obtener las órdenes
     * @return Lista de órdenes del cliente
     */
    List<Order> findByCustomer(Customer customer);

    /**
     * Obtiene todas las órdenes incluyendo la información del cliente.
     * Utiliza un JOIN FETCH para cargar eficientemente los datos del cliente
     * en una sola consulta.
     * 
     * @return Lista de órdenes con sus clientes asociados
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer")
    List<Order> findAllWithCustomer();

    /**
     * Obtiene todas las órdenes de forma paginada, incluyendo la información del cliente.
     * Utiliza un JOIN FETCH para cargar eficientemente los datos del cliente
     * en una sola consulta.
     * 
     * @param pageable Configuración de la paginación (número de página, tamaño, ordenamiento)
     * @return Página de órdenes con sus clientes asociados
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer")
    Page<Order> findAll(Pageable pageable);
}
