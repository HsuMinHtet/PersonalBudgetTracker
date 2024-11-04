package edu.miu.cs489.hsumin.personalbudgettracker;

import edu.miu.cs489.hsumin.personalbudgettracker.model.Address;
import edu.miu.cs489.hsumin.personalbudgettracker.model.State;
import edu.miu.cs489.hsumin.personalbudgettracker.model.User;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class PersonalBudgetTrackerApplication {
    private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(PersonalBudgetTrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            //Register User
            User user=new User("Jimmy","641-222-9922","12Password@!","jimmy@gmail.com");
            Address address= new Address("Fairfield",52556,new State("Iowa"));
            user.setAddress(address);
            System.out.println( userRepository.save(user));
        };
    }
}
