import { Component } from '@angular/core';
import { TeamDTO } from '../models/teams-dto';
import { GroupDTO } from '../models/group-dto';
import { GroupAssignmentDTO } from '../models/group-assignment-dto';
import { DistributionService } from '../core/services/distribution.service';
import { TeamService } from '../core/services/team.service';
import { GroupService } from '../core/services/group.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DistributionResponseDTO } from '../models/distribution-response-dto';

@Component({
  selector: 'app-distribution',
  standalone: true,
  imports: [FormsModule, CommonModule
  ],
  templateUrl: './distribution.component.html',
  styleUrl: './distribution.component.scss'
})
export class DistributionComponent {

  teams: TeamDTO[] = [];
  groups: GroupDTO[] = [];
  previewGroups: GroupAssignmentDTO[] = [];
  numberOfGroups: number = 0;
  newGroup: GroupDTO = {
    groupName: '',
    description: ''
  };
  newTeam: TeamDTO = {
    countryName: '',
    fifaCode: '',
    coach: '',
    fifaRanking: 1,
    registeredPlayers: 23
  };

  savedDistributions: DistributionResponseDTO[] = [];

  searchDistributionId: number | null = null;

  constructor(
    private teamService: TeamService,
    private groupService: GroupService,
    private distributionService: DistributionService
  ) {}
  ngOnInit(): void {
    this.loadTeams();
    this.loadGroups();
    this.loadDistributions();
  }
  loadTeams(): void {
    this.teamService.getTeams().subscribe({
      next: (data) => {
        this.teams = data;
      }
    });
  }
  loadGroups(): void {
    this.groupService.getGroups().subscribe({
      next: (data) => {
        console.log();

        this.groups = data;
      }
    });
  }
  generatePreview(): void {
    this.distributionService
      .previewDistribution(this.numberOfGroups)
      .subscribe({
        next: (data) => {
          this.previewGroups = data;
        },
        error: (err) => {
          alert(
            err.error.message ||
            'Error generando distribución'
          );
        }
      });
  }

saveDistribution(): void {
  this.distributionService
    .saveDistribution(this.numberOfGroups)
    .subscribe({
      next: () => {
        alert('Distribución guardada');
        this.loadDistributions();
      },
      error: (err) => {
        alert(
          err.error.message ||
          'Error guardando distribución'
        );
      }
    });
}

  saveGroup(): void {

  this.groupService.create(this.newGroup)
    .subscribe({

      next: () => {

        alert('Grupo creado');

        this.newGroup = {
          groupName: '',
          description: ''
        };

        this.loadGroups();

      },

      error: (err) => {

        alert(
          err.error.message ||
          'Error creando grupo'
        );

      }

    });

}

saveTeam(): void {

  this.teamService.create(this.newTeam)
    .subscribe({

      next: () => {

        alert('Equipo creado');

        this.newTeam = {
          countryName: '',
          fifaCode: '',
          coach: '',
          fifaRanking: 1,
          registeredPlayers: 23
        };

        this.loadTeams();

      },

      error: (err) => {

        alert(
          err.error.message ||
          'Error creando equipo'
        );

      }

    });

}
loadDistributions(): void {

  this.distributionService
    .getAllDistributions()
    .subscribe({

      next: (data) => {

        this.savedDistributions = data;

      },

      error: (err) => {

        console.error(err);

      }

    });

}

searchDistribution(): void {

  if (
    this.searchDistributionId === null ||
    this.searchDistributionId === undefined
  ) {
    this.loadDistributions();
    return;
  }

  this.distributionService
    .getDistribution(this.searchDistributionId)
    .subscribe({
      next: (data) => {
        this.savedDistributions = [
          {
            distributionId: this.searchDistributionId!,
            totalGroups: data.length,
            createdAt: new Date().toISOString(),
            groups: data
          }
        ];
      },
      error: () => {
        alert('Distribución no encontrada');
        this.loadDistributions();
      }

    });

}

onSearchChange(): void {
  if (
    this.searchDistributionId === null ||
    this.searchDistributionId === undefined
  ) {
    this.loadDistributions();
  }
}
}
