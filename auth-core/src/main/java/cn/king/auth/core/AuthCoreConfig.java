package cn.king.auth.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.king.auth.core.properties.AuthProperties;

/**
 * @author wlh by 2018-09-06
 *
 */
@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class AuthCoreConfig {
	
	

}
