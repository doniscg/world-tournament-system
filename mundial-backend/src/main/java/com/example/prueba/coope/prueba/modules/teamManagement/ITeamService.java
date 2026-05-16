package com.example.prueba.coope.prueba.modules.teamManagement;

import java.util.List;

public interface ITeamService {

    TeamDTO create(TeamDTO dto);

    List<TeamDTO> findAll();

    TeamDTO findById(Long id);

    TeamDTO update(Long id, TeamDTO dto);

    void delete(Long id);
}
