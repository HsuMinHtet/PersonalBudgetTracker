package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.AccountHolderMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.AccountHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountHolderServiceImpl implements AccountHolderService {

    private final AccountHolderMapper accountHolderMapper;
    private final AccountHolderRepository accountHolderRepository;

    @Override
    public Optional<AccountHolderResponseDTO> createAccountHolder(AccountHolderRequestDTO accountHolderRequestDTO) {
        AccountHolder accountHolder=accountHolderMapper.accountHolderRequestDTOToAccountHolder(accountHolderRequestDTO);
       return Optional.of(accountHolderMapper.accountHolderToAccountHolderResponseDTO(accountHolderRepository.save(accountHolder)));
    }

    @Override
    public Optional<AccountHolder> findByAccountHolderID(Integer id) {
        return Optional.of(accountHolderRepository.findById(id).get());
    }
}
