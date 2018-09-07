package cn.king.web.utils;

/**
 * @author wlh by 2018-09-07
 *
 */

public class JsonMsg {

	private Object message;
	
	public JsonMsg(Object message) {
		this.message = message;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

}
