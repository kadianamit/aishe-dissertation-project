package org.nicexam.authorizationservice.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.nicexam.authorizationservice.utility.CaptchaUtil;
import org.nicexam.authorizationservice.utility.EncryptionDecryptionUtil;
import org.nicexam.authorizationservice.utility.ReturnResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/reg/auth/captcha")
public class CaptchaVerificationController {
	@RequestMapping(value = "/verifycaptcha", method = RequestMethod.GET)
	public ResponseEntity<ReturnResponse> getProfileById(@RequestParam("captchaText") String captchaText,
			@RequestParam("data") String encodeCaptcha, HttpSession session) throws Exception {
		String decodeCaptcha = EncryptionDecryptionUtil.getDecryptedString(encodeCaptcha);
		if (decodeCaptcha == null || !decodeCaptcha.equals(captchaText)) {
			return new ResponseEntity<ReturnResponse>(
					new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Captcha Invalid"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<ReturnResponse>(new ReturnResponse(HttpStatus.OK.value(), "Captcha Valid"),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String fetchCaptcha() {
		String FILE_TYPE = "jpeg";
		String captchaStr = CaptchaUtil.generateCaptchatMethod(6).toUpperCase();
		String output = "";
		try {
			int width = 250;
			int height = 80;
			Color bg = new Color(0, 255, 255);
			Color fg = new Color(0, 0, 0);
			Font font = new Font("Arial", Font.BOLD, 40);
			BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.OPAQUE);
			Graphics g = cpimg.createGraphics();
			g.setFont(font);
			g.setColor(bg);
			g.fillRect(0, 0, width, height);
			g.setColor(fg);
			g.drawString(captchaStr, 30, 55);
			String captchaEncode = EncryptionDecryptionUtil.getEncryptedString(captchaStr);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(cpimg, FILE_TYPE, bos);
			bos.close();
			byte[] image = bos.toByteArray();
			String base64 = "data:image/jpeg;base64,"
					+ new String(org.apache.commons.codec.binary.Base64.encodeBase64(image), "UTF-8");
			output = "{\"capcha\":\"" + base64 + "\", \"data\":\"" + captchaEncode + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

}
