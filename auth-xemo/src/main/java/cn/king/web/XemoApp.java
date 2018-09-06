package cn.king.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class XemoApp {

	public static void main(String[] args) {
		SpringApplication.run(XemoApp.class, args);
	}

}
