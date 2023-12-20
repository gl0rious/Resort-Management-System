package edu.miu.cs.cs544.controller;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;

@Configuration
// @Profile("test")
public class SpringSecurityWebTestConfig {
    // @Bean
    // public UserDetailsService userDetailsService() {
    // return new InMemoryUserDetailsManager(
    // new User("admin", "{noop}password", UserType.ADMIN),
    // new User("user1", "{noop}password", UserType.CLIENT),
    // new User("user2", "{noop}password", UserType.CLIENT));
    // }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = new User("admin", "{noop}password", UserType.ADMIN);
        UserDetails user1 = new User("user1", "{noop}password", UserType.CLIENT);
        UserDetails user2 = new User("user2", "{noop}password", UserType.CLIENT);

        return new InMemoryUserDetailsManager(admin, user1, user2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                .disable()).authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admins/**").permitAll()
                        .requestMatchers("/customers/**").permitAll()
                        // .requestMatchers("/h2/**").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}