package proyecto.cafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.service.CustomerService;

/**
 * Controlador REST que maneja las operaciones relacionadas con clientes.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Customer.
 * Endpoints disponibles:
 * - POST /customers: Crear un nuevo cliente
 * - PUT /customers/{id}: Actualizar un cliente existente
 * - DELETE /customers/{id}: Eliminar un cliente
 * @author Maria
 * @version 1.0
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor que inyecta el servicio de clientes.
     * @param customerService Servicio que maneja la lógica de negocio de clientes
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Crea un nuevo cliente.
     * @param customer Datos del cliente a crear
     * @return ResponseEntity con el cliente creado o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Customer customer) {
        return customerService.crearCliente(customer);
    }

    /**
     * Actualiza completamente un cliente existente.
     * @param id ID del cliente a actualizar
     * @param customer Nuevos datos del cliente
     * @return ResponseEntity con el cliente actualizado o mensaje de error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer id, @RequestBody Customer customer) {
        return customerService.actualizarCliente(id, customer);
    }

    /**
     * Elimina un cliente por su ID.
     * @param id ID del cliente a eliminar
     * @return ResponseEntity con mensaje de éxito o error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
        return customerService.eliminarCliente(id);
    }
}
