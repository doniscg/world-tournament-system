package com.example.prueba.coope.prueba.modules.teamManagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeamController {

    private final ITeamService teamService;

    @PostMapping
    public TeamDTO create(@Valid @RequestBody TeamDTO dto) {
        return teamService.create(dto);
    }

    @GetMapping
    public List<TeamDTO> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public TeamDTO findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PutMapping("/{id}")
    public TeamDTO update(
            @PathVariable Long id,
            @Valid @RequestBody TeamDTO dto
    ) {
        return teamService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
