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

    @Column(name = "cover", length = 255)
    private String cover;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "color_title", length = 50)
    private String colorTitle;

    @Column(name = "color_body", length = 50)
    private String colorBody;

    @Column(name = "completion_message", length = 255)
    private String completionMessage;
}
