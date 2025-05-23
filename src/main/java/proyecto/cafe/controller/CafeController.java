package proyecto.cafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.service.CafeService;

/**
 * Controlador REST que maneja las operaciones relacionadas con cafés.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Cafe.
 * Endpoints disponibles:
 * - GET /cafe: Obtener todos los cafés
 * - POST /cafe: Crear un nuevo café
 * - PUT /cafe/{id}: Actualizar un café existente
 * - PATCH /cafe/{id}: Actualizar parcialmente un café
 * - DELETE /cafe/{id}: Eliminar un café
 *
 * @author Maria
 * @version 1.0
 */
@RestController
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    /**
     * Constructor que inyecta el servicio de cafés.
     * @param cafeService Servicio que maneja la lógica de negocio de cafés
     */
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    /**
     * Obtiene todos los cafés registrados.
     * @return ResponseEntity con la lista de cafés o mensaje si no hay registros
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosCafes() {
        return cafeService.obtenerTodos();
    }

    /**
     * Crea un nuevo café.
     * @param cafe Datos del café a crear
     * @return ResponseEntity con el café creado o mensaje de error
     */
    @PostMapping
    public ResponseEntity<?> crearCafe(@RequestBody Cafe cafe) {
        return cafeService.crearCafe(cafe);
    }

    /**
     * Actualiza completamente un café existente.
     * @param id ID del café a actualizar
     * @param cafe Nuevos datos del café
     * @return ResponseEntity con el café actualizado o mensaje de error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        return cafeService.actualizarCafe(id, cafe);
    }

    /**
     * Actualiza parcialmente un café existente.
     * @param id ID del café a actualizar
     * @param cafe Datos parciales del café
     * @return ResponseEntity con el café actualizado o mensaje de error
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialmenteCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        return cafeService.actualizarParcialmenteCafe(id, cafe);
    }

    /**
     * Elimina un café por su ID.
     * @param id ID del café a eliminar
     * @return ResponseEntity con mensaje de éxito o error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCafe(@PathVariable Integer id) {
        return cafeService.eliminarCafe(id);
    }
}