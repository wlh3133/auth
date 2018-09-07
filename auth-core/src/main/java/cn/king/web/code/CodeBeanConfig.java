package cn.king.web.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.king.web.properties.AuthProperties;

/**
 * @author wlh by 2018-09-07
 *
 */

@Configuration
public class CodeBeanConfig {

	private final AuthProperties authProperties;
	
	@Autowired
	public CodeBeanConfig(AuthProperties authProperties) {
		this.authProperties = authProperties;
	}
	
	@Bean
	@ConditionalOnMissingBean(name="imgCodeGenerator")
	public ICodeGenerator imgCodeGenerator() {
		ImgCodeGenerator imgCodeGenerator = new ImgCodeGenerator();
		imgCodeGenerator.setAuthProperties(authProperties);
		return imgCodeGenerator;
	}
	
}
