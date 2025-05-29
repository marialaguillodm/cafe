package proyecto.cafe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.repository.CafeRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio relacionada con los cafés.
 * Proporciona operaciones CRUD y validaciones para la entidad Cafe.
 * @author Maria
 * @version 1.0
 */
@Service
public class CafeService {

    private final CafeRepository cafeRepository;

    /**
     * Constructor que inyecta el repositorio de cafés.
     * @param cafeRepository Repositorio para acceder a los datos de cafés
     */
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }


    /**
     * Crea un nuevo café.
     * Valida los campos requeridos y permite que la base de datos genere el ID.
     * @param cafe El café a crear
     * @return ResponseEntity con el café creado o mensaje de error
     */
    public ResponseEntity<?> crearCafe(Cafe cafe) {
        ResponseEntity<?> validationResponse = validateCafeFields(cafe);
        if (validationResponse != null) {
            return validationResponse;
        }
        
        // No permitimos IDs manuales para nuevos cafés
        cafe.setId(null);
        
        Cafe savedCafe = cafeRepository.save(cafe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCafe);
    }

    /**
     * Actualiza completamente un café existente.
     * @param id ID del café a actualizar
     * @param cafe Nuevos datos del café
     * @return ResponseEntity con el café actualizado o mensaje de error
     */
    public ResponseEntity<?> actualizarCafe(Integer id, Cafe cafe) {
        ResponseEntity<?> idValidation = validateId(id);
        if (idValidation != null) {
            return idValidation;
        }
        ResponseEntity<?> cafeValidation = validateCafeFields(cafe);
        if (cafeValidation != null) {
            return cafeValidation;
        }
        ResponseEntity<?> notFoundResponse = validateCafeExists(id);
        if (notFoundResponse != null) {
            return notFoundResponse;
        }
        
        cafe.setId(id);
        cafeRepository.save(cafe);
        return ResponseEntity.ok(cafe);
    }

    /**
     * Elimina un café por su ID.
     * @param id ID del café a eliminar
     * @return ResponseEntity con mensaje de éxito o error
     */
    public ResponseEntity<?> eliminarCafe(Integer id) {
        ResponseEntity<?> idValidation = validateId(id);
        if (idValidation != null) {
            return idValidation;
        }
        ResponseEntity<?> notFoundResponse = validateCafeExists(id);
        if (notFoundResponse != null) {
            return notFoundResponse;
        }
        cafeRepository.deleteById(id);
        return ResponseEntity.ok("Café eliminado correctamente");
    }

    /**
     * Valida que un café exista en el repositorio.
     * @param id ID del café a validar
     * @return ResponseEntity con mensaje de error si no existe, null si existe
     */
    private ResponseEntity<?> validateCafeExists(Integer id) {
        if (!cafeRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Café no encontrado.");
        }
        return null;
    }

    /**
     * Valida que un ID no sea nulo.
     * @param id ID a validar
     * @return ResponseEntity con mensaje de error si es nulo, null si es válido
     */
    private ResponseEntity<?> validateId(Integer id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El ID del café es obligatorio.");
        }
        return null;
    }

    /**
     * Valida que un objeto café no sea nulo.
     * @param cafe Café a validar
     * @return ResponseEntity con mensaje de error si es nulo, null si es válido
     */
    private ResponseEntity<?> validateObject(Cafe cafe) {
        if (cafe == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El café no puede ser nulo.");
        }
        return null;
    }

    /**
     * Valida todos los campos requeridos de un café.
     * @param cafe Café a validar
     * @return ResponseEntity con mensaje de error si hay campos inválidos, null si es válido
     */
    private ResponseEntity<?> validateCafeFields(Cafe cafe) {
        ResponseEntity<?> objectValidation = validateObject(cafe);
        if (objectValidation != null) {
            return objectValidation;
        }
        ResponseEntity<?> nameValidation = validateString(cafe.getNombre(), "nombre");
        if (nameValidation != null) {
            return nameValidation;
        }
        ResponseEntity<?> descriptionValidation = validateString(cafe.getDescripcion(), "descripción");
        if (descriptionValidation != null) {
            return descriptionValidation;
        }
        if (cafe.getPrecio() == null || cafe.getPrecio() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El precio debe ser mayor a 0.");
        }
        return null;
    }

    /**
     * Valida que una cadena no sea nula o vacía.
     *
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
     * Actualiza los campos de un café existente con los valores de un café actualizado.
     * Solo actualiza los campos no nulos del café actualizado.
     * @param existing Café existente a actualizar
     * @param updated Café con los nuevos valores
     * @throws IllegalArgumentException si algún campo actualizado es inválido
     */
    private void updateCafeFields(Cafe existing, Cafe updated) {
        if (updated.getNombre() != null) {
            if (updated.getNombre().trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del café no puede estar vacío.");
            }
            existing.setNombre(updated.getNombre());
        }
        if (updated.getDescripcion() != null) {
            if (updated.getDescripcion().trim().isEmpty()) {
                throw new IllegalArgumentException("La descripción del café no puede estar vacía.");
            }
            existing.setDescripcion(updated.getDescripcion());
        }
        if (updated.getPrecio() != null) {
            if (updated.getPrecio() <= 0) {
                throw new IllegalArgumentException("El precio del café debe ser mayor a 0.");
            }
            existing.setPrecio(updated.getPrecio());
        }
    }

    @Transactional(readOnly = true)
    public Page<Cafe> getAllCafes(Pageable pageable) {
        return cafeRepository.findAll(pageable);
    }

    public Optional<Cafe> getCafeById(Integer id) {
        return cafeRepository.findById(id);
    }
}
