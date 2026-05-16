package com.example.prueba.coope.prueba.modules.groupManagement;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDTO {

    private Long id;

    @NotBlank(message = "Nombre del grupo es requerido")
    private String groupName;

    @NotBlank(message = "Descripcion del grupo es requerida")
    private String description;
}
