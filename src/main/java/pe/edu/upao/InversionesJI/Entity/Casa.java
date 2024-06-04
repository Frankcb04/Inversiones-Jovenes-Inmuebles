package pe.edu.upao.InversionesJI.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Casa")
@DiscriminatorValue("Casa")
public class Casa extends Propiedad{

    private int cantPisos;
    private int areaJardin;
    private boolean jardin;
    private boolean atico;
    private boolean sotano;
}
