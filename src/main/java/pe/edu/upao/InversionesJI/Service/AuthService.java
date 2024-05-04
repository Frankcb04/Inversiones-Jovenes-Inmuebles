package pe.edu.upao.InversionesJI.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upao.InversionesJI.Entity.Agente;
import pe.edu.upao.InversionesJI.Entity.Inmobiliaria;
import pe.edu.upao.InversionesJI.Jwt.JwtService;
import pe.edu.upao.InversionesJI.Repository.AgenteRepository;
import pe.edu.upao.InversionesJI.Repository.ClienteRepository;
import pe.edu.upao.InversionesJI.Repository.InmobiliariaRepository;
import pe.edu.upao.InversionesJI.Request.LoginRequest;
import pe.edu.upao.InversionesJI.Request.RegisterClienteRequest;
import pe.edu.upao.InversionesJI.Response.AuthResponse;
import pe.edu.upao.InversionesJI.Entity.Cliente;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final AgenteRepository agenteRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final InmobiliariaRepository inmobiliariaRepository;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));

        // Buscar el usuario como Cliente
        Optional<Cliente> clienteOptional = clienteRepository.findByUsername(request.getCorreo());
        if(clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            String token = jwtService.getToken(cliente);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }

        // Si no es Cliente, buscar como Agente
        Optional<Agente> agenteOptional = agenteRepository.findByUsername(request.getCorreo());
        if(agenteOptional.isPresent()) {
            Agente agente = agenteOptional.get();
            String token = jwtService.getToken(agente);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }

        // Si no es Agente, buscar como Inmobiliaria
        Optional<Inmobiliaria> inmobiliariaOptional = inmobiliariaRepository.findByUsername(request.getCorreo());
        if(inmobiliariaOptional.isPresent()) {
            Inmobiliaria inmobiliaria = inmobiliariaOptional.get();
            String token = jwtService.getToken(inmobiliaria);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }

        throw new UsernameNotFoundException("Usuario no encontrado con username: " + request.getCorreo());
    }

    public AuthResponse registerCliente(RegisterClienteRequest request){
        Cliente cliente = new Cliente();
        cliente.setUsername(request.getCorreo());
        cliente.setPassword(passwordEncoder.encode(request.getContrasena()));
        cliente.setRole("Cliente");
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        clienteRepository.save(cliente);

        return AuthResponse.builder()
                .token(jwtService.getToken(cliente))
                .build();
    }
}
