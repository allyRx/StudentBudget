package org.allyrx.studentbudget.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
@EnableWebSecurity
public class ApplicationSecurity {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    public ApplicationSecurity(BCryptPasswordEncoder bCryptPasswordEncoder, JwtFilter jwtFilter, UserDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authorize -> authorize
                                // Authentification ouverte
                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                                // Budget : GET pour parent et étudiant / autre que GET pour parent seulement
                                .requestMatchers(HttpMethod.GET, "/budget/**").hasAnyRole("PARENT", "STUDENT")
                                .requestMatchers("/budget/**").hasRole("PARENT")

                                // Dépense : GET pour étudiant et parent / autre que GET pour étudiant seulement
                                .requestMatchers(HttpMethod.GET, "/depense/**").hasAnyRole("STUDENT", "PARENT")
                                .requestMatchers("/depense/**").hasRole("STUDENT")

                                // Catégories : gérées uniquement par l'étudiant
                                .requestMatchers("/category/**").hasRole("STUDENT")

                                // Rôles : gérés uniquement par l'admin
                                .requestMatchers("/role/**").hasRole("ADMIN")

                                // Toute autre requête doit être authentifiée
                                .anyRequest().authenticated()
                        )
                                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                                )
                                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                                .build()
                                ;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }
}
