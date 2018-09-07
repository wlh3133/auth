package cn.king.web.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author wlh by 2018-09-07
 *
 */
@Data
public class ImgCode {

	private BufferedImage image;
	private String code;
	private LocalDateTime expireTime;
	
	public ImgCode(BufferedImage image, String code, LocalDateTime expireTime) {
		this.image = image;
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public ImgCode(BufferedImage image, String code, Long seconds) {
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(seconds);
	}
	
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
