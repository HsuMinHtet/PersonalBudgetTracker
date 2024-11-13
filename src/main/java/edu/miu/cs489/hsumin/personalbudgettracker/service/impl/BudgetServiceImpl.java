package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.BudgetRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.BudgetResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    @Override
    public Optional<BudgetResponseDTO> createBudget(BudgetRequestDTO budgetRequestDTO) {

        return Optional.empty();
    }
}
