package pe.edu.upao.InversionesJI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upao.InversionesJI.Entity.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
}
