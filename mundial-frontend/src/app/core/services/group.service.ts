import { Injectable } from '@angular/core';
import { environment } from '../../../environments/envitonment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GroupDTO } from '../../models/group-dto';

@Injectable({
  providedIn: 'root'
})
export class GroupService {



  private apiUrl = `${environment.URL}/api/groups`;

  constructor(private http: HttpClient) {}

  create(group: GroupDTO): Observable<GroupDTO> {

    return this.http.post<GroupDTO>(

      this.apiUrl,

      group

    );

  }

  getGroups(): Observable<GroupDTO[]> {

    return this.http.get<GroupDTO[]>(

      this.apiUrl

    );

  }

  findById(id: number): Observable<GroupDTO> {

    return this.http.get<GroupDTO>(

      `${this.apiUrl}/${id}`

    );

  }

  update(id: number, group: GroupDTO): Observable<GroupDTO> {

    return this.http.put<GroupDTO>(

      `${this.apiUrl}/${id}`,

      group

    );

  }

  delete(id: number): Observable<void> {

    return this.http.delete<void>(

      `${this.apiUrl}/${id}`

    );

  }
}
