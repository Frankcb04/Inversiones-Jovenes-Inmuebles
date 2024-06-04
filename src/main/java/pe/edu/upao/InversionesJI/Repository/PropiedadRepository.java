package pe.edu.upao.InversionesJI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upao.InversionesJI.Entity.Propiedad;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
}
