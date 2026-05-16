package com.example.prueba.coope.prueba.modules.GroupDistribution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistributionResponseDTO {

    private Long distributionId;

    private Integer totalGroups;

    private LocalDateTime createdAt;

    private List<GroupAssignmentDTO> groups;
}
