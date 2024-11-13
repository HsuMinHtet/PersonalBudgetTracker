package edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.CategoryResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;
import edu.miu.cs489.hsumin.personalbudgettracker.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

public record TransactionRequestDTO(
         @NotBlank(message = "Transaction Amount is required")
         Double amount,

         @CreatedDate
         LocalDate transactionDate,

         String description,

         @NotBlank(message = "Transaction Type is required")
         TransactionType type,

         Integer accountHolderId,

         Long categoryId

) {}
