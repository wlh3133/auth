package cn.king.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cn.king.auth.core.service.AuthUserDetailsService;

/**
 * @author wlh by 2018-09-06
 *
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthUserDetailsService authUserDetailsService;
	
    /**
     * 配置了这个Bean以后，从前端传递过来的密码将被加密
     *
     * @return PasswordEncoder实现类对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
		        .loginPage("/authentication/require")
		        .loginProcessingUrl("/authentication/form")
		//        .successHandler(lemonAuthenticationSuccessHandler)
		//        .failureHandler(lemonAuthenticationFailureHandler)
		        .permitAll()
	        	.and()
	        .authorizeRequests()
		        .antMatchers("/authentication/require")
		        .permitAll()
		        .anyRequest()
		        .authenticated()
		        .and()
	        .csrf().disable();
	}

    
}
