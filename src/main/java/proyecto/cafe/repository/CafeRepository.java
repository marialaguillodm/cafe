package proyecto.cafe.repository;

import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Cafe;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio que maneja el almacenamiento y recuperación de cafés.
 * Implementa un almacenamiento en memoria usando un Map.
 * @author Maria
 * @version 1.0
 */
@Repository
public class CafeRepository {
    private final Map<Integer, Cafe> cafes = new HashMap<>();

    /**
     * Constructor que inicializa el repositorio con datos de ejemplo.
     * Crea cuatro cafés predefinidos con diferentes características.
     */
    public CafeRepository() {
        cafes.put(1, new Cafe(1, "Espresso", "Arabia", 3.50));
        cafes.put(2, new Cafe(2, "Latte", "Brasil", 3.55));
        cafes.put(3, new Cafe(3, "Cappuccino", "Etiopía", 6.50));
        cafes.put(4, new Cafe(4, "Americano", "Guatemala", 1.00));
    }

    /**
     * Obtiene todos los cafés almacenados.
     * @return Colección con todos los cafés
     */
    public Collection<Cafe> findAll() {
        return cafes.values();
    }

    /**
     * Busca un café por su ID.
     * @param id ID del café a buscar
     * @return Optional que contiene el café si existe, o vacío si no existe
     */
    public Optional<Cafe> findById(Integer id) {
        return Optional.ofNullable(cafes.get(id));
    }

    /**
     * Guarda un café en el repositorio.
     * Si el café ya existe, actualiza sus datos.
     * @param cafe Café a guardar
     * @return El café guardado
     */
    public Cafe save(Cafe cafe) {
        cafes.put(cafe.getId(), cafe);
        return cafe;
    }

    /**
     * Elimina un café del repositorio.
     * @param id ID del café a eliminar
     */
    public void delete(Integer id) {
        cafes.remove(id);
    }

    /**
     * Verifica si existe un café con el ID especificado.
     * @param id ID del café a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existsById(Integer id) {
        return cafes.containsKey(id);
    }
}
