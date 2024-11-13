package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.TransactionRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.TransactionResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.TransactionMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Transaction;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.CategoryRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.TransactionRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountHolderRepository accountHolderRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public Optional<TransactionResponseDTO> createTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction saveTransaction= transactionMapper.transactionRequestDTOToTransaction(transactionRequestDTO);
        Optional<AccountHolder> accountHolder= accountHolderRepository.findById(transactionRequestDTO.accountHolderId());
        Optional<Category> category= categoryRepository.findById(transactionRequestDTO.categoryId());
        if(accountHolder.isPresent() && category.isPresent()){
            saveTransaction.setAccountHolder(accountHolder.get());
            saveTransaction.setCategory(category.get());
            return Optional.of(transactionMapper.transactionToTransactionResponseDTO(transactionRepository.save(saveTransaction)));
        }
        return Optional.empty();
    }
}
