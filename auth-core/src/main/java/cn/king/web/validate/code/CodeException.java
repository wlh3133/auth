package cn.king.web.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author wlh by 2018-09-07
 *
 */
public class CodeException extends AuthenticationException {

	private static final long serialVersionUID = -7629736693184798374L;

	/**
	 * @param msg
	 */
	public CodeException(String msg) {
		super(msg);
	}

}
