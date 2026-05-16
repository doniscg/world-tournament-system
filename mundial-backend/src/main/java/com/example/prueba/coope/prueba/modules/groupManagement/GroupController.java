package com.example.prueba.coope.prueba.modules.groupManagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GroupController {

    private final IGroupService groupService;

    @PostMapping
    public GroupDTO create(
            @Valid @RequestBody GroupDTO dto
    ) {
        return groupService.create(dto);
    }

    @GetMapping
    public List<GroupDTO> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public GroupDTO findById(
            @PathVariable Long id
    ) {
        return groupService.findById(id);
    }

    @PutMapping("/{id}")
    public GroupDTO update(
            @PathVariable Long id,
            @Valid @RequestBody GroupDTO dto
    ) {
        return groupService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        groupService.delete(id);
    }
}
