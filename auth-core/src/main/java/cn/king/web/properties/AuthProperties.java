/**
 * 
 */
package cn.king.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


/**
 * @author wlh by 2018-09-06
 *
 */
@Data
@ConfigurationProperties(prefix = "king.auth")
public class AuthProperties {
	
	private WebProperty web = new WebProperty();
	
	private CodeProperty code = new CodeProperty();


	
}
