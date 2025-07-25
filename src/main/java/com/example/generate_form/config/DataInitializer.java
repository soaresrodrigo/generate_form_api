package com.example.generate_form.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.generate_form.entity.Template;
import com.example.generate_form.entity.User;
import com.example.generate_form.entity.UserRole;
import com.example.generate_form.repository.TemplateRepository;
import com.example.generate_form.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        User user1 = new User();
        user1.setFullName("Rodrigo Soares");
        user1.setEmail("rodrigo@example.com");
        user1.setPassword(passwordEncoder.encode("123456"));
        user1.setRole(UserRole.ADMIN);
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setFullName("Laís Soares");
        user2.setEmail("lais@example.com");
        user2.setPassword(passwordEncoder.encode("123456"));
        user2.setRole(UserRole.USER);
        user2 = userRepository.save(user2);

        Template template1 = new Template();
        template1.setUser(user1);
        template1.setTitle("Rodrigo's Template");
        template1.setDescription("First template for admin");
        template1.setActive(true);
        templateRepository.save(template1);

        Template template2 = new Template();
        template2.setUser(user2);
        template2.setTitle("Laís's Template");
        template2.setDescription("First template for regular user");
        template2.setActive(true);
        templateRepository.save(template2);
    }
}
