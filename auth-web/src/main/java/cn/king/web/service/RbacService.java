package cn.king.web.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
* 基于RBAC(role-Based-access control)权限控制
* 返回权限验证的接口
*/
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication auth);
}