package proyecto.cafe.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.repository.CustomerRepository;

import java.util.Collection;
import java.util.List;

/**
 * Servicio que maneja la lógica de negocio relacionada con los clientes.
 * Proporciona operaciones CRUD y validaciones para la entidad Customer.
 * @author Maria
 * @version 1.0
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Constructor que inyecta el repositorio de clientes.
     * @param customerRepository Repositorio para acceder a los datos de clientes
     */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Crea un nuevo cliente.
     * Valida los campos requeridos antes de guardar.
     * @param customer El cliente a crear
     * @return ResponseEntity con el cliente creado o mensaje de error
     */
    public ResponseEntity<?> crearCliente(Customer customer) {
        ResponseEntity<?> validationResponse = validateCustomerFields(customer);
        if (validationResponse != null) {
            return validationResponse;
        }
        // Generar nuevo ID si no existe
        if (customer.getId() == null) {
            customer.setId(generateNewId());
        }
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    /**
     * Actualiza completamente un cliente existente.
     * @param id ID del cliente a actualizar
     * @param customer Nuevos datos del cliente
     * @return ResponseEntity con el cliente actualizado o mensaje de error
     */
    public ResponseEntity<?> actualizarCliente(Integer id, Customer customer) {
        ResponseEntity<?> idValidation = validateId(id);
        if (idValidation != null) {
            return idValidation;
        }
        ResponseEntity<?> customerValidation = validateCustomerFields(customer);
        if (customerValidation != null) {
            return customerValidation;
        }
        ResponseEntity<?> notFoundResponse = validateCustomerExists(id);
        if (notFoundResponse != null) {
            return notFoundResponse;
        }
        customer.setId(id);
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * Elimina un cliente por su ID.
     * @param id ID del cliente a eliminar
     * @return ResponseEntity con mensaje de éxito o error
     */
    public ResponseEntity<?> eliminarCliente(Integer id) {
        ResponseEntity<?> idValidation = validateId(id);
        if (idValidation != null) {
            return idValidation;
        }
        ResponseEntity<?> notFoundResponse = validateCustomerExists(id);
        if (notFoundResponse != null) {
            return notFoundResponse;
        }
        customerRepository.delete(id);
        return ResponseEntity.ok().build();
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
        return validateString(customer.getUsuario(), "usuario");
    }

    /**
     * Valida que una cadena no sea nula o vacía.
     * @param value Cadena a validar
     * @param fieldUsuario Nombre del campo para el mensaje de error
     * @return ResponseEntity con mensaje de error si es inválida, null si es válida
     */
    private ResponseEntity<?> validateString(String value, String fieldUsuario) {
        if (value == null || value.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El campo " + fieldUsuario + " es obligatorio.");
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
}

