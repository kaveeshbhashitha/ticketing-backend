package com.ticketing.ticketing_backend.Config;
import com.ticketing.ticketing_backend.JWT.UserInfoUserDetailsService;
import com.ticketing.ticketing_backend.filter.JwtAuthFilter;

import jakarta.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter; // Updated variable name for clarity

    @Autowired
    private UserInfoUserDetailsService userDetailsService; // Injected directly

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        Filter jwtAuthenticationFilter = jwtAuthFilter;
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                        "/api/user/register", "/api/user/login", "/api/user/logout", "/api/user/**", // Public endpoints
                        "/api/events/getAll","/api/events/getEvent/{eventId}", // Public endpoints
                       "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**","/api/events/addEvent",// Allow Swagger UI and API docs access
                       "/api/notification/**", "/api/events/**", "/api/payment/**", "/api/reservation/**" // Protected user endpoints
                )
                .permitAll().anyRequest().authenticated()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(authenticationProvider())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService); // Use injected `userDetailsService`
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
