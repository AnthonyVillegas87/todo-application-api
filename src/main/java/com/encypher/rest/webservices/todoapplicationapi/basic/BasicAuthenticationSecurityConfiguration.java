package com.encypher.rest.webservices.todoapplicationapi.basic;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {

    // Filter chain for basic authentication. Disable CSRF
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSession httpSession) throws Exception {


        // enable authentication for every request
      return  http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        )
          // basic authentication
        .httpBasic(Customizer.withDefaults())
         // configuring a stateless session
        .sessionManagement(
                session -> session.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS)
        ).csrf().disable().build();
    }

}
