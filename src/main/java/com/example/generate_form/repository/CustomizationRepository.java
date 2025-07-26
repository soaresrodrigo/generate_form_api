package com.example.generate_form.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.generate_form.entity.Customization;

@Repository
public interface CustomizationRepository extends JpaRepository<Customization, Long> {
    Optional<Customization> findByTemplateId(Long templateId);
}
