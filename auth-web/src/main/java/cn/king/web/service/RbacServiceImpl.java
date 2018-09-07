package cn.king.web.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import ch.qos.logback.core.net.LoginAuthenticator;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
* 核心就是判断当前的用户所拥有的URL是否和当前访问的URL是否匹配。
*/
@Slf4j
@Component("rbacService")
public class RbacServiceImpl implements RbacService {
	
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        boolean has = false;
        Object userDetails = authentication.getPrincipal();
        //首先判断先当前用户是否是我们UserDetails对象
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails)userDetails).getUsername();
            log.info("--- {} request:", username);
            // 根据username去数据库读取用户所拥有权限的所有URL
            Set<String> urls = new HashSet<>();

            urls.add("/goods/**");

            // 注意这里不能用equal来判断，有些URL是有参数的，要用AntPathMatcher来比较
            for (String url : urls) {
                has = antPathMatcher.match(url, request.getRequestURI());
                log.info("--- url:{}, request-url:{}, is:{}", url, request.getRequestURI(), has);
                if (has) break;
            }
        }
        return has;
    }
}