package cn.king.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cn.king.web.service.AuthUserDetailsService;

/**
 * @author wlh by 2018-09-06
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthUserDetailsService authUserDetailsService;
	@Autowired
	AuthenticationSuccessHandler authSuccessHandler;
	@Autowired
	AuthenticationFailureHandler authFailureHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
		        .loginPage("/auth/require")
		        .loginProcessingUrl("/auth/form")
		        .successHandler(authSuccessHandler)
		        .failureHandler(authFailureHandler)
		        .permitAll()
	        	.and()
	        .authorizeRequests()
		        .antMatchers("/auth.html", "/login.html", "/code/img", "/css/**", "/img/**", "/js/**")
		        .permitAll()
		        .anyRequest()
		        .authenticated()
		        .and()
	        .csrf().disable();
	}

    
}
