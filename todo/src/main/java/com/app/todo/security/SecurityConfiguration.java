package com.app.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.function.Function;

@Configuration
public class SecurityConfiguration {
    private UserDetails createUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return user;
    }

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails user01 = createUser("filipefrs", "pass");
        UserDetails user02 = createUser("user", "pass");

        return new InMemoryUserDetailsManager(user01, user02);
    }

    @Bean
    public PasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }
}
