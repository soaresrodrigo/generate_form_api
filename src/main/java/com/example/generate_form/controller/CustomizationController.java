package com.example.generate_form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.generate_form.dto.CustomizationRequestDTO;
import com.example.generate_form.dto.CustomizationResponseDTO;
import com.example.generate_form.service.CustomizationService;

@RestController
@RequestMapping("/customizations")
public class CustomizationController {

    @Autowired
    private CustomizationService customizationService;

    @GetMapping("/template/{templateId}")
    public ResponseEntity<CustomizationResponseDTO> getByTemplateId(@PathVariable Long templateId) {
        CustomizationResponseDTO dto = customizationService.findByTemplateId(templateId);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CustomizationResponseDTO> create(@RequestBody CustomizationRequestDTO dto) {
        CustomizationResponseDTO created = customizationService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomizationResponseDTO> update(@PathVariable Long id,
            @RequestBody CustomizationRequestDTO dto) {
        CustomizationResponseDTO updated = customizationService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
