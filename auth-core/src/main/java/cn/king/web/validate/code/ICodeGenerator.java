package cn.king.web.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author wlh by 2018-09-07
 *
 */
public interface ICodeGenerator {

    /**
     * 生成图片验证码
     *
     * @param request 请求
     * @return ImageCode实例对象
     */
    ImgCode generate(ServletWebRequest request);
}
