package pe.edu.upao.InversionesJI.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upao.InversionesJI.Dto.ArticuloDto;
import pe.edu.upao.InversionesJI.Entity.Agente;
import pe.edu.upao.InversionesJI.Entity.Articulo;
import pe.edu.upao.InversionesJI.Entity.Inmobiliaria;
import pe.edu.upao.InversionesJI.Jwt.JwtService;
import pe.edu.upao.InversionesJI.Repository.AgenteRepository;
import pe.edu.upao.InversionesJI.Repository.ArticuloRepository;
import pe.edu.upao.InversionesJI.Repository.InmobiliariaRepository;
import pe.edu.upao.InversionesJI.Request.RegisterAgenteRequest;
import pe.edu.upao.InversionesJI.Request.RegisterInmobiliariaRequest;
import pe.edu.upao.InversionesJI.Response.AuthResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InmobiliariaService {

    private final InmobiliariaRepository inmobiliariaRepository;
    private final AgenteRepository agenteRepository;
    private final ArticuloRepository articuloRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse agregarAgente(RegisterAgenteRequest request) {
        System.out.println("Solicitud para agregar agentes recibida: " + request);
        Agente agente = new Agente();
        agente.setNombre(request.getNombre());
        agente.setApellido(request.getApellido());
        agente.setRole("Agente");
        agente.setUsername(request.getCorreo());
        agente.setPassword(passwordEncoder.encode(request.getContrasena()));
        agente.setDni(request.getDni());
        agente.setTelefono(request.getTelefono());
        agente.setHorarioAtencion(request.getHorarioAtencion());
        agenteRepository.save(agente);

        return AuthResponse.builder()
                .token(jwtService.getToken(agente))
                .build();

    }

    public AuthResponse agregarInmobiliaria(RegisterInmobiliariaRequest request) {
        System.out.println("Solicitud para agregar inmobiliaria recibida: " + request);
        Inmobiliaria inmobiliaria = new Inmobiliaria();
        inmobiliaria.setNombreInmobiliaria(request.getNombreInmobiliaria());
        inmobiliaria.setRole("Inmobiliaria");
        inmobiliaria.setUsername(request.getCorreo());
        inmobiliaria.setPassword(passwordEncoder.encode(request.getContrasena()));
        inmobiliaria.setRuc(request.getRuc());
        inmobiliaria.setTelefonoContacto(request.getTelefonoContacto());
        inmobiliaria.setDireccion(request.getDireccion());
        inmobiliariaRepository.save(inmobiliaria);

        return AuthResponse.builder()
                .token(jwtService.getToken(inmobiliaria))
                .build();

    }

    public ResponseEntity<?> eliminarAgente(Long id) {
        Optional<Agente> agenteOptional = agenteRepository.findById(id);
        if (agenteOptional.isPresent()) {
            Agente agente = agenteOptional.get();
            agenteRepository.delete(agente);
            return ResponseEntity.status(HttpStatus.OK).body("Agente eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El agente no se ha encontrado");
        }
    }

    public Articulo agregarArticulo(Long idInmobiliaria, ArticuloDto articuloDto) {
        Inmobiliaria inmobiliaria = findById(idInmobiliaria);
        if (inmobiliaria == null) {
            throw new IllegalArgumentException("Inmobiliaria no encontrada");
        }

        Articulo articulo = new Articulo();
        articulo.setTitulo(articuloDto.getTitulo());
        articulo.setImagen(articuloDto.getImagen());
        articulo.setVideo(articuloDto.getVideo());
        articulo.setContenido(articuloDto.getContenido());
        articulo.setFechaPublicacion(articuloDto.getFechaPublicacion());
        articulo.setTipoArticulo(articuloDto.getTipoArticulo());
        articulo.setInmobiliaria(inmobiliaria);
        inmobiliaria.getArticulos().add(articulo);
        inmobiliariaRepository.save(inmobiliaria);
        articulo = articuloRepository.save(articulo);
        return articulo;
    }

    public Inmobiliaria findById(Long id) {
        return inmobiliariaRepository.findById(id).orElse(null);
    }
}