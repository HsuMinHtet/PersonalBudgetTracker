package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.TransactionRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.TransactionResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.exception.category.CategoryNotFoundException;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.TransactionMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Category;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Transaction;
import edu.miu.cs489.hsumin.personalbudgettracker.model.TransactionType;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.CategoryRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.TransactionRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountHolderRepository accountHolderRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;

    //check the current balance, budget and goal
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

    //check the current balance, budget and goal
    @Override
    public Optional<TransactionResponseDTO> updateTransaction(Long id, TransactionRequestDTO transactionRequestDTO) {
        Optional<Transaction> foundTransaction=transactionRepository.findById(id);
        if(foundTransaction.isPresent()){
            Transaction transaction= foundTransaction.get();
            if(transactionRequestDTO.amount()!=null){
                transaction.setAmount(transactionRequestDTO.amount());
            }
            if(transactionRequestDTO.type()!=null){
                transaction.setType(transactionRequestDTO.type());
            }
            if(transactionRequestDTO.transactionDate()!=null){
                transaction.setTransactionDate(transactionRequestDTO.transactionDate());
            }
            if(transactionRequestDTO.categoryId()!=null){
                Optional<Category> category= categoryRepository.findById(transactionRequestDTO.categoryId());
                transaction.setCategory(category.get());
            }
            if(transactionRequestDTO.description()!=null){
                transaction.setDescription(transactionRequestDTO.description());
            }
            return Optional.of(transactionMapper.transactionToTransactionResponseDTO(transactionRepository.save(transaction)));
        }
        throw new CategoryNotFoundException("Transaction is not found.");
    }

    //check the current balance, budget and goal
    @Override
    public void deleteTransaction(Long id) {
        Optional<Transaction> foundTransaction=transactionRepository.findById(id);
        if(foundTransaction.isPresent()){
            transactionRepository.deleteById(id);
        }
    }

    @Override
    public Optional<TransactionResponseDTO> findByTransactionID(Long id) {
       return Optional.of(transactionMapper.transactionToTransactionResponseDTO(transactionRepository.findById(id).get()));
    }

    @Override
    public List<TransactionResponseDTO> findAllTransactions(Integer accountHolder_id) {
        List<Transaction> transactions= transactionRepository.findALLByAccountHolder_Id(accountHolder_id);
        List<TransactionResponseDTO> transactionResponseDTOS= new ArrayList<>();
        for(Transaction transaction: transactions){
            transactionResponseDTOS.add(transactionMapper.transactionToTransactionResponseDTO(transaction));
        }
        return transactionResponseDTOS;
    }

    @Override
    public List<TransactionResponseDTO> searchTransactions(Integer accountHolder_id, LocalDate transactionDate, Double amount, TransactionType transactionType, String description, Long categoryId) {
        List<Transaction> filteredTransactionByAccountHolders = transactionRepository.findALLByAccountHolder_Id(accountHolder_id).stream()
                .filter(transaction -> (transactionDate == null || transaction.getTransactionDate().isAfter(transactionDate)))
                .filter(transaction -> (amount == null || transaction.getAmount().equals(amount)))
                .filter(transaction -> (transactionType == null || transaction.getType().equals(transactionType)))
                .filter(transaction -> (description == null || transaction.getDescription().equalsIgnoreCase(description)))
                .filter(transaction -> (categoryId == null || transaction.getCategory().getId().equals(categoryId)))
                .toList();

        return filteredTransactionByAccountHolders.stream()
                .map(transactionMapper::transactionToTransactionResponseDTO)
                .collect(Collectors.toList());
    }
}
