package com.bonusGo.Bonus.Go.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuración de seguridad para deshabilitar CSRF y permitir acceso a todas las rutas
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para evitar bloqueos en Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permitir acceso a todas las rutas sin autenticación
                );

        return http.build();
    }
}

  /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF si usas Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Permitir rutas públicas
                        .anyRequest().authenticated() // Requiere autenticación en otras rutas
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/home", true) // Redirigir a /home después de login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/public")
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("casa")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
*/
