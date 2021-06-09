package ru.ivanov.evgeny.eventscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import ru.ivanov.evgeny.eventscheduler.security.jwt.JWTConfigurer;
import ru.ivanov.evgeny.eventscheduler.security.jwt.JwtAccessDeniedHandler;
import ru.ivanov.evgeny.eventscheduler.security.jwt.JwtAuthenticationEntryPoint;
import ru.ivanov.evgeny.eventscheduler.security.jwt.TokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint authenticationErrorHandler,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // Configure BCrypt password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // create no session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/event").permitAll()
                .antMatchers("/event/*").permitAll()
                .antMatchers("/events/filtered").permitAll()
                .antMatchers("/chat/event/**").permitAll()
                .antMatchers("/chat/EVENT/**").permitAll()
                .antMatchers("/categories").permitAll()
                .antMatchers("/ws/**").permitAll()
                .antMatchers("/events").permitAll()
                .antMatchers("/event/checkUserAsEventMember").permitAll()
                .antMatchers("/invite/status").permitAll()
                .antMatchers("/invite/register").permitAll()
                .antMatchers("/invite").permitAll()
                .antMatchers("/invite/accept").permitAll()
                .antMatchers("/invite/decline").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/account/avatar/defaultImage").permitAll()
                .antMatchers("/account/avatar/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .apply(securityConfigurerAdapter());

        httpSecurity.cors();

    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

}

