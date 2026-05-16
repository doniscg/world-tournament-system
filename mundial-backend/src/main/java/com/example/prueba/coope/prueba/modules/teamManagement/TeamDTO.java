package com.example.prueba.coope.prueba.modules.teamManagement;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamDTO {

    private Long id;

    @NotBlank(message = "Nombre del pais es requerido")
    private String countryName;

    @NotBlank(message = "FIFA codigo requerido")
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "codigo FIFA debe tener unicamente 3 letras mayusculas"
    )
    private String fifaCode;

    @NotBlank(message = "Entrenador requerido")
    private String coach;

    @NotNull(message = "Ranking requerido")
    private Integer fifaRanking;

    @NotNull(message = "Registro de jugadores requerido")
    @Min(value = 23, message = "Minimo de jugadores es 23")
    @Max(value = 26, message = "Maximo de jugadores es 26")
    private Integer registeredPlayers;

}
