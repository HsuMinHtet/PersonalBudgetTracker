package edu.miu.cs489.hsumin.personalbudgettracker;

import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.*;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.AccountHolderResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO.CategoryResponseDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.model.*;
import edu.miu.cs489.hsumin.personalbudgettracker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class PersonalBudgetTrackerApplication {

    private final AccountHolderService accountHolderService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TransactionService transactionService;
    private final GoalService goalService;

    public static void main(String[] args) {
        SpringApplication.run(PersonalBudgetTrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            System.out.println();
            //Create User(Admin)
            UserRequestDTO userRequestDTO=new UserRequestDTO("Tim","641-233-9993","11Password@!", "tim@gmail.com", Role.ADMIN);
            System.out.println("Admin =>>>: "+userService.createUserAdmin(userRequestDTO)+"is saved");

            //Create Address
            AddressRequestDTO addressRequestDTO = new AddressRequestDTO("New York","5th Avenue","123",10001,"USA","NY");

            //Create User(AccountHolder)
            AccountHolderRequestDTO accountHolderRequestDTO=new AccountHolderRequestDTO("Jimmy","641-222-9922","12Password@!","jimmy@gmail.com", Role.ACCOUNT_HOLDER,addressRequestDTO);
            System.out.println("Account Holder1 =>>>: "+accountHolderService.createAccountHolder(accountHolderRequestDTO)+"is saved");

            //Create Category
            CategoryRequestDTO categoryRequestDTO= new CategoryRequestDTO("Transportation", "Daily Expense");
            System.out.println("New Category =>>: "+categoryService.createCategory(categoryRequestDTO)+" is saved.");

            //Create Transaction
            TransactionRequestDTO transactionRequestDTO=new TransactionRequestDTO(50.0, LocalDate.now(),"Daily Transportation" , TransactionType.INCOME,2,1L);
            System.out.println("New Transaction1 =>>: "+transactionService.createTransaction(transactionRequestDTO));
            TransactionRequestDTO transactionRequestDTO1=new TransactionRequestDTO(30.0, LocalDate.now(),"Daily Transportation" , TransactionType.EXPENSE,2,1L);
            System.out.println("New Transaction2 =>>: "+transactionService.createTransaction(transactionRequestDTO1));

            //Create Goal
            GoalRequestDTO goalRequestDTO= new GoalRequestDTO("Nov Target $500", 500.0, 0.0,  false);
            System.out.println("New Goal =>>: "+goalService.createGoal(goalRequestDTO));

            //Create Budget


            System.out.println();
        };
    }
}
