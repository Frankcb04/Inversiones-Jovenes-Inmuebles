package pe.edu.upao.InversionesJI.InmobiliariaServiceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upao.InversionesJI.Jwt.JwtService;
import pe.edu.upao.InversionesJI.Repository.AgenteRepository;
import pe.edu.upao.InversionesJI.Repository.InmobiliariaRepository;
import pe.edu.upao.InversionesJI.Request.RegisterAgenteRequest;
import pe.edu.upao.InversionesJI.Request.RegisterInmobiliariaRequest;
import pe.edu.upao.InversionesJI.Response.AuthResponse;
import pe.edu.upao.InversionesJI.Service.InmobiliariaService;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InmobiliariaServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AgenteRepository agenteRepository;

    @Mock
    private InmobiliariaRepository inmobiliariaRepository;

    @InjectMocks
    private InmobiliariaService inmobiliariaService;

    @Test
    public void testAgregarAgente() {
        RegisterAgenteRequest request = new RegisterAgenteRequest();
        request.setNombre("Mi");
        request.setApellido("Palomita");
        request.setCorreo("pruebita123@gmail.com");
        request.setContrasena("012345");
        request.setTelefono(929102837);
        request.setDni(73940195);
        request.setHorarioAtencion(new Date(2022-10-12));

        when(passwordEncoder.encode(anyString())).thenReturn("contraseñaEncriptada");
        when(jwtService.getToken(any())).thenReturn("token");

        AuthResponse response = inmobiliariaService.agregarAgente(request);

        assertNotNull(response);
        assertEquals("token", response.getToken());
    }

    @Test
    public void testAgregarInmobiliaria() {
        RegisterInmobiliariaRequest request = new RegisterInmobiliariaRequest();
        request.setNombreInmobiliaria("Palomita Enterprise");
        request.setCorreo("palomita123@gmail.com");
        request.setContrasena("123456");
        request.setDireccion("Las palomas 212");
        request.setTelefonoContacto(928301637);
        request.setRuc(298417270);

        when(passwordEncoder.encode(anyString())).thenReturn("contraseñaEncriptada");
        when(jwtService.getToken(any())).thenReturn("token");

        AuthResponse response = inmobiliariaService.agregarInmobiliaria(request);

        assertNotNull(response);
        assertEquals("token", response.getToken());
    }
}

