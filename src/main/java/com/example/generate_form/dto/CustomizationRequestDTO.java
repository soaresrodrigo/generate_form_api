package com.example.generate_form.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationRequestDTO {
    @NotNull(message = "Template is required")
    private Long templateId;

    @NotNull(message = "Layout is required")
    private Long layoutId;

    @Size(max = 255, message = "Cover must not exceed 255 characters")
    private String cover;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotBlank(message = "Text color title is required")
    @Size(max = 7, message = "Text color title must not exceed 7 characters")
    private String textColorTitle;

    @NotBlank(message = "Text color body is required")
    @Size(max = 7, message = "Text color body must not exceed 7 characters")
    private String textColorBody;

    @Size(max = 255, message = "Completion message must not exceed 255 characters")
    private String completionMessage;
}
