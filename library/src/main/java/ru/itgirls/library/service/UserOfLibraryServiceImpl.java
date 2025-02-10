package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.itgirls.library.model.UserOfLibrary;
import ru.itgirls.library.repository.UserOfLibraryRepository;
import ru.itgirls.library.security.Role;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOfLibraryServiceImpl implements UserOfLibraryService {

    private final UserOfLibraryRepository userOfLibraryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserOfLibrary createUserOfLibrary(String username, String password, Role role) {
        log.info("Try to create a new user with username: {}", username);
        if (!StringUtils.hasText(username)) {
            log.warn("Attempt to register user with empty or null username.");
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (!StringUtils.hasText(password)) {
            log.warn("Attempt to register user with empty or null password.");
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (userOfLibraryRepository.findByUsername(username).isPresent()) {
            log.warn("User with username '{}' already exists.", username);
            throw new IllegalArgumentException("User with this name already exists.");
        }
        try {
            UserOfLibrary user = UserOfLibrary.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .build();
            UserOfLibrary savedUser = userOfLibraryRepository.save(user);
            log.info("User '{}' had been created with role: {}", savedUser.getUsername(), savedUser.getRole());
            return savedUser;
        } catch (Exception e) {
            log.error("Error occurred while creating user '{}': {}", username, e.getMessage(), e);
            throw e;
        }
    }
}