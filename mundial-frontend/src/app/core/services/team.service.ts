import { Injectable } from '@angular/core';
import { environment } from '../../../environments/envitonment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TeamDTO } from '../../models/teams-dto';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

private apiUrl = `${environment.URL}/api/teams`;

  constructor(private http: HttpClient) {}

  create(team: TeamDTO): Observable<TeamDTO> {

    return this.http.post<TeamDTO>(

      this.apiUrl,

      team

    );

  }

  getTeams(): Observable<TeamDTO[]> {

    return this.http.get<TeamDTO[]>(

      this.apiUrl

    );

  }

  findById(id: number): Observable<TeamDTO> {

    return this.http.get<TeamDTO>(

      `${this.apiUrl}/${id}`

    );

  }

  update(id: number, team: TeamDTO): Observable<TeamDTO> {

    return this.http.put<TeamDTO>(

      `${this.apiUrl}/${id}`,

      team

    );

  }

  delete(id: number): Observable<void> {

    return this.http.delete<void>(

      `${this.apiUrl}/${id}`

    );

  }
}
