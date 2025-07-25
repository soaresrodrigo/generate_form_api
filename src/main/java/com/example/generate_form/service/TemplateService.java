package com.example.generate_form.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.generate_form.entity.Template;
import com.example.generate_form.entity.User;
import com.example.generate_form.repository.TemplateRepository;
import com.example.generate_form.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;

    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    public Optional<Template> findById(Long id) {
        return templateRepository.findById(id);
    }

    public List<Template> findByUserId(Long userId) {
        return templateRepository.findByUserId(userId);
    }

    public Template save(Long userId, Template template) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        template.setUser(user);
        return templateRepository.save(template);
    }

    public void deleteById(Long id) {
        templateRepository.deleteById(id);
    }
}
