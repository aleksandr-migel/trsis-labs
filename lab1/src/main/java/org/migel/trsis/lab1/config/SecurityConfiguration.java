package org.migel.trsis.lab1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);
        http.sessionManagement((configurer) -> {
            configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                    ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                                            requests.requestMatchers(new String[]{"/**"})).permitAll()
                                            .requestMatchers(new String[]{"/api/v*/**"})).permitAll()
                            .anyRequest()).denyAll();
        });

        return (SecurityFilterChain)http.build();
    }
}