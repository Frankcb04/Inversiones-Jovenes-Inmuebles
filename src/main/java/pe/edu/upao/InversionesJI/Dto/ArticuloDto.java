package pe.edu.upao.InversionesJI.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ArticuloDto {

    private Long idArticulo;

    @NotNull
    @NotBlank(message = "El título no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String titulo;

    @NotNull
    @NotBlank(message = "La imagen no debe estar en blanco")
    @Size(min = 5, max = 50)
    private String imagen;

    @NotNull
    @NotBlank(message = "El video no debe estar en blanco")
    @Size(min = 5, max = 50)
    private String video;

    @NotNull
    @NotBlank(message = "El contenido no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String contenido;

    @NotNull
    @NotBlank(message = "La fecha de publicación no debe estar en blanco")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;

    @NotNull
    @NotBlank(message = "El tipo de artículo no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String tipoArticulo;
}
