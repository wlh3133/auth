package cn.king.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.king.web.properties.AuthProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy strategy = new DefaultRedirectStrategy();
	private static final String HTML = ".html";
	@Autowired
	private AuthProperties authProperties;
	
    /**
     * 当需要进行身份认证的时候跳转到此方法
     *
     * @param request  请求
     * @param response 响应
     * @return 将信息以JSON形式返回给前端
     */
	@RequestMapping("/auth/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String authRequire(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取请求的来源
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String redirectUrl = savedRequest.getRedirectUrl();
			 log.info("--- Request Url is: \"{}\"", redirectUrl);

			// 处理来自html的请求，直接跳到登陆页面
			if (StringUtils.endsWithIgnoreCase(redirectUrl, HTML)) {
				String url = authProperties.getWeb().getLoginPage();
				log.info("--- Redirect Url is: \"{}\"", url);
				strategy.sendRedirect(request, response, url);
			}
		}
		// 非html请求则返回一个Json信息
		return "{\"status\":403,\"msg\": \"访问的服务需要权限认证!请先登陆\"}";
	}
}