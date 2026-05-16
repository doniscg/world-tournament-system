package com.example.prueba.coope.prueba.modules.teamManagement;

import com.example.prueba.coope.prueba.modules.groupManagement.GroupEntity;
import com.example.prueba.coope.prueba.modules.groupManagement.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements ITeamService {


    private final TeamRepository teamRepository;
    private final GroupRepository groupRepository;

    @Override
    public TeamDTO create(TeamDTO dto) {

        if (teamRepository.findByCountryName(dto.getCountryName()).isPresent()) {
            throw new RuntimeException("El pais que quiere ingresar ya existe");
        }

        if (teamRepository.findByFifaCode(dto.getFifaCode()).isPresent()) {
            throw new RuntimeException("el codigo FIFA que quiere ingresar ya existe");
        }

        TeamEntity entity = new TeamEntity();
        entity.setCountryName(dto.getCountryName());
        entity.setFifaCode(dto.getFifaCode());
        entity.setCoach(dto.getCoach());
        entity.setFifaRanking(dto.getFifaRanking());
        entity.setRegisteredPlayers(dto.getRegisteredPlayers());
        teamRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<TeamDTO> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public TeamDTO findById(Long id) {
        TeamEntity entity = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return mapToDTO(entity);
    }

    @Override
    public TeamDTO update(Long id, TeamDTO dto) {

        TeamEntity entity = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        if (teamRepository.findByCountryName(dto.getCountryName()).isPresent()) {
            throw new RuntimeException("El pais que quiere ingresar ya existe");
        }

        if (teamRepository.findByFifaCode(dto.getFifaCode()).isPresent()) {
            throw new RuntimeException("el codigo FIFA que quiere ingresar ya existe");
        }
        entity.setCountryName(dto.getCountryName());
        entity.setFifaCode(dto.getFifaCode());
        entity.setCoach(dto.getCoach());
        entity.setFifaRanking(dto.getFifaRanking());
        entity.setRegisteredPlayers(dto.getRegisteredPlayers());
        teamRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    private TeamDTO mapToDTO(TeamEntity entity) {
        TeamDTO dto = new TeamDTO();
        dto.setId(entity.getId());
        dto.setCountryName(entity.getCountryName());
        dto.setFifaCode(entity.getFifaCode());
        dto.setCoach(entity.getCoach());
        dto.setFifaRanking(entity.getFifaRanking());
        dto.setRegisteredPlayers(entity.getRegisteredPlayers());
        return dto;

    }
}
