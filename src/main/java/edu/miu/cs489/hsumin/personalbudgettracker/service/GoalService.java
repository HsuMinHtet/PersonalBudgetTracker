package edu.miu.cs489.hsumin.personalbudgettracker.service;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.GoalRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.GoalResponseDTO;

import java.util.Optional;

public interface GoalService {
    Optional<GoalResponseDTO> createGoal(GoalRequestDTO goalRequestDTO);
}
