package com.example.generate_form.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.generate_form.dto.TemplateRequestDTO;
import com.example.generate_form.dto.TemplateResponseDTO;
import com.example.generate_form.entity.Template;
import com.example.generate_form.service.TemplateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    public ResponseEntity<List<TemplateResponseDTO>> getAllTemplates() {
        List<TemplateResponseDTO> templates = templateService.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TemplateResponseDTO>> getTemplatesByUser(@PathVariable Long userId) {
        List<TemplateResponseDTO> templates = templateService.findByUserId(userId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResponseDTO> getTemplateById(@PathVariable Long id) {
        return templateService.findById(id)
                .map(this::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TemplateResponseDTO> createTemplate(@Valid @RequestBody TemplateRequestDTO dto) {
        Template template = new Template();
        template.setTitle(dto.getTitle());
        template.setDescription(dto.getDescription());
        template.setActive(dto.isActive());
        Template saved = templateService.save(dto.getUserId(), template);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateResponseDTO> updateTemplate(@PathVariable Long id,
            @Valid @RequestBody TemplateRequestDTO dto) {
        return templateService.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setDescription(dto.getDescription());
                    existing.setActive(dto.isActive());
                    Template updated = templateService.save(existing.getUser().getId(), existing);
                    return ResponseEntity.ok(toResponseDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        templateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private TemplateResponseDTO toResponseDTO(Template template) {
        return new TemplateResponseDTO(
                template.getId(),
                template.getUser().getId(),
                template.getTitle(),
                template.getDescription(),
                template.isActive());
    }
}
