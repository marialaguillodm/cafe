package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.repository.CustomerRepository;

import java.util.Optional;

/**
 * Servicio para la gestión de clientes en el sistema.
 * Proporciona métodos para realizar operaciones CRUD y consultas
 * sobre la entidad Customer.
 * 
 * @author Maria
 * @version 1.5
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Obtiene todos los clientes registrados en el sistema de forma paginada.
     * 
     * @param pageable Configuración de la paginación (número de página, tamaño, ordenamiento)
     * @return Página de clientes
     */
    @Transactional(readOnly = true)
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    /**
     * Busca un cliente por su ID.
     * 
     * @param id ID del cliente a buscar
     * @return Optional que puede contener el cliente si existe
     */
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    /**
     * Crea un nuevo cliente en el sistema.
     * Valida que el nombre y email no estén vacíos.
     * 
     * @param customer Cliente a crear
     * @return Cliente creado con su ID asignado
     * @throws IllegalArgumentException si el nombre o email están vacíos
     */
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

    /**
     * Actualiza un cliente existente en el sistema.
     * Valida que el cliente exista y que el nombre y email no estén vacíos.
     * 
     * @param customer Cliente con los datos actualizados
     * @return Cliente actualizado
     * @throws IllegalArgumentException si el cliente no existe o si el nombre o email están vacíos
     */
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

    /**
     * Elimina un cliente del sistema por su ID.
     * Valida que el cliente exista antes de eliminarlo.
     * 
     * @param id ID del cliente a eliminar
     * @throws IllegalArgumentException si el cliente no existe
     */
    @Transactional
    public void deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        customerRepository.deleteById(id);
    }
}

