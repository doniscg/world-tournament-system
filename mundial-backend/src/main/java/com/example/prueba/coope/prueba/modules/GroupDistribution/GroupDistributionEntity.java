package com.example.prueba.coope.prueba.modules.GroupDistribution;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "group_distributions")
@ToString(onlyExplicitlyIncluded = true)
public class GroupDistributionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalGroups;

    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "distribution",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<GroupDistributionDetailEntity> details;
}
