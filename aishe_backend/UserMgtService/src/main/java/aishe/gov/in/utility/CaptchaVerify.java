package aishe.gov.in.utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CaptchaVerify extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setDateHeader("Expires", 10000);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		response.setContentType("text");
		PrintWriter out = response.getWriter();

		String encodeCaptcha = request.getParameter("data");
		String captchaText = request.getParameter("captchaText");
		String decodeCaptcha = EncryptionDecryptionUtil.getDecryptedString(encodeCaptcha);
		if (decodeCaptcha == null || !decodeCaptcha.equals(captchaText)) {
			decodeCaptcha = "";
			out.print("Captcha Invalid");
			out.flush();
		} else {
			out.print("Captcha Valid");
			out.flush();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}