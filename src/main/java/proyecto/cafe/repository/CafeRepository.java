package proyecto.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.cafe.entity.Cafe;

/**
 * Repositorio para la entidad Cafe.
 * Proporciona métodos para acceder y manipular los datos de cafés en la base de datos.
 * @author Maria
 * @version 1.0
 */
@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {
}
