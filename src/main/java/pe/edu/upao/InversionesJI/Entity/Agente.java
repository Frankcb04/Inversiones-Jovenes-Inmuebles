package pe.edu.upao.InversionesJI.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Agente extends Usuario {

    private Date horarioAtencion;
}
