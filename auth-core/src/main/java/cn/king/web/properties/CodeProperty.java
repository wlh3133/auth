package cn.king.web.properties;

import lombok.Data;

/**
 * @author wlh by 2018-09-07
 *
 */
@Data
public class CodeProperty {

	private int width = 45;
	private int height = 130;
	private int length = 4;
	//过期时间
	private Long seconds = 60L * 2; 
	//验证码类型：1-数字，2-小写字母，3-大写字符，4-字符,5-混合字符串
	private int type = 5; 
	
}
