package pe.edu.upao.InversionesJI.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.InversionesJI.Dto.ArticuloDto;
import pe.edu.upao.InversionesJI.Entity.Articulo;
import pe.edu.upao.InversionesJI.Request.RegisterAgenteRequest;
import pe.edu.upao.InversionesJI.Request.RegisterInmobiliariaRequest;
import pe.edu.upao.InversionesJI.Service.InmobiliariaService;

@RestController
@RequestMapping("/inmobiliaria")
@RequiredArgsConstructor
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;

    @PostMapping(value = "/registrarInmobiliaria")
    public ResponseEntity<?> agregarInmobiliaria(@RequestBody RegisterInmobiliariaRequest request) {
        // Log para verificar si la solicitud llega al controlador
        System.out.println("Solicitud para registrar inmobiliaria recibida.");

        // Código para registrar la inmobiliaria
        return ResponseEntity.ok(inmobiliariaService.agregarInmobiliaria(request));
    }

    @PostMapping(value = "/registrarAgente")
    public ResponseEntity<?> agregarAgente(@RequestBody RegisterAgenteRequest request) {
        return ResponseEntity.ok(inmobiliariaService.agregarAgente(request));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarAgente(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inmobiliariaService.eliminarAgente(id));
    }

    @PostMapping("/articulos/{id}")
    public ResponseEntity<Articulo> crearArticulo(@PathVariable("idInmobiliaria") Long idInmobiliaria, @RequestBody ArticuloDto articuloDto) {
        Articulo articulo = inmobiliariaService.agregarArticulo(idInmobiliaria, articuloDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(articulo);
    }
}
