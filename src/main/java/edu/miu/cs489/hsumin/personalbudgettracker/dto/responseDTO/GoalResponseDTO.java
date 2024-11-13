package edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO;

import java.time.LocalDate;

public record GoalResponseDTO(
        String title,
        Double targetAmount,
        Double currentAmount,
        Boolean isAchieved
) { }
