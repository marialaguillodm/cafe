package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.repository.CustomerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing customers.
 * Provides business logic for customer operations.
 * @author Maria
 * @version 1.0
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Get all customers.
     * @return List of all customers or empty message
     */
    public ResponseEntity<?> obtenerTodos() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            return ResponseEntity.ok("No hay clientes registrados");
        }
        return ResponseEntity.ok(customers);
    }

    /**
     * Create a new customer.
     * @param customer Customer to create
     * @return Created customer or error message
     */
    @Transactional
    public ResponseEntity<?> crearCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del cliente no puede estar vacío");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El email del cliente no puede estar vacío");
        }
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    /**
     * Update an existing customer.
     * @param id Customer ID
     * @param customer Updated customer data
     * @return Updated customer or error message
     */
    @Transactional
    public ResponseEntity<?> actualizarCustomer(Integer id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del cliente no puede estar vacío");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El email del cliente no puede estar vacío");
        }
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    /**
     * Partially update an existing customer.
     * @param id Customer ID
     * @param customer Partial customer data
     * @return Updated customer or error message
     */
    public ResponseEntity<?> actualizarParcialmenteCustomer(Integer id, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Customer updatedCustomer = existingCustomer.get();
        if (customer.getName() != null) {
            updatedCustomer.setName(customer.getName());
        }
        if (customer.getEmail() != null) {
            updatedCustomer.setEmail(customer.getEmail());
        }
        
        return ResponseEntity.ok(customerRepository.save(updatedCustomer));
    }

    /**
     * Delete a customer.
     * @param id Customer ID
     * @return Success message or error
     */
    @Transactional
    public ResponseEntity<?> eliminarCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }

    /**
     * Valida que un ID no sea nulo.
     * @param id ID a validar
     * @return ResponseEntity con mensaje de error si es nulo, null si es válido
     */
    private ResponseEntity<?> validateId(Integer id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El ID del cliente es obligatorio.");
        }
        return null;
    }

    /**
     * Valida que un objeto cliente no sea nulo.
     * @param customer Cliente a validar
     * @return ResponseEntity con mensaje de error si es nulo, null si es válido
     */
    private ResponseEntity<?> validateObject(Customer customer) {
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El cliente no puede ser nulo.");
        }
        return null;
    }

    /**
     * Valida todos los campos requeridos de un cliente.
     * @param customer Cliente a validar
     * @return ResponseEntity con mensaje de error si hay campos inválidos, null si es válido
     */
    private ResponseEntity<?> validateCustomerFields(Customer customer) {
        ResponseEntity<?> objectValidation = validateObject(customer);
        if (objectValidation != null) {
            return objectValidation;
        }
        return validateString(customer.getName(), "nombre");
    }

    /**
     * Valida que una cadena no sea nula o vacía.
     * @param value Cadena a validar
     * @param fieldName Nombre del campo para el mensaje de error
     * @return ResponseEntity con mensaje de error si es inválida, null si es válida
     */
    private ResponseEntity<?> validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El campo " + fieldName + " es obligatorio.");
        }
        return null;
    }

    /**
     * Valida que un cliente exista en el repositorio.
     * @param id ID del cliente a validar
     * @return ResponseEntity con mensaje de error si no existe, null si existe
     */
    private ResponseEntity<?> validateCustomerExists(Integer id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Cliente no encontrado.");
        }
        return null;
    }

    /**
     * Genera un nuevo ID para un cliente.
     * Busca el ID más alto existente y le suma 1.
     * @return Nuevo ID generado
     */
    private Integer generateNewId() {
        return customerRepository.findAll().stream()
            .mapToInt(Customer::getId)
            .max()
            .orElse(0) + 1;
    }

    /**
     * Get a customer by ID.
     * @param id Customer ID
     * @return Customer or error message
     */
    public ResponseEntity<?> obtenerCustomerPorId(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @Transactional(readOnly = true)
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email del cliente no puede estar vacío");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email del cliente no puede estar vacío");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        customerRepository.deleteById(id);
    }
}

