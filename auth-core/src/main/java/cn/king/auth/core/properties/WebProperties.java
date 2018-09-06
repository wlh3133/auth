package cn.king.auth.core.properties;

/**
 * @author wlh by 2018-09-06
 *
 */
public class WebProperties {
	
    private String loginPage = "/login.html";

    private boolean json = true;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public boolean isJson() {
		return json;
	}

	public void setJson(boolean json) {
		this.json = json;
	}
    
    
}
