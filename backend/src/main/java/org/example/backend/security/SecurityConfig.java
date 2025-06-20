package org.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/api/auth/me").authenticated()
                        .requestMatchers("/oauth2/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(o -> o.defaultSuccessUrl("https://capstone-lunelle-latest.onrender.com/"))
                .logout(l -> l.logoutSuccessUrl("https://capstone-lunelle-latest.onrender.com/"));

        return http.build();
    }

}