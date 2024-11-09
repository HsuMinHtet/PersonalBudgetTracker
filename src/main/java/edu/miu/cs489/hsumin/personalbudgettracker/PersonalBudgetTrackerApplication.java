package edu.miu.cs489.hsumin.personalbudgettracker;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AddressRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Address;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Role;
import edu.miu.cs489.hsumin.personalbudgettracker.model.User;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.UserRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.service.AccountHolderService;
import edu.miu.cs489.hsumin.personalbudgettracker.service.impl.AccountHolderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class PersonalBudgetTrackerApplication {
   // private final UserRepository userRepository;
    private final AccountHolderService accountHolderService;
    public static void main(String[] args) {
        SpringApplication.run(PersonalBudgetTrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            //Create Address
            AddressRequestDTO addressRequestDTO = new AddressRequestDTO("New York","5th Avenue","123",10001,"USA","NY");
            //Create User(Admin)
            AccountHolderRequestDTO accountHolderRequestDTO=new AccountHolderRequestDTO("Jimmy","641-222-9922","12Password@!","jimmy@gmail.com", Role.ACCOUNT_HOLDER,addressRequestDTO);
            System.out.println(">>>AccountHolder<<<<<"+accountHolderRequestDTO);
            System.out.println(accountHolderService.register(accountHolderRequestDTO)+"is saved");
            //System.out.println( userRepository.save(addressRequestDTO));
        };
    }
}
