package edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO;

import edu.miu.cs489.hsumin.personalbudgettracker.model.TransactionType;
import java.time.LocalDate;

public record TransactionResponseDTO(
        Double amount,

        LocalDate transactionDate,

        String description,

        TransactionType type,

        AccountHolderResponseDTO accountHolderResponseDTO,

        CategoryResponseDTO categoryResponseDTO
) { }
