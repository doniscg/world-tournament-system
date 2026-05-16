package com.example.prueba.coope.prueba.modules.teamManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    Optional<TeamEntity> findByCountryName(String countryName);

    Optional<TeamEntity> findByFifaCode(String fifaCode);

}
