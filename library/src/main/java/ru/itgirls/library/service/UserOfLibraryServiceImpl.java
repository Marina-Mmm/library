package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itgirls.library.model.UserOfLibrary;
import ru.itgirls.library.repository.UserOfLibraryRepository;
import ru.itgirls.library.security.Role;

@Service
@RequiredArgsConstructor
public class UserOfLibraryServiceImpl implements UserOfLibraryService {

    private final UserOfLibraryRepository userOfLibraryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserOfLibrary createUserOfLibrary(String username, String password, Role role) {
        if (userOfLibraryRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User with this name already exists.");
        }

        UserOfLibrary user = UserOfLibrary.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .build();
        return userOfLibraryRepository.save(user);
    }
}
