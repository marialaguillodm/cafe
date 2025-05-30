package proyecto.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Customer;

/**
 * Repositorio para la entidad Customer.
 * Proporciona métodos para acceder y manipular los datos de cafés en la base de datos.
 * @author Maria
 * @version 1.3
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

