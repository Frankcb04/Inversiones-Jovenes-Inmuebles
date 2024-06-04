package pe.edu.upao.InversionesJI.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CasaDto extends PropiedadDto{

    @NotNull
    @NotBlank(message = "La cantidad de pisos no debe estar en blanco")
    private int cantPisos;

    @NotNull
    @NotBlank(message = "El área del jardín no debe estar en blanco")
    private int areaJardin;

    @NotNull
    private boolean jardin;

    @NotNull
    private boolean atico;

    @NotNull
    private boolean sotano;

    private List<FotoDto> fotos;
}
