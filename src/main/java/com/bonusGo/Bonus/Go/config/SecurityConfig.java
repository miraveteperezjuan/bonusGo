package com.bonusGo.Bonus.Go.config;

import com.bonusGo.Bonus.Go.security.JwtAuthFilter;
import com.bonusGo.Bonus.Go.security.JwtAuthenticationEntryPoint;
import com.bonusGo.Bonus.Go.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter,
                          UserDetailsServiceImpl userDetailsService,
                          JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler))
                .authorizeHttpRequests(auth -> auth
                        // Públicos
                        .requestMatchers("/auth/**").permitAll()

                        // Rutas de solo ADMIN
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/usuario/getTodos").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/usuario/eliminar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/usuario/updateMoneda/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/producto/actualizar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/objetivos/actualizar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/objetivos/registrar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/producto/registrar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/producto/eliminar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/objetivos/eliminar/**").hasAuthority("ROLE_ADMIN")

                        // Rutas accesibles por USER o ADMIN
                        .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/usuario/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/producto/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/objetivo/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        // Cualquier otra petición requiere autenticación
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
