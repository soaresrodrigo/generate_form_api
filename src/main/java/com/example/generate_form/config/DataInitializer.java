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
                InputType.builder().name("text").description("Text field").build(),
                InputType.builder().name("textarea").description("Text area").build(),
                InputType.builder().name("checkbox").description("Checkbox").build(),
                InputType.builder().name("switch").description("Switch").build(),
                InputType.builder().name("image").description("Image upload").build(),
                InputType.builder().name("number").description("Number field").build(),
                InputType.builder().name("date").description("Date picker").build(),
                InputType.builder().name("select").description("Select dropdown").build(),
                InputType.builder().name("multiselect").description("Multiple select dropdown").build(),
                InputType.builder().name("color").description("Color picker").build(),
                InputType.builder().name("password").description("Password field").build());

        inputTypeRepository.saveAll(inputTypes);

    }
}
