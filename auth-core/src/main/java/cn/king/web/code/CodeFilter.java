package cn.king.web.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author wlh by 2018-09-07
 *
 */
public class CodeFilter extends OncePerRequestFilter {


//	@Autowired
//	private AuthenticationFailureHandler authFailureHandler;
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (StringUtils.equals("/auth/form", request.getRequestURI()) 
				&& StringUtils.equalsIgnoreCase("post", request.getMethod())) {
            try {
                validate(new ServletWebRequest(request));
            } catch (Exception e) {
//            	authFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
		}
		
		filterChain.doFilter(request, response);
	}
	
    /**
     * 验证码校验逻辑
     *
     * @param request 请求
     * @throws ServletRequestBindingException 请求异常
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 从session中获取图片验证码
        ImgCode imageCodeInSession = (ImgCode) sessionStrategy.getAttribute(request, CodeController.SESSION_KEY);
        // 从请求中获取用户填写的验证码
        String imageCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imgCode");
        if (StringUtils.isBlank(imageCodeInRequest)) {
//            throw new ValidateCodeException("验证码不能为空");
        }
        if (null == imageCodeInSession) {
//            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCodeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, CodeController.SESSION_KEY);
//            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(imageCodeInRequest, imageCodeInSession.getCode())) {
//            throw new ValidateCodeException("验证码不匹配");
        }
        // 验证成功，删除session中的验证码
        sessionStrategy.removeAttribute(request, CodeController.SESSION_KEY);
    }

}
