package ru.ilynoiz.tacocloud.configs;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.ilynoiz.tacocloud.security.User;
import ru.ilynoiz.tacocloud.data.UserRepository;

import java.util.Objects;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if(Objects.nonNull(user)){
                return user;
            }
            throw new UsernameNotFoundException(String.format("User with name \"%s\" not found.", username));
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/design", "/orders").hasRole("USER")
                        .anyRequest().permitAll())
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true))
                .logout((logout) -> logout.logoutSuccessUrl("/login"));
        return http.build();
    }
}
