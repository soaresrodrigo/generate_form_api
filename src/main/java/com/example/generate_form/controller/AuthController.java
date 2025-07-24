package com.example.generate_form.controller;

import com.example.generate_form.dto.LoginRequestDTO;
import com.example.generate_form.dto.LoginResponseDTO;
import com.example.generate_form.dto.UserRequestDTO;
import com.example.generate_form.dto.UserResponseDTO;
import com.example.generate_form.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> authenticateUser(
      @Valid @RequestBody LoginRequestDTO loginRequest) {
    LoginResponseDTO response = authService.authenticateUser(loginRequest);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> registerUser(
      @Valid @RequestBody UserRequestDTO signUpRequest) {
    UserResponseDTO result = authService.registerUser(signUpRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
}
