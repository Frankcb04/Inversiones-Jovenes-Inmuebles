package pe.edu.upao.InversionesJI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upao.InversionesJI.Entity.Inmobiliaria;

import java.util.Optional;

public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria, Long> {
    Optional<Inmobiliaria> findByUsername(String correo);

}
