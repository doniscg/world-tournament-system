package com.example.prueba.coope.prueba.modules.GroupDistribution;

import com.example.prueba.coope.prueba.modules.teamManagement.TeamDTO;
import com.example.prueba.coope.prueba.modules.groupManagement.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupAssignmentDTO {
    private GroupDTO group;
    private List<TeamDTO> teams;
}

