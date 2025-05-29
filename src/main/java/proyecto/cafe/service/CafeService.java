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
import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio relacionada con los cafés.
 * Proporciona operaciones CRUD y validaciones para la entidad Cafe.
 * @author Maria
 * @version 1.0
 */
@Service
public class CafeService {

    @Autowired
    private CafeRepository cafeRepository;

    /**
     * Crea un nuevo café.
     * Valida los campos requeridos y permite que la base de datos genere el ID.
     * @param cafe El café a crear
     * @return ResponseEntity con el café creado o mensaje de error
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
     * Actualiza completamente un café existente.
     * @param cafe Nuevos datos del café
     * @return ResponseEntity con el café actualizado o mensaje de error
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

    @Transactional
    public void eliminarCafe(Integer id) {
        cafeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Cafe> getAllCafes(Pageable pageable) {
        return cafeRepository.findAll(pageable);
    }

    public Optional<Cafe> getCafeById(Integer id) {
        return cafeRepository.findById(id);
    }
}
