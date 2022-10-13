package com.example.moneytransfersystem.security;

import com.example.moneytransfersystem.service.CashboxService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CashboxService cashboxService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CashboxService cashboxService, PasswordEncoder passwordEncoder) {
        this.cashboxService = cashboxService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                        (requests) -> requests
                                .antMatchers("/cabinet/**").authenticated()
                                .antMatchers("/**").permitAll()
                )
                .formLogin(
                        (form) -> form
                                .loginPage("/login")
                                .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cashboxService).passwordEncoder(passwordEncoder);
    }

    @Configuration
    static class PasswordEncoderConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

}