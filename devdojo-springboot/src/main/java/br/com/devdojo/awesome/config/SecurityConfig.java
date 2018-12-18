package br.com.devdojo.awesome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import static br.com.devdojo.awesome.config.SecurityConstants.*;

import br.com.devdojo.awesome.service.CustomUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	public SecurityConfig(CustomUserDetailService customUserDetailService) {
		super();
		this.customUserDetailService = customUserDetailService;
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests()
////				.anyRequest()
////				.authenticated()
////				.and().httpBasic()
////				.and().csrf().disable();
//		
//		http.authorizeRequests()
//		.antMatchers("/*/protected/**").hasRole("USER")
//		.antMatchers("/*/admin/**").hasRole("ADMIN")
//		.and().httpBasic()
//		.and().csrf().disable();
//	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
			.antMatchers("/*/protected/**").hasRole("USER")
			.antMatchers("/*/admin/**").hasRole("ADMIN")
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailService)
					.passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//		.withUser("cledson").password("devdojo").roles("USER")
//		.and()
//		.withUser("pacheco").password("devdojo").roles("USER","ADMIN");
//	}
}
