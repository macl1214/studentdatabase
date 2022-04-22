package com.cubas.studentmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cubas.studentmanagement.service.UserService;

/**
 * @Configuration:
 * 	- Indicates that a class declares one or more @Bean methods and may be processed by the Spring container 
 *    to generate bean definitions and service requests for those beans at runtime
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService schoolMemberService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//  AuthenticationProvider bean definition
//	An AuthenticationProvider implementation that retrieves user details 
//	from an UserDetailsService.
//	Uses a Data Access Object (DAO) to retrieve user information from a relational database
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		
		auth.setUserDetailsService(schoolMemberService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		
		return auth;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/register").permitAll()
					.antMatchers("/error/**").permitAll()
					.antMatchers("/saveUser").permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/announcements/list").hasRole("SCHOOL_MEMBER")
					.antMatchers("/announcements/**").hasRole("ADMIN")
					.antMatchers("/course/list").permitAll()
					.antMatchers("/course/description/**").permitAll()
					.antMatchers("/course/**").hasRole("ADMIN")
//					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.logoutSuccessUrl("/");
//					.and()
//				.exceptionHandling()
//					.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	
		web
				.ignoring()
					.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/assets/**");
	}
}
