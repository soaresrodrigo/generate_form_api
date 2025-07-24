package com.example.generate_form.dto;

import com.example.generate_form.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

  private Long id;
  private String fullName;
  private String email;
  private UserRole role;
}
