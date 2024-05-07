package pe.edu.upao.InversionesJI.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAgenteRequest {
    String nombre;
    String apellido;
    String correo;
    String contrasena;
    int telefono;
    int dni;
    Date horarioAtencion;
}