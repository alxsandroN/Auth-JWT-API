package com.token.jwt.tokensJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class TokensJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokensJwtApplication.class, args);
	}

	// Configuración de Seguridad Spring para acceder a APIS
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/test").permitAll()
					.antMatchers(HttpMethod.GET, "/testLogin").permitAll()
					.antMatchers(HttpMethod.POST, "/authJWT").permitAll()
					.anyRequest().authenticated();
		}
	}

}
