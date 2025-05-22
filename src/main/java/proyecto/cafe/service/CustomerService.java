package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> createCustomer(Customer customer) {
        if (customer.getNombre() == null || customer.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del cliente es obligatorio.");
        }
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    public ResponseEntity<?> updateCustomer(Integer id, Customer customer) {
        Customer existing = customerRepository.findById(id);
        if (existing == null) {
            return ResponseEntity.status(404).body("Cliente no encontrado.");
        }
        if (customer.getNombre() == null || customer.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del cliente es obligatorio.");
        }
        customer.setId(id);
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    public ResponseEntity<?> deleteCustomer(Integer id) {
        Customer existing = customerRepository.findById(id);
        if (existing == null) {
            return ResponseEntity.status(404).body("Cliente no encontrado.");
        }
        customerRepository.delete(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente.");
    }
}

