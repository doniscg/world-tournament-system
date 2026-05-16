package com.example.prueba.coope.prueba.modules.GroupDistribution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupDistributionRepository extends JpaRepository<GroupDistributionEntity, Long> {

}

