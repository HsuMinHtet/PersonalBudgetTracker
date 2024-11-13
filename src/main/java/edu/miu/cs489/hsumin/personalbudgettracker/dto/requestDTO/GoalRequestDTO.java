package edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO;

public record GoalRequestDTO(
         String title,
         Double targetAmount,
         Double currentAmount,
         Boolean isAchieved
) { }
