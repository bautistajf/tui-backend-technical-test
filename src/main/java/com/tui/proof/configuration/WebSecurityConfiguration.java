package com.tui.proof.configuration;

import com.tui.proof.security.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static com.tui.proof.util.ControllerHelper.*;

@EnableWebSecurity
@Profile(value = {"local"})
@SuppressWarnings("squid:S1075")
public class WebSecurityConfiguration {


    @Order(1)
    @Configuration
    @AllArgsConstructor
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final JwtRequestFilter jwtRequestFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/**" + AUTH_CONTROLLER_PATH + "/login").permitAll()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/**/api-docs/**").permitAll()
                    .antMatchers("/**/management/**").permitAll()
                    .antMatchers("/**/management").permitAll()
                    .antMatchers("/**" + CLIENT_CONTROLLER_PATH).permitAll()
                    .antMatchers("/**" + CLIENT_CONTROLLER_PATH + "/**").permitAll()
                    .antMatchers(HttpMethod.PUT, "/**" + ORDER_CONTROLLER_PATH + "/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/**" + ORDER_CONTROLLER_PATH).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, ex) -> response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            )
                    )
                    .and()
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Order(2)
    @Configuration
    @AllArgsConstructor
    public static class InMemoryUserDetailsConfiguration extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;

        @Bean
        @Override
        public UserDetailsService userDetailsService() {
            return new InMemoryUserDetailsManager(
                    User.withUsername("admin")
                            .password(passwordEncoder.encode("12345678"))
                            .roles("USER")
                            .build());
        }
    }
}
