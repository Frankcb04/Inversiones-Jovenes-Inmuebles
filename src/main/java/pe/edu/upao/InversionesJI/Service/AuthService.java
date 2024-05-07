package pe.edu.upao.InversionesJI.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final AgenteRepository agenteRepository;
    private final InmobiliariaRepository inmobiliariaRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        System.out.println("Intento de inicio de sesión para el usuario: " + request.getCorreo());
        UserDetails userDetails = loadUserByUsername(request.getCorreo());

        if (userDetails != null && passwordEncoder.matches(request.getContrasena(), userDetails.getPassword())) {
            System.out.println("Autenticación exitosa para el usuario: " + request.getCorreo());

            // Generar token y devolver respuesta de autenticación
            String token = jwtService.getToken(userDetails);

            return new AuthResponse(token);
        } else {
            throw new BadCredentialsException("Credenciales incorrectas para el usuario: " + request.getCorreo());
        }
    }

    public UserDetails loadUserByUsername(String correo) {
        Optional<Cliente> clienteOptional = clienteRepository.findByUsername(correo);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return new User(cliente.getUsername(), cliente.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("Cliente")));
        }

        Optional<Agente> agenteOptional = agenteRepository.findByUsername(correo);
        if (agenteOptional.isPresent()) {
            Agente agente = agenteOptional.get();
            return new User(agente.getUsername(), agente.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("Agente")));
        }

        Optional<Inmobiliaria> inmobiliariaOptional = inmobiliariaRepository.findByUsername(correo);
        if (inmobiliariaOptional.isPresent()) {
            Inmobiliaria inmobiliaria = inmobiliariaOptional.get();
            return new User(inmobiliaria.getUsername(), inmobiliaria.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("Inmobiliaria")));
        }
        throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
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
