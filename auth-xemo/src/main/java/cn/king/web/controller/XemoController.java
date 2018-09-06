package cn.king.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.king.auth.core.domain.AuthUser;
import cn.king.auth.core.domain.AuthUserRepository;

/**
 * @author wlh by 2018-09-06
 *
 */
@RestController
public class XemoController {

	@Autowired
	private AuthUserRepository authUserRepository;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello spring security!";
	}
	
	
	@GetMapping("/user")
	public List<AuthUser> getUses() {
		return authUserRepository.findAll();
	}
	
}
