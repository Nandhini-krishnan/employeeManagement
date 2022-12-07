package com.ideas2it.employeemanagement.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ideas2it.employeemanagement.service.impl.UserDetailsServiceImpl;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 * <p>
 * Handles the security for Employee Management Application
 * </p
 *
 * @author Naganandhini
 * @version 1.0 01-DEC-2022
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	/**
     * <p>
     * To create the object of BCryptPasswordEncoder in spring container
     * </p>
     *
     * @return - the object of BCryptPasswordEncoder
     */
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	/**
     * <p>
     * To grant the access for the methods of employee, projects and techStack based on their authorities.
     * </p>
     *
     * @return - the object of SecurityFilterChain
     */
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	
		http.authenticationProvider(authenticationProvider());
		http.httpBasic().disable();
		http.csrf().disable().authorizeRequests().antMatchers("/login","/register").permitAll()
				.antMatchers(HttpMethod.POST,"/employee").hasAnyAuthority("manager","Admin")
				.antMatchers(HttpMethod.PUT,"/employee/{id}").hasAnyAuthority("manager","Employee")
				.antMatchers(HttpMethod.DELETE,"/employee/{id}").hasAnyAuthority("manager","Admin")
				//.antMatchers("/employee/{id}","/employee","/search","/get-employees-in-range","/get-employees-by-multiple-id").permitAll()
				
				.antMatchers(HttpMethod.POST,"/project").hasAnyAuthority("manager","Admin")
				.antMatchers(HttpMethod.PUT,"/project/{id}").hasAnyAuthority("manager")
				.antMatchers(HttpMethod.DELETE,"/project/{id}").hasAnyAuthority("manager","Admin")
				//.antMatchers("/project/{id}","/project").permitAll()
				
				.antMatchers(HttpMethod.POST,"/techStack").hasAuthority("Admin")
				.antMatchers(HttpMethod.PUT,"/techStack/{id}").hasAuthority("Admin")
				.antMatchers(HttpMethod.DELETE,"/techStack/{id}").hasAuthority("Admin")
				//.antMatchers("/techStack/{id}","/techStack").permitAll()
				
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
     * <p>
     * To get the object of authenticationProvider in spring container and set the user details with encoding method.
     * </p>
     *
     * @return - the object of DaoAuthenticationProvider
     */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
