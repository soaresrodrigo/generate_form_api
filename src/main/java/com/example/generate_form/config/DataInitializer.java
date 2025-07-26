package com.example.generate_form.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.generate_form.entity.InputType;
import com.example.generate_form.entity.Layout;
import com.example.generate_form.entity.User;
import com.example.generate_form.entity.UserRole;
import com.example.generate_form.repository.InputTypeRepository;
import com.example.generate_form.repository.LayoutRepository;
import com.example.generate_form.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LayoutRepository layoutRepository;
    private final InputTypeRepository inputTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        // ADDED USERS
        User user = new User();
        user.setFullName("Rodrigo Soares");
        user.setEmail("rodrigo@example.com");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole(UserRole.ADMIN);
        user = userRepository.save(user);

        // ADDED LAYOUTS
        Layout sapphireLayout = new Layout();
        sapphireLayout.setDescription("sapphire");
        layoutRepository.save(sapphireLayout);

        // ADDED INPUTS
        List<InputType> inputTypes = List.of(
                new InputType(null, "text", "Text field"),
                new InputType(null, "textarea", "Text area"),
                new InputType(null, "checkbox", "Checkbox"),
                new InputType(null, "switch", "Switch"),
                new InputType(null, "image", "Image upload"),
                new InputType(null, "number", "Number field"),
                new InputType(null, "date", "Date picker"),
                new InputType(null, "select", "Select dropdown"),
                new InputType(null, "multiselect", "Multiple select dropdown"),
                new InputType(null, "color", "Color picker"),
                new InputType(null, "password", "Password field"));
        inputTypeRepository.saveAll(inputTypes);
    }
}
