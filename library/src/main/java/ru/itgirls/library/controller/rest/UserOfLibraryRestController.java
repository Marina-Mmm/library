package ru.itgirls.library.controller.rest;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.library.model.UserOfLibrary;
import ru.itgirls.library.security.Role;
import ru.itgirls.library.service.UserOfLibraryService;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
@RequestMapping("/users")
public class UserOfLibraryRestController {

    private final UserOfLibraryService userOfLibraryService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String role) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase());
            UserOfLibrary newUser = userOfLibraryService.createUserOfLibrary(username, password, userRole);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("This is the incorrect role. Use: ADMIN или USER.");
        }
    }
}