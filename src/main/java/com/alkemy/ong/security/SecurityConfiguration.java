package com.alkemy.ong.security;

import com.alkemy.ong.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Autowired
    UserDetailsService userDetailsService;

    String[] publicEndpoint = {
        "/auth/register",
        "/auth/login", 
        "/auth/me"
    };

    private static final String[] SWAGGER = {
        "/swagger-resources/**",
        "/swagger-ui/**", "/v2/api-docs",
        "/api/docs",
        "/api/docs/**",
        "/api/docs/swagger-ui",
        "/swagger-ui.html",
        "/**/swagger-ui/**"  
    };

    String[] adminAuthorizedEndpoint = { 
        "/users", 
        "/testimonials", 
        "/testimonials/**",
        "/news/**",
        "/news/{id}",
        "/activities/**",
        "/activities/{id}",
        "/categories/**",
        "/categories/{id}",
        "/slides/**",
        "/slides/{id}",
        "/comments/**",
        "/comments", 
        "/members/**",
        "/members/{id}",
        "/contacts",
        "/contacts/**"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(SWAGGER).permitAll()
                .antMatchers(adminAuthorizedEndpoint).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/news").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/organization/public").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/organization/public").permitAll()
                .antMatchers(HttpMethod.PUT, "/news/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/testimonials/{id}").hasRole("ADMIN")
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers(publicEndpoint).permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
