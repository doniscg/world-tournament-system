import { Injectable } from '@angular/core';
import { environment } from '../../../environments/envitonment';
import { HttpClient } from '@angular/common/http';
import { GroupAssignmentDTO } from '../../models/group-assignment-dto';
import { Observable } from 'rxjs';
import { DistributionResponseDTO } from '../../models/distribution-response-dto';

@Injectable({
  providedIn: 'root'
})
export class DistributionService {

    private apiUrl = `${environment.URL}/api/distributions`;

  constructor(private http: HttpClient) {}

  previewDistribution(groups: number): Observable<GroupAssignmentDTO[]> {
    return this.http.get<GroupAssignmentDTO[]>(
      `${this.apiUrl}/preview?groups=${groups}`,
      {}
    );
  }

  saveDistribution(groups: number): Observable<number> {
    return this.http.post<number>(
      `${this.apiUrl}?groups=${groups}`,
      {}
    );
  }



  getDistribution(id: number): Observable<GroupAssignmentDTO[]> {
    return this.http.get<GroupAssignmentDTO[]>(`${this.apiUrl}/${id}`
    );
  }

  getAllDistributions()

    : Observable<DistributionResponseDTO[]> {
    return this.http.get<DistributionResponseDTO[]>(
      this.apiUrl
    );
  }
}
