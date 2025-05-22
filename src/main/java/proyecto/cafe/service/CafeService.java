package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.repository.CafeRepository;

import java.util.Collection;

@Service
public class CafeService {

    @Autowired
    private CafeRepository cafeRepository;

    public Collection<Cafe> getAll() {
        return cafeRepository.findAll();
    }

    public ResponseEntity<?> createCafe(Cafe cafe) {
        if (cafe.getNombre() == null || cafe.getOrigen() == null || cafe.getPrecio() == null || cafe.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios y el precio debe ser mayor a 0.");
        }
        return ResponseEntity.status(201).body(cafeRepository.save(cafe));
    }

    public ResponseEntity<?> updateCafe(Integer id, Cafe cafe) {
        Cafe existing = cafeRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(404).body("Café no encontrado.");
        }
        if (cafe.getNombre() == null || cafe.getOrigen() == null || cafe.getPrecio() == null || cafe.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios y el precio debe ser mayor a 0.");
        }
        cafe.setId(id);
        return ResponseEntity.ok(cafeRepository.save(cafe));
    }

    public ResponseEntity<?> patchCafe(Integer id, Cafe cafe) {
        Cafe existing = cafeRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(404).body("Café no encontrado.");
        }
        if (cafe.getNombre() != null) existing.setNombre(cafe.getNombre());
        if (cafe.getOrigen() != null) existing.setOrigen(cafe.getOrigen());
        if (cafe.getPrecio() != null && cafe.getPrecio() > 0) existing.setPrecio(cafe.getPrecio());
        return ResponseEntity.ok(existing);
    }

    public ResponseEntity<?> deleteCafe(Integer id) {
        Cafe existing = cafeRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(404).body("Café no encontrado.");
        }
        cafeRepository.delete(id);
        return ResponseEntity.ok("Café eliminado exitosamente.");
    }
}
