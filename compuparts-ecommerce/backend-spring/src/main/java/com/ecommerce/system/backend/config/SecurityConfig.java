package com.ecommerce.system.backend.config;


import com.ecommerce.system.backend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	@Autowired
	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());

		return auth;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
				.cors(Customizer.withDefaults())
				.csrf((csrf -> csrf.disable()))
				.formLogin(form -> form.disable())
				.authenticationProvider(authenticationProvider())
				.authorizeHttpRequests(configurer ->
						configurer
								.requestMatchers("/api/**").hasRole("ADMIN")
								.anyRequest().authenticated())
				.exceptionHandling(configurer ->
						configurer
								.accessDeniedPage("/access-denied"))
				.httpBasic(Customizer.withDefaults())
				.build();


	}


}





