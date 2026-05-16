package com.example.prueba.coope.prueba.modules.GroupDistribution;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GroupDistributionController {

    private final GroupDistributionService distributionService;

    @GetMapping("/preview")
    public ResponseEntity<List<GroupAssignmentDTO>> preview(@RequestParam("groups") int groups) {
        List<GroupAssignmentDTO> preview = distributionService.previewDistribution(groups);
        return ResponseEntity.ok(preview);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestParam("groups") int groups) {
        Long id = distributionService.createDistribution(groups);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GroupAssignmentDTO>> get(@PathVariable("id") Long id) {
        List<GroupAssignmentDTO> result = distributionService.getDistribution(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<DistributionResponseDTO>>
    findAllDistributions() {
        return ResponseEntity.ok(
                distributionService.findAllDistributions()
        );
    }
}

