import { GroupAssignmentDTO } from "./group-assignment-dto";

export interface DistributionResponseDTO {

  distributionId: number;
  totalGroups: number;
  createdAt: string;
  groups: GroupAssignmentDTO[];

}
