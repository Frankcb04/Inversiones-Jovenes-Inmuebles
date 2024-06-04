package pe.edu.upao.InversionesJI.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upao.InversionesJI.Entity.Propiedad;
import pe.edu.upao.InversionesJI.Repository.PropiedadRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;

    public List<Propiedad> listarPropiedades() {
        return propiedadRepository.findAll();
    }
}
