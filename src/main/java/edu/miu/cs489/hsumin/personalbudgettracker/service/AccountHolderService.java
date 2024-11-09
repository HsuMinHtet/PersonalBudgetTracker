package edu.miu.cs489.hsumin.personalbudgettracker.service;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;

import java.util.Optional;

public interface AccountHolderService {
    Optional<AccountHolderResponseDTO> register(AccountHolderRequestDTO accountHolderRequestDTO);
}
