package com.example.generate_form.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.generate_form.dto.CustomizationRequestDTO;
import com.example.generate_form.dto.CustomizationResponseDTO;
import com.example.generate_form.entity.Customization;
import com.example.generate_form.entity.Layout;
import com.example.generate_form.entity.Template;
import com.example.generate_form.repository.CustomizationRepository;
import com.example.generate_form.repository.LayoutRepository;
import com.example.generate_form.repository.TemplateRepository;

@Service
public class CustomizationService {

    @Autowired
    private CustomizationRepository customizationRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private LayoutRepository layoutRepository;

    public CustomizationResponseDTO findByTemplateId(Long templateId) {
        Customization customization = customizationRepository.findByTemplateId(templateId).orElse(null);
        return customization != null ? toResponseDTO(customization) : null;
    }

    public CustomizationResponseDTO create(CustomizationRequestDTO dto) {
        Customization customization = fromRequestDTO(dto);
        customization = customizationRepository.save(customization);
        return toResponseDTO(customization);
    }

    public CustomizationResponseDTO update(Long id, CustomizationRequestDTO dto) {
        Optional<Customization> optional = customizationRepository.findById(id);
        if (optional.isEmpty())
            return null;
        Customization customization = optional.get();
        customization.setCover(dto.getCover());
        customization.setTitle(dto.getTitle());
        customization.setDescription(dto.getDescription());
        customization.setTextColorTitle(dto.getTextColorTitle());
        customization.setTextColorBody(dto.getTextColorBody());
        customization.setCompletionMessage(dto.getCompletionMessage());
        if (!customization.getTemplate().getId().equals(dto.getTemplateId())) {
            Template template = templateRepository.findById(dto.getTemplateId()).orElse(null);
            customization.setTemplate(template);
        }
        if (!customization.getLayout().getId().equals(dto.getLayoutId())) {
            Layout layout = layoutRepository.findById(dto.getLayoutId()).orElse(null);
            customization.setLayout(layout);
        }
        customization = customizationRepository.save(customization);
        return toResponseDTO(customization);
    }

    public void delete(Long id) {
        customizationRepository.deleteById(id);
    }

    private Customization fromRequestDTO(CustomizationRequestDTO dto) {
        Template template = templateRepository.findById(dto.getTemplateId()).orElse(null);
        Layout layout = layoutRepository.findById(dto.getLayoutId()).orElse(null);
        return new Customization(null, template, layout, dto.getCover(), dto.getTitle(), dto.getDescription(),
                dto.getTextColorTitle(), dto.getTextColorBody(), dto.getCompletionMessage());
    }

    private CustomizationResponseDTO toResponseDTO(Customization customization) {
        CustomizationResponseDTO dto = new CustomizationResponseDTO();
        dto.setId(customization.getId());
        dto.setTemplateId(customization.getTemplate() != null ? customization.getTemplate().getId() : null);
        dto.setLayoutId(customization.getLayout() != null ? customization.getLayout().getId() : null);
        dto.setCover(customization.getCover());
        dto.setTitle(customization.getTitle());
        dto.setDescription(customization.getDescription());
        dto.setTextColorTitle(customization.getTextColorTitle());
        dto.setTextColorBody(customization.getTextColorBody());
        dto.setCompletionMessage(customization.getCompletionMessage());
        return dto;
    }
}
