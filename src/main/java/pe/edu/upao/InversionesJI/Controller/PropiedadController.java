package pe.edu.upao.InversionesJI.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.InversionesJI.Entity.Propiedad;
import pe.edu.upao.InversionesJI.Service.PropiedadService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/propiedad")
public class PropiedadController {

    private final PropiedadService propiedadService;

    @GetMapping("/listarPropiedades")
    public ResponseEntity<List<Propiedad>> listarPropiedades() {
        List<Propiedad> propiedades = propiedadService.listarPropiedades();
        return ResponseEntity.ok(propiedades);
    }
}
