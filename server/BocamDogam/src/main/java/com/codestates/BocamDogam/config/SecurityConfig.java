package com.codestates.BocamDogam.config;

import com.codestates.BocamDogam.auth.JwtTokenizer;
import com.codestates.BocamDogam.auth.filter.JwtAuthenticationFilter;
import com.codestates.BocamDogam.auth.filter.JwtVerificationFilter;
import com.codestates.BocamDogam.auth.handler.MemberAuthenticationFailureHandler;
import com.codestates.BocamDogam.auth.handler.MemberAuthenticationSuccessHandler;
import com.codestates.BocamDogam.auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    public SecurityConfig(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login_processing")
                .and()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        // 게시판
                        .antMatchers(HttpMethod.POST, "/*/board/**").hasAnyRole("USER", "APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/*/board/**").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/*/board/**").hasAnyRole("USER", "APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/*/board/**").hasAnyRole("USER", "APPROVED", "ADMIN")
                        // 질문
                        .antMatchers(HttpMethod.POST, "/main/qna/questions").hasAnyRole("USER", "APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/main/qna/**").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/main/qna/questions/*").hasAnyRole("USER", "APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/main/qna/*").hasAnyRole("USER", "APPROVED", "ADMIN")
                        // 답변F
                        .antMatchers(HttpMethod.POST, "/main/qna/questions/*/answers").hasAnyRole("APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/main/qna/**").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/main/qna/questions/*/answer/*").hasAnyRole("APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "main/qna/questions/*/answer/*").hasAnyRole("APPROVED", "ADMIN")
                        // 리뷰
                        .antMatchers(HttpMethod.POST, "/main/institutes/*/reviews").hasAnyRole("APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/main/institutes/**").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/main/institutes/*/reviews/*").hasAnyRole("APPROVED", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/main/institutes/*/reviews/*").hasAnyRole("APPROVED", "ADMIN")

                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

}
