package pe.edu.upao.InversionesJI.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Propiedad")
@DiscriminatorValue("Propiedad")
@Inheritance(strategy = InheritanceType.JOINED)
public class Propiedad {
    @Id
    @GeneratedValue
    private Long idPropiedad;

    //Dirección de las propiedades
    private String latitud;
    private String longitud;
    private String pais;
    private String region;
    private String provincia;
    private String distrito;
    private String direccion;

    //Descripción de las propiedades
    private String descripcion;
    private String otrasComodidades;
    private String tipoPropiedad;
    private double areaTerreno;
    private double costoTotal;
    private double costoInicial;
    private boolean cochera;
    private int cantBanos;
    private int cantDormitorios;
    private int cantCochera;

    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Foto> fotos;
}
