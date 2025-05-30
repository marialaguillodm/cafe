package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.repository.CafeRepository;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de cafés en el sistema.
 * Proporciona métodos para realizar operaciones CRUD y consultas
 * sobre la entidad Cafe.
 * 
 * @author Maria
 * @version 1.5
 */
@Service
public class CafeService {

    @Autowired
    private CafeRepository cafeRepository;

    /**
     * Crea un nuevo café en el sistema.
     * 
     * @param cafe Café a crear
     * @return Café creado con su ID asignado
     */
    @Transactional
    public Cafe crearCafe(Cafe cafe) {
        if (cafe.getNombre() == null || cafe.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del café no puede estar vacío");
        }
        if (cafe.getDescripcion() == null || cafe.getDescripcion() .trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del café no puede estar vacío");
        }
        if (cafe.getPrecio() == null || cafe.getPrecio()== 0) {
            throw new IllegalArgumentException("El precio  del café no puede estar vacío");
        }
        return cafeRepository.save(cafe);
    }

    /**
     * Actualiza un café existente en el sistema.
     * 
     * @param cafe Café con los datos actualizados
     * @return Café actualizado
     */
    @Transactional
    public Cafe actualizarCafe(Cafe cafe) {
        if (!cafeRepository.existsById(cafe.getId())) {
            throw new IllegalArgumentException("Café no encontrado");
        }
        if (cafe.getNombre() == null || cafe.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del café no puede estar vacío");
        }
        if (cafe.getDescripcion() == null || cafe.getDescripcion() .trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del café no puede estar vacío");
        }
        if (cafe.getPrecio() == null || cafe.getPrecio()== 0) {
            throw new IllegalArgumentException("El precio  del café no puede estar vacío");
        }
        return cafeRepository.save(cafe);
    }

    /**
     * Elimina un café del sistema por su ID.
     * 
     * @param id ID del café a eliminar
     */
    @Transactional
    public void eliminarCafe(Integer id) {
        cafeRepository.deleteById(id);
    }

    /**
     * Obtiene todos los cafés registrados en el sistema.
     * 
     * @return Lista de todos los cafés
     */
    @Transactional(readOnly = true)
    public Page<Cafe> getAllCafes(Pageable pageable) {
        return cafeRepository.findAll(pageable);
    }

    /**
     * Busca un café por su ID.
     * 
     * @param id ID del café a buscar
     * @return Optional que puede contener el café si existe
     */
    public Optional<Cafe> getCafeById(Integer id) {
        return cafeRepository.findById(id);
    }
}
