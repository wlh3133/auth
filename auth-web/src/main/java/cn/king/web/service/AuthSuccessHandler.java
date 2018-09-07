package cn.king.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


/**
* 自定义登录成功的处理逻辑
* 在现在的大多数应用中，一般都是前后端分离的，所以我们登录成功或失败都需要用json格式返回，
* 或者登录成功之后，跳转到某个具体的页面。
*/
@Slf4j
@Component("authSuccessHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {


  @Autowired
  private ObjectMapper objectMapper; //Spring启动时自动注册的JSON工具类

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
    log.info("--- {} login success", ((UserDetails)auth.getPrincipal()).getUsername());
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(auth));

  }

}