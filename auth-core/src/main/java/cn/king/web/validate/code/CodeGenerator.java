package cn.king.web.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import cn.king.web.properties.AuthProperties;
import lombok.Data;

/**
 * @author wlh by 2018-09-07
 *
 */
@Data
public class CodeGenerator implements ICodeGenerator {

	@Autowired
	private AuthProperties properties;

	@Override
	public ImgCode generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", properties.getCode().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				properties.getCode().getHeight());
		// 在内存中创建图象
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		// 设定背景颜色
//		g.setColor(Color.WHITE); 
		g.setColor(randColor(200, 250)); 
		g.fillRect(0, 0, width, height);
		// 设定边框颜色
		g.drawRect(0, 0, width - 1, height - 1);

		final Random random = new Random();
		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		final int count = 100;
		final int lineWidth = 20;
		for (int i = 0; i < count; i++) {
			g.setColor(randColor(150, 200)); // ---3

			final int x = random.nextInt(width  - 1) + 1; // 保证画在边框之内
			final int y = random.nextInt(height  - 1) + 1;
			final int xl = random.nextInt(lineWidth);
			final int yl = random.nextInt(lineWidth);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		final int length = properties.getCode().getLength();
		final String resultCode = getRandCode(length);
		for (int i = 0; i < length; i++) {
			// 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			 g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(130)));
			// g.setColor(Color.BLACK);
			// 设置字体样式
			g.setFont(new Font("Times New Roman", Font.ITALIC, height-5));
			// 设置字符，字符间距，上边距
			g.drawString(String.valueOf(resultCode.charAt(i)), (width/length * i) + 5, height-6);
		}
		g.dispose();
		return new ImgCode(image, resultCode, properties.getCode().getSeconds());
	}

	private String getRandCode(int length) {
		final int codeType = properties.getCode().getType();
		
		switch (codeType) {
		case 1:
			return CodeEnum.NUMBER_CHAR.getStr(length);
		case 2:
			return CodeEnum.LOWER_CHAR.getStr(length);
		case 3:
			return CodeEnum.UPPER_CHAR.getStr(length);
		case 4:
			return CodeEnum.LETTER_CHAR.getStr(length);
		case 5:
			return CodeEnum.ALL_CHAR.getStr(length);
		default:
			return CodeEnum.NUMBER_CHAR.getStr(length);
		}

	}

	// 取得给定范围随机颜色
	private Color randColor(int fc, int bc) {
		final Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		final int r = fc + random.nextInt(bc - fc);
		final int g = fc + random.nextInt(bc - fc);
		final int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}

enum CodeEnum {

	ALL_CHAR("0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ"),

	LETTER_CHAR("abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ"),

	LOWER_CHAR("abcdefghijkmnopqrstuvwxyz"),

	NUMBER_CHAR("0123456789"),

	UPPER_CHAR("ABCDEFGHJKLMNOPQRSTUVWXYZ");

	private String charStr;

	private CodeEnum(final String charStr) {
		this.charStr = charStr;
	}

	public String getStr(final int codeLength) {
		final StringBuffer sb = new StringBuffer();
		final Random random = new Random();
		final String sourseStr = getCharStr();

		for (int i = 0; i < codeLength; i++) {
			sb.append(sourseStr.charAt(random.nextInt(sourseStr.length())));
		}
		return sb.toString();
	}

	public String getCharStr() {
		return charStr;
	}

}
