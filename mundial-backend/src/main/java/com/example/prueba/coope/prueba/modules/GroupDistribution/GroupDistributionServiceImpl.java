package com.example.prueba.coope.prueba.modules.GroupDistribution;

import com.example.prueba.coope.prueba.modules.groupManagement.GroupDTO;
import com.example.prueba.coope.prueba.modules.groupManagement.GroupEntity;
import com.example.prueba.coope.prueba.modules.groupManagement.GroupRepository;
import com.example.prueba.coope.prueba.modules.teamManagement.TeamDTO;
import com.example.prueba.coope.prueba.modules.teamManagement.TeamEntity;
import com.example.prueba.coope.prueba.modules.teamManagement.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupDistributionServiceImpl implements GroupDistributionService {

    private final TeamRepository teamRepository;
    private final GroupRepository groupRepository;
    private final GroupDistributionRepository distributionRepository;
    private final GroupDistributionDetailRepository detailRepository;

    @Override
    public List<GroupAssignmentDTO> previewDistribution(int numberOfGroups) {
        validateBasic(numberOfGroups);

        List<TeamEntity> teams = teamRepository.findAll();
        List<GroupEntity> groups = groupRepository.findAll();

        Collections.shuffle(teams);
        Collections.shuffle(groups);

        List<GroupEntity> selectedGroups = groups.stream().limit(numberOfGroups).collect(Collectors.toList());

        int teamsPerGroup = teams.size() / numberOfGroups;

        List<GroupAssignmentDTO> result = new ArrayList<>();

        for (int i = 0; i < numberOfGroups; i++) {
            GroupEntity g = selectedGroups.get(i);
            int start = i * teamsPerGroup;
            int end = start + teamsPerGroup;
            List<TeamDTO> assigned = teams.subList(start, end).stream().map(this::mapToDTO).collect(Collectors.toList());
            GroupDTO gdto = new GroupDTO(g.getId(), g.getGroupName(), g.getDescription());
            result.add(new GroupAssignmentDTO(gdto, assigned));
        }

        return result;
    }

    @Override
    public Long createDistribution(int numberOfGroups) {
        // previewValidation uses same checks
        validateBasic(numberOfGroups);

        List<GroupAssignmentDTO> assignments = previewDistribution(numberOfGroups);

        GroupDistributionEntity distribution = new GroupDistributionEntity();
        distribution.setTotalGroups(numberOfGroups);
        distribution.setCreatedAt(LocalDateTime.now());
        distribution = distributionRepository.save(distribution);

        List<GroupDistributionDetailEntity> details = new ArrayList<>();

        for (GroupAssignmentDTO assign : assignments) {
            // fetch group entity by id
            Optional<GroupEntity> groupOpt = groupRepository.findById(assign.getGroup().getId());
            if (groupOpt.isEmpty()) continue; // should not happen
            GroupEntity group = groupOpt.get();
            for (TeamDTO t : assign.getTeams()) {
                Optional<TeamEntity> teamOpt = teamRepository.findById(t.getId());
                if (teamOpt.isEmpty()) continue;
                TeamEntity team = teamOpt.get();
                GroupDistributionDetailEntity detail = new GroupDistributionDetailEntity();
                detail.setDistribution(distribution);
                detail.setGroup(group);
                detail.setTeam(team);
                details.add(detail);
            }
        }

        detailRepository.saveAll(details);

        return distribution.getId();
    }

    @Override
    public List<GroupAssignmentDTO> getDistribution(Long distributionId) {
        List<GroupDistributionDetailEntity> details = detailRepository.findByDistributionId(distributionId);
        if (details.isEmpty()) return Collections.emptyList();

        Map<Long, GroupAssignmentDTO> map = new LinkedHashMap<>();

        for (GroupDistributionDetailEntity d : details) {
            Long groupId = d.getGroup().getId();
            GroupAssignmentDTO gadto = map.get(groupId);
            if (gadto == null) {
                GroupDTO gdto = new GroupDTO(d.getGroup().getId(), d.getGroup().getGroupName(), d.getGroup().getDescription());
                gadto = new GroupAssignmentDTO(gdto, new ArrayList<>());
                map.put(groupId, gadto);
            }
            gadto.getTeams().add(mapToDTO(d.getTeam()));
        }

        return new ArrayList<>(map.values());
    }

    @Override
    public List<DistributionResponseDTO> findAllDistributions() {
        List<GroupDistributionEntity> distributions =

                distributionRepository.findAll();

        return distributions.stream()

                .map(this::mapDistribution)
                .toList();
    }

    private void validateBasic(int numberOfGroups) {
        if (numberOfGroups <= 1) {
            throw new RuntimeException("No se permite formar un solo grupo con todos los equipos");
        }

        long totalTeams = teamRepository.count();
        long totalGroups = groupRepository.count();

        if (numberOfGroups > totalGroups) {
            throw new RuntimeException("No existen suficientes grupos registrados para realizar la asignacion");
        }

        if (totalTeams == 0) {
            throw new RuntimeException("No existen equipos registrados");
        }

        if (totalTeams % numberOfGroups != 0) {
            throw new RuntimeException("La cantidad de equipos registrados no puede dividirse exactamente entre la cantidad de grupos solicitados");
        }
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

    private DistributionResponseDTO mapDistribution(
            GroupDistributionEntity distribution
    ) {

        Map<GroupEntity, List<TeamDTO>> grouped =
                distribution.getDetails()
                        .stream()
                        .collect(Collectors.groupingBy(
                                GroupDistributionDetailEntity::getGroup,
                                Collectors.mapping(
                                        detail -> mapToDTO(detail.getTeam()),
                                        Collectors.toList()
                                )
                        ));

        List<GroupAssignmentDTO> assignments =
                grouped.entrySet()
                        .stream()
                        .map(entry -> {

                            GroupEntity group = entry.getKey();

                            GroupDTO groupDTO = new GroupDTO();

                            groupDTO.setId(group.getId());
                            groupDTO.setGroupName(group.getGroupName());
                            groupDTO.setDescription(group.getDescription());

                            return new GroupAssignmentDTO(
                                    groupDTO,
                                    entry.getValue()
                            );

                        }).toList();

        DistributionResponseDTO dto =
                new DistributionResponseDTO();

        dto.setDistributionId(distribution.getId());
        dto.setTotalGroups(distribution.getTotalGroups());
        dto.setCreatedAt(distribution.getCreatedAt());
        dto.setGroups(assignments);

        return dto;
    }
}

