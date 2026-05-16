package com.example.prueba.coope.prueba.modules.GroupDistribution;

import com.example.prueba.coope.prueba.modules.groupManagement.GroupEntity;
import com.example.prueba.coope.prueba.modules.teamManagement.TeamEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "group_distribution_details")
@ToString(onlyExplicitlyIncluded = true)
public class GroupDistributionDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "distribution_id")
    private GroupDistributionEntity distribution;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;
}
