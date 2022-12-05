package com.ideas2it.employeemanagement.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	/**
     * <p>
     * To grant the access for the methods of employee, projects and techStack based on their authorities.
     * </p>
     *
     * @return - the object of SecurityFilterChain
     */
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/employee/getById/{id}").hasAuthority("EMPLOYEE")
//				.antMatchers("/employee/get").hasAuthority("MANAGER").antMatchers("/employee/welcome").authenticated()
//				.and().formLogin().defaultSuccessUrl("/employee/welcome", true)
//
//				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		http.authenticationProvider(authenticationProvider());
		
        http.httpBasic()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/register","/loginUser").permitAll()
        //.antMatchers("/employee/get","/employee/getById{id}").hasAuthority("MANAGER")
        .anyRequest().authenticated();
        
		//.and().formLogin().defaultSuccessUrl("/employee/welcome", true)

		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        
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
