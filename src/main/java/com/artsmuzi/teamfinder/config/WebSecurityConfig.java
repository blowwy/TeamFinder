package com.artsmuzi.teamfinder.config;

import com.artsmuzi.teamfinder.securityfilter.JWTAuthenticationFilter;
import com.artsmuzi.teamfinder.securityfilter.JWTAuthorizationFilter;
import com.artsmuzi.teamfinder.service.TokenService;
import com.artsmuzi.teamfinder.service.implementation.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenService tokenService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public WebSecurityConfig(TokenService tokenService, CustomUserDetailsService customUserDetailsService) {
        this.tokenService = tokenService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup/**").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/api/map/**/").hasRole("ADMIN").and()
                .authorizeRequests()
                    .antMatchers("/api/**").permitAll().and()
                .formLogin().loginProcessingUrl("/login").permitAll().and()
                .logout().permitAll().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), tokenService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), tokenService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.unmodifiableList(
                Arrays.asList("HEAD",
                        "GET", "POST", "PUT", "DELETE", "PATCH")));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(
                Collections.unmodifiableList(
                        Arrays.asList("Authorization", "Cache-Control", "Content-Type"))
        );

        config.setExposedHeaders(
                Collections.unmodifiableList(
                        Arrays.asList("Authorization", "Cache-Control", "Content-Type"))
        );
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
