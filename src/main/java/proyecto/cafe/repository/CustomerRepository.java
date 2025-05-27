package proyecto.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Customer;

/**
 * Repository for the Customer entity.
 * Provides methods to access and manipulate customer data in the database.
 * @author Maria
 * @version 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

