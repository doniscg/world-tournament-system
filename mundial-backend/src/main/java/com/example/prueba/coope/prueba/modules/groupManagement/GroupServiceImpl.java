package com.example.prueba.coope.prueba.modules.groupManagement;

import com.example.prueba.coope.prueba.modules.teamManagement.TeamDTO;
import com.example.prueba.coope.prueba.modules.teamManagement.TeamEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements IGroupService {

    private final GroupRepository groupRepository;


    @Override
    public GroupDTO create(GroupDTO dto) {
        if (groupRepository.findByGroupName(dto.getGroupName()).isPresent()) {
            throw new RuntimeException("El nombre del grupo que quiere ingresar ya existe");
        }
        GroupEntity entity = new GroupEntity();

        entity.setGroupName(dto.getGroupName());
        entity.setDescription(dto.getDescription());
        groupRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<GroupDTO> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public GroupDTO findById(Long id) {
        GroupEntity entity = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        return mapToDTO(entity);
    }

    @Override
    public GroupDTO update(Long id, GroupDTO dto) {

        if (groupRepository.findByGroupName(dto.getGroupName()).isPresent()) {
            throw new RuntimeException("El nombre del grupo que quiere ingresar ya existe");
        }
        GroupEntity entity = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));


        entity.setGroupName(dto.getGroupName());
        entity.setDescription(dto.getDescription());
        groupRepository.save(entity);

        return mapToDTO(entity);
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    private GroupDTO mapToDTO(GroupEntity entity) {

        GroupDTO dto = new GroupDTO();
        dto.setId(entity.getId());
        dto.setGroupName(entity.getGroupName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

}
