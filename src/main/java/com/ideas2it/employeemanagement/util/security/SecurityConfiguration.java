package com.ideas2it.employeemanagement.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws EmployeeManagementException {
		try {
			auth.authenticationProvider(authenticationProvider());
		} catch (Exception ex) {
			throw new EmployeeManagementException(ex.getMessage(), "200", HttpStatus.OK);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/employee/getById/{id}").hasAuthority("EMPLOYEE")
//				.antMatchers("/employee/get").hasAuthority("MANAGER").antMatchers("/employee/welcome").authenticated()
//				.and().formLogin().defaultSuccessUrl("/employee/welcome", true)
//
//				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.httpBasic()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/register").permitAll()
        .antMatchers("/employee/get","/employee/getById{id}").hasAuthority("MANAGER")
        .anyRequest().authenticated()
        
		.and().formLogin().defaultSuccessUrl("/employee/welcome", true)

		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
