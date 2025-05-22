package proyecto.cafe.repository;

import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Cafe;

import java.util.*;

@Repository
public class CafeRepository {
    private final Map<Integer, Cafe> cafes = new HashMap<>();

    public CafeRepository() {
        cafes.put(1, new Cafe(1, "Espresso", "Colombia", 2.50));
        cafes.put(2, new Cafe(2, "Latte", "Brasil", 3.75));
        cafes.put(3, new Cafe(3, "Cappuccino", "Etiopía", 3.50));
        cafes.put(4, new Cafe(4, "Americano", "Guatemala", 2.00));
    }

    public Collection<Cafe> findAll() {
        return cafes.values();
    }

    public Optional<Cafe> findById(Integer id) {
        return Optional.ofNullable(cafes.get(id));
    }

    public Cafe save(Cafe cafe) {
        cafes.put(cafe.getId(), cafe);
        return cafe;
    }

    public void delete(Integer id) {
        cafes.remove(id);
    }

    public boolean existsById(Integer id) {
        return cafes.containsKey(id);
    }
}
