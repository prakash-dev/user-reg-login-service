package com.sp.user.config;

import com.sp.user.repository.AccountRepo;
import com.sp.user.repository.UserRepo;
import com.sp.user.security.UserBasicAuthenticationEntryPoint;
import com.sp.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserBasicAuthenticationEntryPoint userBasicAuthenticationEntryPoint;

    @Autowired
    private LoginService loginService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder).getUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/register/**").permitAll()
                .antMatchers("/api/users/**")
                .access("@userAuthorizationControl.checkAccessBasedOnRole(authentication)")
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(userBasicAuthenticationEntryPoint);

    }

}
