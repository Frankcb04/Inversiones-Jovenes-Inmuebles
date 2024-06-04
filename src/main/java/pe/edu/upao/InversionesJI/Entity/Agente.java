package pe.edu.upao.InversionesJI.Entity;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Agente extends Usuario {

    private String nombreInmobiliaria;
}
