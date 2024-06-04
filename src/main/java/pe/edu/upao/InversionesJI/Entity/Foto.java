package pe.edu.upao.InversionesJI.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Foto")
@DiscriminatorValue("Foto")
public class Foto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoto;

    private String nombreFoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPropiedad", nullable = false)
    @JsonBackReference
    private Propiedad propiedad;
}

