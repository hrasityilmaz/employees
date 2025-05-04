package art.timestart.spring.employees.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource){
                return new JdbcUserDetailsManager(dataSource);
        }

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint() {
                return (request, response, authException) -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/JSON");
                response.setHeader("WWW-Authenticate", "");
                response.getWriter().write("{\"error\": \"Unauthorized access\"}");
                };
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                                AuthenticationEntryPoint authenticationEntryPoint) throws Exception {

                httpSecurity.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/docs/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                );

                httpSecurity.httpBasic(httpBasicCustomizer -> httpBasicCustomizer.disable());
                httpSecurity.httpBasic(Customizer.withDefaults());
                httpSecurity.csrf(csrf -> csrf.disable());

                httpSecurity.exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(authenticationEntryPoint));

                httpSecurity.headers(headers -> headers.frameOptions(frameoptions -> frameoptions.disable()));

                return httpSecurity.build();
        }
}
