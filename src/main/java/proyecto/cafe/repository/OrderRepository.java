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
 * Repository for the Order entity.
 * Provides methods to access and manipulate order data in the database.
 * @author Maria
 * @version 1.0
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer")
    List<Order> findAllWithCustomer();

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer")
    Page<Order> findAll(Pageable pageable);
}
