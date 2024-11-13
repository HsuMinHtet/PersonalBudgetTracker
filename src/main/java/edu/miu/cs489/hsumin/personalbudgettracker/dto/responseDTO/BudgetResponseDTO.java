package edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO;


public record BudgetResponseDTO(
        String title,
        Double targetAmount,
        Double currentAmount,
        Boolean isExceed,
        CategoryResponseDTO categoryResponseDTO,
        AccountHolderResponseDTO accountHolderResponseDTO
) { }
