import { GroupDTO } from "./group-dto";
import { TeamDTO } from "./teams-dto";

export interface GroupAssignmentDTO {

  group: GroupDTO;

  teams: TeamDTO[];

}
