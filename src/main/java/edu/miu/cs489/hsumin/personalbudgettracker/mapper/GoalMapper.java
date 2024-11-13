package edu.miu.cs489.hsumin.personalbudgettracker.mapper;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.GoalRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.GoalResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Goal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    Goal goalRequestDTOToGoal(GoalRequestDTO goalRequestDTO);
    GoalResponseDTO goalToGoalResponseDTO(Goal goal);

}
