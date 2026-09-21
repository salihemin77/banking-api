package com.example.banking_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails salih = User.builder()
                .username("salih")
                .password("{noop}salih123")
                .roles("USER")
                .build();

        UserDetails ali = User.builder()
                .username("ali")
                .password("{noop}ali123")
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(salih, ali);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET, "/api/users/**")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/api/accounts/**")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/api/transactions/**")
                        .hasRole("USER")

                        .requestMatchers(
                                "/api/accounts/*/deposit",
                                "/api/accounts/*/withdraw",
                                "/api/accounts/*/transfer/*"
                        )
                        .hasRole("MANAGER")

                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}