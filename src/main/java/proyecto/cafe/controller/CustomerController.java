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
 * Controlador REST que maneja las operaciones relacionadas con clientes.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Customer.
 * Endpoints disponibles:
 * - GET /customers: Obtener todos los clientes
 * - POST /customers: Crear un nuevo cliente
 * - PUT /customers/{id}: Actualizar un cliente existente
 * - DELETE /customers/{id}: Eliminar un cliente
 * - GET /customers/{id}: Obtener un cliente por su ID
 * @author Maria
 * @version 1.5
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Obtiene todos los clientes registrados.
     * @param page Número de página (comienza en 0)
     * @param size Tamaño de la página
     * @return ResponseEntity con la lista paginada de clientes o mensaje de error
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
     * Obtiene un cliente por su ID.
     * @param id ID del cliente a obtener
     * @return ResponseEntity con el cliente encontrado o mensaje de error
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
     * Crea un nuevo cliente.
     * @param customer Datos del cliente a crear
     * @return ResponseEntity con el cliente creado o mensaje de error
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
     * Actualiza completamente un cliente existente.
     * @param id ID del cliente a actualizar
     * @param customer Nuevos datos del cliente
     * @return ResponseEntity con el cliente actualizado o mensaje de error
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
     * Elimina un cliente por su ID.
     * @param id ID del cliente a eliminar
     * @return ResponseEntity con mensaje de éxito o error
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
