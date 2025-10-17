package aishe.gov.in.utility;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class CaptchaServlet extends HttpServlet {

//	@Autowired
//	AisheDao aisheDao;
	
	
    public static final String FILE_TYPE = "jpeg";      
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
//     response.setHeader("Cache-Control", "no-cache");
       response.setDateHeader("Expires", 10000);
       response.setHeader("Pragma", "no-cache");
       response.setDateHeader("Max-Age", 0);
       response.setContentType("application/json");
//       response.setContentType("image/jpeg");
       MultiValueMap<String, Object> body  = new LinkedMultiValueMap();
       String captchaStr=CaptchaUtil.generateCaptchatMethod2(6).toUpperCase();      
       try {
           int width=250;         int height=80;                
           Color bg = new Color(0,255,255);
//         Color bg = new Color(255, 255, 255);
//           Color fg = new Color(0,100,0);                
           Color fg = new Color(0,0,0);  
           Font font = new Font("Arial", Font.BOLD, 40);
           BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
           Graphics g = cpimg.createGraphics();
           g.setFont(font);
           g.setColor(bg);
           g.fillRect(0, 0, width, height);
           g.setColor(fg);
           g.drawString(captchaStr,30,55);
           HttpSession session = request.getSession(true);
           session.setAttribute("CAPTCHA",  captchaStr );
           String captcha=(String)session.getAttribute("CAPTCHA");
           System.out.println(captcha);
           String captchaEncode =  EncryptionDecryptionUtil.getEncryptedString(captcha);
//           System.out.println("Encode->Decode : "+EncryptionDecryptionUtil.getDecryptedString(captchaEncode));
		
//			OutputStream outputStream = response.getOutputStream();
//			  response.addHeader("CAPTCHA", captchaStr); response.setHeader("CAPTCHATwo",
//			  captchaStr); ImageIO.write(cpimg, FILE_TYPE, outputStream);
//			  outputStream.close();
			
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           ImageIO.write(cpimg,FILE_TYPE, bos);
           bos.close();
           byte[] image = bos.toByteArray();
           String base64 = "data:image/jpeg;base64," + new String(org.apache.commons.codec.binary.Base64.encodeBase64(image), "UTF-8");
           PrintWriter out = response.getWriter();
           out.print("{\"capcha\":\""+base64+"\", \"data\":\""+captchaEncode+"\"}");
           out.flush();
//           
       } catch (Exception e) {
               e.printStackTrace();
       }
   }

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
       doPost(request, response);
   }
}