package com.example.prueba.coope.prueba.modules.teamManagement;

import com.example.prueba.coope.prueba.modules.groupManagement.GroupEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "teams",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "country_name"),
                @UniqueConstraint(columnNames = "fifa_code")
        }
)
@ToString(onlyExplicitlyIncluded = true)
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "fifa_code", nullable = false, length = 3)
    private String fifaCode;

    @Column(nullable = false)
    private String coach;

    @Column(nullable = false)
    private Integer fifaRanking;

    @Column(nullable = false)
    private Integer registeredPlayers;

}
