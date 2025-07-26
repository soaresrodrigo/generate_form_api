package com.example.generate_form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationResponseDTO {
    private Long id;
    private Long templateId;
    private Long layoutId;
    private String cover;
    private String title;
    private String description;
    private String textColorTitle;
    private String textColorBody;
    private String completionMessage;
}
