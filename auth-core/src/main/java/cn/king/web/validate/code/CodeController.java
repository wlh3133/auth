package cn.king.web.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import cn.king.web.properties.AuthProperties;
import lombok.Data;

/**
 * @author wlh by 2018-09-07
 *
 */
@RestController
public class CodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMG_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private ICodeGenerator codeGenerator;

		
	@GetMapping("/code/img")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 第一步：根据请求生成一个图形验证码对象
		ImgCode imgCode = codeGenerator.generate(new ServletWebRequest(request));
		// 第二步：将图形验证码对象存到session中,第一个参数可以从传 入的请求中获取session
		sessionStrategy.setAttribute(new ServletRequestAttributes(request), SESSION_KEY, imgCode);
		// 第三步：将生成的图片写到接口的响应中
		ImageIO.write(imgCode.getImage(), "JPEG", response.getOutputStream());
	}


}
