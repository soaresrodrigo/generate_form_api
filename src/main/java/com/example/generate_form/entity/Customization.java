package com.example.generate_form.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @ManyToOne
    @JoinColumn(name = "layout_id", nullable = false)
    private Layout layout;

    @Column(name = "cover", length = 255, nullable = true)
    private String cover;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "text_color_title", length = 7, nullable = false)
    private String textColorTitle = "#222222";

    @Column(name = "text_color_body", length = 7, nullable = false)
    private String textColorBody = "#333333";

    @Column(name = "completion_message", length = 255, nullable = true)
    private String completionMessage;
}
