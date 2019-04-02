package com.vc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider customAuthProvider;

	@Autowired
	AuthSignInSuccessHandler authSignInSuccessHandler;

	@Autowired
	AuthSignInFailureHandler authSignInFailureHandler;

	@Autowired
	AuthSignOutSuccessHandler authSignOutSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/login","/login*", "/logout", "/error", "/perform_login", "/perform_logout", "/login.css", "/*.png", "/*.ico")
				.permitAll().anyRequest().fullyAuthenticated()
				.and().formLogin().loginPage("/login.html").permitAll()
				.loginProcessingUrl("/perform_login")
				.failureHandler(authSignInFailureHandler)
				.successHandler(authSignInSuccessHandler)
				.and().authorizeRequests().antMatchers("/login").permitAll()
				.and().logout()
				.logoutSuccessHandler(authSignOutSuccessHandler)
				.logoutUrl("/perform_logout")
				.deleteCookies("JSESSIONID", "userId");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthProvider);
	}

}