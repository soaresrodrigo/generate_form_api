package com.example.generate_form.controller;

import com.example.generate_form.dto.UserRequestDTO;
import com.example.generate_form.dto.UserResponseDTO;
import com.example.generate_form.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<UserResponseDTO> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    return userService
        .findById(id)
        .map(user -> ResponseEntity.ok(user))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(
      @Valid @RequestBody UserRequestDTO userRequestDTO) {
    UserResponseDTO createdUser = userService.save(userRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
    UserResponseDTO updatedUser = userService.update(id, userRequestDTO);
    return ResponseEntity.ok(updatedUser);
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
    String email = authentication.getName();
    return userService
        .findByEmail(email)
        .map(user -> ResponseEntity.ok(user))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
