package com.UserService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//Brings in the main builder used to configure web security rules.

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//Turns on Spring Security for the application.
import org.springframework.security.web.SecurityFilterChain;
//This is the object that represents your final security rules.
//Spring Security uses a “filter chain” to apply authentication/authorization logic to every incoming request.

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// Setting prePostEnabled = true means
// "Allow me to use @PreAuthorize and @PostAuthorize".
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt());
    return http.build();
  }

}
