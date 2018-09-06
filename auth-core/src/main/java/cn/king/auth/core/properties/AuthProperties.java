/**
 * 
 */
package cn.king.auth.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author wlh by 2018-09-06
 *
 */
@ConfigurationProperties(prefix = "king.auth")
public class AuthProperties {
	
	private WebProperties webProperties;

	public WebProperties getWebProperties() {
		return webProperties;
	}

	public void setWebProperties(WebProperties webProperties) {
		this.webProperties = webProperties;
	}
	
	
}
