package edu.miu.cs489.hsumin.personalbudgettracker.service;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AccountHolderService {
    Optional<AccountHolderResponseDTO> createAccountHolder(AccountHolderRequestDTO accountHolderRequestDTO);
    List<AccountHolderResponseDTO> findAllUsers();
    Optional<AccountHolderResponseDTO> updateUser(Integer id, AccountHolderRequestDTO accountHolderRequestDTO);
    Optional<AccountHolderResponseDTO> updateUserPartially(Integer id, AccountHolderRequestDTO accountHolderRequestDTO);
    Optional<AccountHolderResponseDTO>  findByAccountHolderID(Integer id);
    void deleteUserByUsername(Integer id);

    List<AccountHolderResponseDTO> searchAccountHolders(String name, String city, String country);
}
