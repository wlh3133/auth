package cn.king.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("authFailureHandler")
public class AuthFailureHandler implements AuthenticationFailureHandler {


  @Autowired
  private ObjectMapper objectMapper; //Spring启动时自动注册的JSON工具类

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    log.info("--- login failed by:{}", exception.getMessage());

    //这里可以根据实际情况，来确定是跳转到页面或者json格式。
    //如果是返回json格式
    Map<String,String> map=new HashMap<>();
    map.put("code", "201");
    map.put("msg", "登录失败");
    response.setContentType("application/json; charset=UTF-8");
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.getWriter().write(objectMapper.writeValueAsString(map));
  }
}