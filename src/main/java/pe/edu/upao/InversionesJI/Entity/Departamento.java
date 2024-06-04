package pe.edu.upao.InversionesJI.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Departamento")
@DiscriminatorValue("Departamento")
public class Departamento extends Propiedad{

    private int pisos;
    private int interior;
    private boolean ascensor;
    private boolean areasComunes;
    private String areasComunesEspecificas;
}
