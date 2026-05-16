package com.example.prueba.coope.prueba.modules.groupManagement;

import java.util.List;

public interface IGroupService {

    GroupDTO create(GroupDTO dto);

    List<GroupDTO> findAll();

    GroupDTO findById(Long id);

    GroupDTO update(Long id, GroupDTO dto);

    void delete(Long id);
}
