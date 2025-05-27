package proyecto.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.service.CafeService;
import proyecto.cafe.service.OrderService;

import java.util.List;

/**
 * Controlador REST que maneja las operaciones relacionadas con cafés.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Cafe.
 * Endpoints disponibles:
 * - GET /cafes: Obtener todos los cafés
 * - POST /cafes: Crear un nuevo café
 * - PUT /cafes/{id}: Actualizar un café existente
 * - PATCH /cafes/{id}: Actualizar parcialmente un café
 * - DELETE /cafes/{id}: Eliminar un café
 * - GET /cafes/customer/{customerId}: Obtener pedidos por cliente
 *
 * @author Maria
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cafes")
@CrossOrigin(origins = "*")
public class CafeController {

    private final CafeService cafeService;
    private final OrderService orderService;

    /**
     * Constructor que inyecta el servicio de cafés y el servicio de pedidos.
     * @param cafeService Servicio que maneja la lógica de negocio de cafés
     * @param orderService Servicio que maneja la lógica de negocio de pedidos
     */
    public CafeController(CafeService cafeService, OrderService orderService) {
        this.cafeService = cafeService;
        this.orderService = orderService;
    }

    /**
     * Obtiene todos los cafés registrados.
     * @return ResponseEntity con la lista de cafés o mensaje si no hay registros
     */
    @GetMapping
    public ResponseEntity<Page<Cafe>> getAllCafes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Cafe> cafes = cafeService.getAllCafes(PageRequest.of(page, size));
            return ResponseEntity.ok(cafes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Obtiene un café por su ID.
     * @param id ID del café a obtener
     * @return ResponseEntity con el café encontrado o mensaje de error
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cafe> getCafeById(@PathVariable Integer id) {
        try {
            return cafeService.getCafeById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Crea un nuevo café.
     * @param cafe Datos del café a crear
     * @return ResponseEntity con el café creado o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> createCafe(@RequestBody Cafe cafe) {
        try {
            if (cafe == null || cafe.getNombre() == null || cafe.getPrecio() == null) {
                return ResponseEntity.badRequest().body("El café debe incluir nombre y precio");
            }
            ResponseEntity<?> savedCafe = cafeService.crearCafe(cafe);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCafe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el café: " + e.getMessage());
        }
    }

    /**
     * Actualiza completamente un café existente.
     * @param id ID del café a actualizar
     * @param cafe Nuevos datos del café
     * @return ResponseEntity con el café actualizado o mensaje de error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        try {
            if (cafe == null || cafe.getNombre() == null || cafe.getPrecio() == null) {
                return ResponseEntity.badRequest().body("El café debe incluir nombre y precio");
            }
            cafe.setId(id);
            ResponseEntity<?> updatedCafe = cafeService.actualizarCafe(id,cafe);
            return ResponseEntity.ok(updatedCafe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el café: " + e.getMessage());
        }
    }

    /**
     * Elimina un café por su ID.
     * @param id ID del café a eliminar
     * @return ResponseEntity con mensaje de éxito o error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCafe(@PathVariable Integer id) {
        try {
            cafeService.eliminarCafe(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el café: " + e.getMessage());
        }
    }

    /**
     * Obtiene pedidos por cliente.
     * @param customer ID del cliente
     * @return ResponseEntity con la lista de pedidos o mensaje de error
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomer(@PathVariable Customer customer) {
        try {
            var orders = orderService.getOrdersByCustomer(customer);
            if (orders.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(orders);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}