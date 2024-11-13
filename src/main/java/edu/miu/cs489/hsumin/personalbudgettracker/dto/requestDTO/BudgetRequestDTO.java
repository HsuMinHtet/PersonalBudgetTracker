package edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO;

public record BudgetRequestDTO(
        String title,
        Double targetAmount,
        Double currentAmount,
        Boolean isExceed

) { }
