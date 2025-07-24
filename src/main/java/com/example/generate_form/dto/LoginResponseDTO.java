package com.example.generate_form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

  private String accessToken;
  private String tokenType = "Bearer";
  private UserResponseDTO user;

  public LoginResponseDTO(String accessToken, UserResponseDTO user) {
    this.accessToken = accessToken;
    this.user = user;
  }
}
