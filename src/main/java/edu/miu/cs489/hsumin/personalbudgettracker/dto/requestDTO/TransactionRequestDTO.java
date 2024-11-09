package edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO;

import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;
import edu.miu.cs489.hsumin.personalbudgettracker.model.TransactionType;

import java.time.LocalDate;

public record TransactionRequestDTO(
         Double amount,
         LocalDate transactionDate,
         String description,
         TransactionType type,
         Category category
) {}
