package cn.king.web.properties;

import lombok.Data;

/**
 * @author wlh by 2018-09-06
 *
 */
@Data
public class WebProperties {
	
    private String loginPage = "/auth.html";

    private boolean json = true;

    
    
}
