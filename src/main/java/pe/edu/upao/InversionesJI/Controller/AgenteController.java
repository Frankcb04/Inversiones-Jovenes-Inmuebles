package pe.edu.upao.InversionesJI.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.InversionesJI.Dto.CasaDto;
import pe.edu.upao.InversionesJI.Dto.DepartamentoDto;
import pe.edu.upao.InversionesJI.Service.AgenteService;

@RestController
@RequestMapping("/agente")
@AllArgsConstructor
public class AgenteController {

    private final AgenteService agenteService;

    //CASA

    //Agregar una casa
    @PostMapping("/agregarCasa")
    public ResponseEntity<String> agregarCasa(@RequestBody CasaDto casaDto) {
        try {
            agenteService.agregarCasa(casaDto);
            return ResponseEntity.ok("Casa agregada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Modificar datos de la casa
    @PutMapping("/modificarCasa/{id}")
    public ResponseEntity<Void> modificarCasa(@PathVariable Long id, @RequestBody CasaDto casaDto) {
        agenteService.modificarCasa(id, casaDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //Elimianr una casa
    @DeleteMapping("/eliminarCasa/{id}")
    public ResponseEntity<String> eliminarCasa(@PathVariable Long id) {
        try {
            agenteService.eliminarCasa(id);
            return ResponseEntity.ok("Casa eliminada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DEPARTAMENTO

    //Agregar un departamento
    @PostMapping("/agregarDepartamento")
    public ResponseEntity<String> agregarDepartamento(@RequestBody DepartamentoDto departamentoDto) {
        try {
            agenteService.agregarDepartamento(departamentoDto);
            return ResponseEntity.ok("Departamento agregado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Modificar Departamento
    @PutMapping("/modificarDepartamento/{id}")
    public ResponseEntity<Void> modificarDepartamento(@PathVariable Long id, @RequestBody DepartamentoDto departamentoDto) {
        agenteService.modificarDepartamento(id, departamentoDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Eliminar los datos del departamento
    @DeleteMapping("/eliminarDepartamento/{id}")
    public ResponseEntity<String> eliminarDepartamento(@PathVariable Long id) {
        try {
            agenteService.eliminarDepartamento(id);
            return ResponseEntity.ok("Departamento eliminado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
