package edu.miu.cs489.hsumin.personalbudgettracker.dto.auth;

import edu.miu.cs489.hsumin.personalbudgettracker.config.JWTService;
import edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO.AccountHolderRequestDTO;
import edu.miu.cs489.hsumin.personalbudgettracker.mapper.AccountHolderMapper;
import edu.miu.cs489.hsumin.personalbudgettracker.model.AccountHolder;
import edu.miu.cs489.hsumin.personalbudgettracker.model.User;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.AccountHolderRepository;
import edu.miu.cs489.hsumin.personalbudgettracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountHolderRepository accountHolderRepository;
    private final UserRepository userRepository;
    private final AccountHolderMapper accountHolderMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse accountHolderRegister(AccountHolderRequestDTO accountHolderRequestDTO) {
        AccountHolder accountHolder=accountHolderMapper.accountHolderRequestDTOToAccountHolder(accountHolderRequestDTO);
        accountHolder.setPassword(passwordEncoder.encode(accountHolderRequestDTO.password()));
        AccountHolder registerAccountHolder= accountHolderRepository.save(accountHolder);
        //generate token
        String token= jwtService.generateToken(registerAccountHolder);
        return new AuthenticationResponse(token, registerAccountHolder.getId(), registerAccountHolder.getRole());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        //authentication object creation to send to the AuthenticationManager
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        //After authentication is successfully, generate Token for this authenticated user
        User user= (User) authentication.getPrincipal();

        // Update the last login time
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        String token=jwtService.generateToken(user);
        return new AuthenticationResponse(token, user.getId(), user.getRole());
    }
}
