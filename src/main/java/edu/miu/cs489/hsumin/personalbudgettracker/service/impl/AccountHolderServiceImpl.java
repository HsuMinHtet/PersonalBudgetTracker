package edu.miu.cs489.hsumin.personalbudgettracker.service.impl;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.exception.accountHolder.UserNotFoundException;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.AccountHolderMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Address;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.AccountHolderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<AccountHolderResponseDTO> findAllUsers() {
        List<AccountHolder> accountHolders= accountHolderRepository.findAll();
        List<AccountHolderResponseDTO> accountHolderResponseDTOS= new ArrayList<>();
        for(AccountHolder accountHolder:accountHolders){
            accountHolderResponseDTOS.add(accountHolderMapper.accountHolderToAccountHolderResponseDTO(accountHolder));
        }
        return accountHolderResponseDTOS;
    }

    @Override
    public Optional<AccountHolderResponseDTO> updateUser(Integer id, AccountHolderRequestDTO accountHolderRequestDTO) {
        Optional<AccountHolder> foundAccountHolder=accountHolderRepository.findById(id);
        if(foundAccountHolder.isPresent()){
            AccountHolder accountHolder= foundAccountHolder.get();
            accountHolder.setName(accountHolderRequestDTO.name());
            accountHolder.setPassword(accountHolderRequestDTO.password());
            accountHolder.setPhone(accountHolderRequestDTO.phone());
            accountHolder.setEmail(accountHolderRequestDTO.email());
            Address address = getAddress(accountHolderRequestDTO, accountHolder);
            accountHolder.setAddress(address);

            return Optional.of(accountHolderMapper.accountHolderToAccountHolderResponseDTO(accountHolderRepository.save(accountHolder)));
        }
        throw new UserNotFoundException("Account is not found.");
    }

    private static Address getAddress(AccountHolderRequestDTO accountHolderRequestDTO, AccountHolder accountHolder) {
        Address address= accountHolder.getAddress();
        address.setCity(accountHolderRequestDTO.addressRequestDTO().city());
        address.setState(accountHolderRequestDTO.addressRequestDTO().state());
        address.setNumber(accountHolderRequestDTO.addressRequestDTO().number());
        address.setStreet(accountHolderRequestDTO.addressRequestDTO().street());
        address.setPostalCode(accountHolderRequestDTO.addressRequestDTO().postalCode());
        address.setCountry(accountHolderRequestDTO.addressRequestDTO().country());
        return address;
    }

    @Override
    public Optional<AccountHolderResponseDTO> updateUserPartially(Integer id, AccountHolderRequestDTO accountHolderRequestDTO) {
        Optional<AccountHolder> foundAccountHolder=accountHolderRepository.findById(id);
        if(foundAccountHolder.isPresent()){
            AccountHolder accountHolder= foundAccountHolder.get();
            if(accountHolderRequestDTO.password()!=null){
                accountHolder.setPassword(accountHolderRequestDTO.password());
            }
            return Optional.of(accountHolderMapper.accountHolderToAccountHolderResponseDTO(accountHolderRepository.save(accountHolder)));
        }
        throw new UserNotFoundException("Account is not found.");
    }


    public Optional<AccountHolderResponseDTO> findByAccountHolderID(Integer id) {
        return Optional.of(accountHolderMapper.accountHolderToAccountHolderResponseDTO( accountHolderRepository.findById(id).get()));
    }

    @Override
    @Transactional
    public void deleteUserByUsername(Integer id) {
        findByAccountHolderID(id).ifPresent(
                accountHolderResponseDTO -> accountHolderRepository.deleteById(id)
        );
    }

    @Override
    public List<AccountHolderResponseDTO> searchAccountHolders(String name, String city, String country) {
        List<AccountHolder> filteredAccountHolders = accountHolderRepository.findAll().stream()
                .filter(accountHolder -> (name == null || accountHolder.getName().equalsIgnoreCase(name)))
                .filter(accountHolder -> (city == null || accountHolder.getAddress().getCity().equalsIgnoreCase(city)))
                .filter(accountHolder -> (country == null || accountHolder.getAddress().getCountry().equalsIgnoreCase(country)))
                .toList();

        return filteredAccountHolders.stream()
                .map(accountHolderMapper::accountHolderToAccountHolderResponseDTO)
                .collect(Collectors.toList());
    }
}
