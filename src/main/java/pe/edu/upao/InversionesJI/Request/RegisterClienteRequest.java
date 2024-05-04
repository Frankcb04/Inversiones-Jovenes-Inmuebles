package pe.edu.upao.InversionesJI.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClienteRequest {
    String nombre;
    String apellido;
    String correo;
    String contrasena;
    int telefono;
    int dni;
}