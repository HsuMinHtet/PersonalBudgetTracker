package edu.miu.cs489.hsumin.personalbudgettracker.service;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.TransactionRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.TransactionResponseDTO;

import java.util.Optional;

public interface TransactionService {
    Optional<TransactionResponseDTO> createTransaction(TransactionRequestDTO transactionRequestDTO);
}
