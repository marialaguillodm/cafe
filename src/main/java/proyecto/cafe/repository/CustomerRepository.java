package proyecto.cafe.repository;

import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Customer;

import java.util.*;

@Repository
public class CustomerRepository {
    private final Map<Integer, Customer> customers = new HashMap<>();

    public Collection<Customer> findAll() {
        return customers.values();
    }

    public Optional<Customer> findById(Integer id) {
        return Optional.ofNullable(customers.get(id));
    }

    public Customer save(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    public void delete(Integer id) {
        customers.remove(id);
    }

    public boolean existsById(Integer id) {
        return customers.containsKey(id);
    }
}

