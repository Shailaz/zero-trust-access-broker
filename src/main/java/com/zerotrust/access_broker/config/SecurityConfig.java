package com.zerotrust.access_broker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails developer = User.withUsername("shailaj")
                .password("password123")
                .roles("DEVELOPER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();
        UserDetails hrUser = User.withUsername("hruser")
                .password("hr123")
                .roles("HR")
                .build();
        return new InMemoryUserDetailsManager(developer,admin,hrUser);
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth->auth.requestMatchers("/access").authenticated().anyRequest().permitAll()
        ).httpBasic(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
