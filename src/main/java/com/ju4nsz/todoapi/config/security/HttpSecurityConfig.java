package com.ju4nsz.todoapi.config.security;

import com.ju4nsz.todoapi.config.security.filter.JwtAuthenticationFilter;
import com.ju4nsz.todoapi.util.Permissions;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class HttpSecurityConfig {

    private static final String routeTasks = "/v1/tasks";
    private static final String routeUsers = "/v1/users";

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public HttpSecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement( sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig -> {

                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers( "/error").permitAll();

                    authConfig.requestMatchers(HttpMethod.GET, routeUsers).hasAuthority(Permissions.READ_ALL_USERS.name());
                    authConfig.requestMatchers(HttpMethod.GET, routeTasks).hasAuthority(Permissions.READ_ALL_TASKS.name());
                    authConfig.requestMatchers(HttpMethod.GET, "v1/tasks/{id}").hasAuthority(Permissions.READ_TASK.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/v1/users/{id}").hasAuthority(Permissions.READ_USER.name());
                    authConfig.requestMatchers(HttpMethod.POST, routeTasks).hasAuthority(Permissions.CREATE_TASK.name());
                    authConfig.requestMatchers(HttpMethod.POST, routeUsers).permitAll();
                    authConfig.requestMatchers(HttpMethod.DELETE, "v1/tasks/{id}").hasAuthority(Permissions.DELETE_TASK.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/v1/users/{id}").hasAuthority(Permissions.DELETE_USER.name());

                    authConfig.requestMatchers(HttpMethod.GET, "/doc/swagger-ui/index.html").permitAll();

                    authConfig.anyRequest().permitAll();

                } );

        return http.build();

    }

}
