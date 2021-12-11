package ru.smolina.request.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.smolina.request.domain.Role;
import ru.smolina.request.servises.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/user/**").hasAuthority(Role.USER.name())
		.antMatchers(HttpMethod.POST, "/user/**").hasAuthority(Role.USER.name())
		.antMatchers(HttpMethod.PUT, "/user/**").hasAuthority(Role.USER.name())
		.antMatchers(HttpMethod.PATCH, "/user/**").hasAuthority(Role.USER.name())
		.antMatchers(HttpMethod.GET, "/operator/**").hasAuthority(Role.OPERATOR.name())
		.antMatchers(HttpMethod.PATCH, "/operator/**").hasAuthority(Role.OPERATOR.name())
		.antMatchers(HttpMethod.GET, "/admin/**").hasAuthority(Role.ADMIN.name())
		.antMatchers(HttpMethod.PATCH, "/admin/**").hasAuthority(Role.ADMIN.name())
		.and()
		.csrf().disable();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

}