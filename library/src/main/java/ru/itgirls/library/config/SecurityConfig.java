package ru.itgirls.library.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.itgirls.library.security.Role;
import ru.itgirls.library.service.CustomUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(HttpMethod.GET, "/authors/name").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/authors/{id}").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/authors/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/authors/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/authors/{id}").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(Customizer.withDefaults())
                .httpBasic(withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
