package aishe.gov.in.controller;

import aishe.gov.in.mastersvo.CaptchaResponse;
import aishe.gov.in.utility.CaptchaUtil;
import aishe.gov.in.utility.CaptchaValidatorComponent;
import aishe.gov.in.utility.EncryptionDecryptionUtil;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class CaptchaController {

    @Autowired
    private CaptchaValidatorComponent validatorComponent;


    @RequestMapping(value = "/verifycaptcha", method = RequestMethod.GET)
    public ResponseEntity<String> getProfileById(@RequestParam("captchaText") String captchaText, @RequestParam("data") String encodeCaptcha, HttpSession session) throws Exception {
        validatorComponent.validateCaptcha(captchaText);
        String decodeCaptcha = EncryptionDecryptionUtil.getDecryptedString(encodeCaptcha);
        if (decodeCaptcha == null || !decodeCaptcha.equals(captchaText)) {
            decodeCaptcha = "";
            return ResponseEntity.ok("Captcha Invalid");
        } else
            return ResponseEntity.ok("Captcha Valid");
    }

    @RequestMapping(value = "/verifycaptchaaishe", method = RequestMethod.GET)
    public Map<String, String> verifyVaptchaAishe(@RequestParam("captchaText") String captchaText,
                                                  @RequestParam("data") String encodeCaptcha, HttpSession session) throws Exception {
        validatorComponent.validateCaptcha(captchaText);

        String decodeCaptcha = EncryptionDecryptionUtil.getDecryptedString(encodeCaptcha);
        Map<String, String> message = new HashMap<String, String>();
        if (decodeCaptcha == null || !decodeCaptcha.equals(captchaText)) {
            decodeCaptcha = "";
            message.put("message", "Captcha Invalid");
            ;
            return message;
        } else
            message.put("message", "Captcha Valid");
        return message;
    }

    @GetMapping(value = "/getCaptcha",produces = MediaType.APPLICATION_JSON_VALUE)
    public CaptchaResponse fetchCaptcha() {
        String FILE_TYPE = "jpeg";
        String captchaStr = CaptchaUtil.generateCaptchatMethod2(6).toUpperCase();
        String output = "";
        CaptchaResponse response = new CaptchaResponse();
        try {
            int width = 250;
            int height = 80;
            Color bg = new Color(218, 217, 223);//0,255,255
            Color fg = new Color(0, 0, 0);
            Font font = new Font("Chiller", Font.BOLD, 40);
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
            response.setCapcha(base64);
            response.setData(captchaEncode);
            //output = "{\"capcha\":\"" + base64 + "\", \"data\":\"" + captchaEncode + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}