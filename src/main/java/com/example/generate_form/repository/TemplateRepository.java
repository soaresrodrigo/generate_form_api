package com.example.generate_form.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.generate_form.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByUserId(Long userId);
}
