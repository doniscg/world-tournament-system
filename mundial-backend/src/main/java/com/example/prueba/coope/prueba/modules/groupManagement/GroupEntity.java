package com.example.prueba.coope.prueba.modules.groupManagement;

import com.example.prueba.coope.prueba.modules.teamManagement.TeamEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups_worldcup")
@ToString(onlyExplicitlyIncluded = true)
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column( name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "description")
    private String description;


}
