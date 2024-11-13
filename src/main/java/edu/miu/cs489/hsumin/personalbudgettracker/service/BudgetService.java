package edu.miu.cs489.hsumin.personalbudgettracker.service;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.BudgetRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.BudgetResponseDTO;

import java.util.Optional;

public interface BudgetService {
    Optional<BudgetResponseDTO> createBudget(BudgetRequestDTO budgetRequestDTO);
}
