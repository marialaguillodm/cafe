package proyecto.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.service.CustomerService;

import java.util.List;

/**
 * REST controller for managing customers.
 * Provides endpoints for CRUD operations on customers.
 * @author Maria
 * @version 1.0
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Get all customers.
     * @return List of all customers
     */
    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Customer> customers = customerService.getAllCustomers(PageRequest.of(page, size));
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get a customer by ID.
     * @param id Customer ID
     * @return Customer or error message
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        try {
            return customerService.getCustomerById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create a new customer.
     * @param customer Customer to create
     * @return Created customer or error message
     */
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            if (customer == null || customer.getName() == null || customer.getEmail() == null) {
                return ResponseEntity.badRequest().body("El cliente debe incluir nombre y email");
            }
            Customer savedCustomer = customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el cliente: " + e.getMessage());
        }
    }

    /**
     * Update an existing customer.
     * @param id Customer ID
     * @param customer Updated customer data
     * @return Updated customer or error message
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            if (customer == null || customer.getName() == null || customer.getEmail() == null) {
                return ResponseEntity.badRequest().body("El cliente debe incluir nombre y email");
            }
            customer.setId(id);
            Customer updatedCustomer = customerService.updateCustomer(customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    /**
     * Delete a customer.
     * @param id Customer ID
     * @return Success message or error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el cliente: " + e.getMessage());
        }
    }
}
