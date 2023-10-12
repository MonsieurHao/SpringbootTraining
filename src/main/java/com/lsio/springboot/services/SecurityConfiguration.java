package com.lsio.springboot.services;
//package com.lsio.springboot.services.restfulwebservices.basic.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authorization.AuthorizationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.Customizer;

//import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
//import static org.springframework.security.config.Customizer.withDefaults;

//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public PasswordEncoder pwdEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.builder()
            .username("tom")
            .password(pwdEncoder().encode("oui"))
            .roles("ADMIN")
            .build();
        UserDetails guest = User.builder()
            .username("guest")
            .password(pwdEncoder().encode("hello"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin,guest);
    }

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http
            .authorizeHttpRequests(
            authz -> authz
                    .requestMatchers(HttpMethod.GET,"/user").permitAll()
                    .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    /* .sessionManagement(
                    session -> session.sessionCreationPolicy
                    (SessionCreationPolicy.STATELESS))
                */
            );
            http.csrf(csrf -> csrf.disable());
            
            return http.build();
    }    
}
 
