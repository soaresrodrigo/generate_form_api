package com.example.generate_form.service;

import com.example.generate_form.dto.LoginRequestDTO;
import com.example.generate_form.dto.LoginResponseDTO;
import com.example.generate_form.dto.UserRequestDTO;
import com.example.generate_form.dto.UserResponseDTO;
import com.example.generate_form.entity.User;
import com.example.generate_form.repository.UserRepository;
import com.example.generate_form.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        UserResponseDTO userResponse = new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail()
        );
        
        return new LoginResponseDTO(jwt, userResponse);
    }
    
    public UserResponseDTO registerUser(UserRequestDTO signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }
        
        return userService.save(signUpRequest);
    }
}
