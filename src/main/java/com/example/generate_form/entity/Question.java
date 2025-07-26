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
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step_id", nullable = false)
    private Step step;

    @ManyToOne
    @JoinColumn(name = "input_type_id", nullable = false)
    private InputType inputType;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "label", length = 100, nullable = false)
    private String label;

    @Column(name = "placeholder", length = 255)
    private String placeholder;

    @Column(name = "options_by_text", length = 1000, nullable = true)
    private String optionsByText;

    @Column(name = "is_endpoint", nullable = false)
    private boolean isEndpoint = false;

    @Column(name = "endpoint_url", length = 255, nullable = true)
    private String endpointUrl;

    @Column(name = "endpoint_key_to_label", length = 100, nullable = true)
    private String endpointKeyToLabel;

    @Column(name = "endpoint_key_to_value", length = 100, nullable = true)
    private String endpointKeyToValue;
}
