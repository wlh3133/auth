/**
 * 
 */
package cn.king.auth.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cn.king.auth.core.domain.AuthUser;
import cn.king.auth.core.domain.AuthUserRepository;

/**
 * @author wlh by 2018-09-06
 *
 */
@Component
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserRepository authUserRepository;
	
	/**
	 * 获到UsernameNotFoundException异常会new一个新的BadCredentialsException异常抛出来，
	 * 那么最后捕获到并放入session中的就是这个BadCredentialsException异常。
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser user = authUserRepository.findByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("用户名不存在");
		}
		return user;
	}
}
