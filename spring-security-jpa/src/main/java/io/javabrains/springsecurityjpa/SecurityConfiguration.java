package io.javabrains.springsecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(basicAuthProvider());
    }

    @Bean
    public AuthenticationProvider basicAuthProvider() {
    DaoAuthenticationProvider basicauth = new DaoAuthenticationProvider();
    basicauth.setPasswordEncoder(passwordEncoder());
    basicauth.setUserDetailsService(userDetailsService);
    return basicauth;
    }

    @Bean
    public  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSorce()
    {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration cros = new CorsConfiguration();
    cros.addAllowedMethod(HttpMethod.DELETE);
    cros.addAllowedMethod(HttpMethod.POST);
    cros.addAllowedMethod(HttpMethod.PUT);
    cros.addAllowedMethod(HttpMethod.GET);
    cros.addAllowedMethod(HttpMethod.OPTIONS);
    cros.addAllowedMethod(HttpMethod.PATCH);
    cros.addAllowedOrigin("*");
    cros.addAllowedHeader("*");
    source.registerCorsConfiguration("/**", cros);
    return source;
    }
}
