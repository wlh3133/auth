package cn.king.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wlh by 2018-09-06
 *
 */
@RestController
public class XemoController {


	
	@GetMapping("/hello")
	public String hello() {
		return "Hello spring security!";
	}
	
	
	@GetMapping("/user/{id}")
	public String getUses(@PathVariable String id) {
		return "--- User is " + id;
	}
	
}
