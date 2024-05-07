package pe.edu.upao.InversionesJI.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Articulo")
public class Articulo {

    @Id
    @GeneratedValue
    private Long idArticulo;
    private String titulo;
    private String imagen;
    private String video;
    private String contenido;
    private Date fechaPublicacion;
    private String tipoArticulo;

    @ManyToOne
    @JoinColumn(name = "idInmobiliaria", nullable = false)
    private Inmobiliaria inmobiliaria;
}
