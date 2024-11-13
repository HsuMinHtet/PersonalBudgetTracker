package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.GoalRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.GoalResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.GoalMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Goal;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.GoalRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    @Override
    public Optional<GoalResponseDTO> createGoal(GoalRequestDTO goalRequestDTO) {
        Goal saveGoal= goalMapper.goalRequestDTOToGoal(goalRequestDTO);
        return Optional.of(goalMapper.goalToGoalResponseDTO(goalRepository.save(saveGoal)));
    }
}
