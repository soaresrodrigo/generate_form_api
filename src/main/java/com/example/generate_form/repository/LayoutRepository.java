package com.example.generate_form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.generate_form.entity.Layout;

@Repository
public interface LayoutRepository extends JpaRepository<Layout, Long> {
}
