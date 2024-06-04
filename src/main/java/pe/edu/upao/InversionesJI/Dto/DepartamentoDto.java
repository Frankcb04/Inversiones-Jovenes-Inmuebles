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
public class DepartamentoDto extends PropiedadDto{

    @NotNull
    @NotBlank(message = "La cantidad de pisos no debe estar en blanco")
    private int pisos;

    @NotNull
    @NotBlank(message = "La cantidad de interiores no debe estar en blanco")
    private int interior;

    @NotNull
    private boolean ascensor;

    @NotNull
    private boolean areasComunes;

    @NotNull
    @NotBlank(message = "Las áreas comunes no debe estar en blanco")
    private String areasComunesEspecificas;

    private List<FotoDto> fotos;
}
