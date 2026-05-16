package com.example.prueba.coope.prueba.modules.GroupDistribution;

import java.util.List;

public interface GroupDistributionService {

    List<GroupAssignmentDTO> previewDistribution(int numberOfGroups);

    Long createDistribution(int numberOfGroups);

    List<GroupAssignmentDTO> getDistribution(Long distributionId);

    List<DistributionResponseDTO> findAllDistributions();

}

