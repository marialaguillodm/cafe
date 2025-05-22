package proyecto.cafe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private List<Cafe> cafes = new ArrayList<>(List.of(
            new Cafe(1, "Espresso",  "Colombia", 2.50),
            new Cafe(2, "Latte", "Brasil",3.75 ),
            new Cafe(3, "Cappuccino", "Etiopía", 3.50 ),
            new Cafe(4, "Americano", "Guatemala",2.00)
    )
    );

    @GetMapping("/cafes")
    public List<Cafe> getCafes() {
        return cafes;

    }

    @PostMapping("/cafes")
    public ResponseEntity<Cafe> addCafe(@RequestBody Cafe cafe){
        cafes.add(cafe);
        return ResponseEntity.status(201).body(cafe);
    }

    @PutMapping("/cafes/{id}")
    public ResponseEntity<?> updateCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        Optional<Cafe> existing = cafes.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (!existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Café no encontrado.");
        }

        if (cafe.getNombre() == null || cafe.getOrigen() == null || cafe.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios y el precio debe ser mayor a 0.");
        }

        Cafe found = existing.get();
        found.setNombre(cafe.getNombre());
        found.setOrigen(cafe.getOrigen());
        found.setPrecio(cafe.getPrecio());

        return ResponseEntity.ok(found);
    }

    @PatchMapping("/cafes/{id}")
    public ResponseEntity<?> patchCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        Optional<Cafe> existing = cafes.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (!existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Café no encontrado.");
        }
        Cafe found = existing.get();
        if (cafe.getNombre() != null) found.setNombre(cafe.getNombre());
        if (cafe.getOrigen() != null) found.setOrigen(cafe.getOrigen());
        if (cafe.getPrecio() > 0) found.setPrecio(cafe.getPrecio());

        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/cafes/{id}")
    public ResponseEntity<?> deleteCafe(@PathVariable Integer id) {
        Optional<Cafe> existing = cafes.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (!existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Café no encontrado.");
        }
        cafes.remove(existing.get());
        return ResponseEntity.ok("Café eliminado exitosamente.");
    }
}
