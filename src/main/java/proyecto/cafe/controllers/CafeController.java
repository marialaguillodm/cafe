package proyecto.cafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.service.CafeService;

import java.util.Collection;

@RestController
@RequestMapping("/coffee")
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @GetMapping
    public Collection<Cafe> getCafes() {
        return cafeService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addCafe(@RequestBody Cafe cafe) {
        return cafeService.createCafe(cafe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        return cafeService.updateCafe(id, cafe);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        return cafeService.patchCafe(id, cafe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCafe(@PathVariable Integer id) {
        return cafeService.deleteCafe(id);
    }
}