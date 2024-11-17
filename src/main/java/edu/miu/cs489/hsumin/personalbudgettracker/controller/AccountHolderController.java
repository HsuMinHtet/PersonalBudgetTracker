package edu.miu.cs489.hsumin.personalbudgettracker.controller;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.service.AccountHolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-holder")
public class AccountHolderController {

    private final AccountHolderService accountHolderService;

    //AccountHolder(create account)
    @PostMapping
    private ResponseEntity<AccountHolderResponseDTO> createUser(@Valid @RequestBody AccountHolderRequestDTO accountHolderRequestDTO){
        Optional<AccountHolderResponseDTO> accountHolderResponseDTO=accountHolderService.createAccountHolder(accountHolderRequestDTO);
        if(accountHolderResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(accountHolderResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //AccountHolder(update password)
    @PatchMapping("/{id}")
    public ResponseEntity<AccountHolderResponseDTO>updatePartially(
            @PathVariable Integer id,
            @RequestBody AccountHolderRequestDTO accountHolderRequestDTO
    ){
        Optional<AccountHolderResponseDTO> accountHolderResponseDTO= accountHolderService.updateUserPartially(id, accountHolderRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderResponseDTO.get());
    }

    //AccountHolder(update account info)
    @PutMapping("/{id}")
    public ResponseEntity<AccountHolderResponseDTO>update(
            @PathVariable Integer id,
            @RequestBody @Valid AccountHolderRequestDTO accountHolderRequestDTO
    ){
        Optional<AccountHolderResponseDTO> accountHolderResponseDTO= accountHolderService.updateUser(id, accountHolderRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderResponseDTO.get());
    }

    //AccountHolder(find an account info)
    @GetMapping("/{id}")
    public ResponseEntity<AccountHolderResponseDTO> findUserByID(@PathVariable Integer id){
        Optional<AccountHolderResponseDTO> accountHolderResponseDTO= accountHolderService.findByAccountHolderID(id);
        if(accountHolderResponseDTO.isPresent()){
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    //Admin(Delete AccountHolder)
    @DeleteMapping("/{id}")//api/v1/users/username
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        accountHolderService.deleteUserByUsername(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Admin(Get all AccountHolder)
    @GetMapping
    public ResponseEntity<List<AccountHolderResponseDTO>> findAllUsers(){
        List<AccountHolderResponseDTO> accountHolderResponseDTOS= accountHolderService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderResponseDTOS);
    }

    //Multiple Criteria
    @GetMapping("/criteria")
    public List<AccountHolderResponseDTO> searchAccountHolders(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country
    ) {
        return accountHolderService.searchAccountHolders(name, city, country);
    }

    //Admin(Get the AccountHOlder List whose last login time is > a year)


}
