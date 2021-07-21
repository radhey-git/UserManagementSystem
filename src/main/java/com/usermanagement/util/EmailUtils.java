package com.usermanagement.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.usermanagement.exception.SMTPException;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String subject, String body) throws SMTPException {
		boolean isTrue = false;
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(body,true);
			mailSender.send(mimeMessageHelper.getMimeMessage());
			isTrue = true;

		} catch (Exception e) {
			throw new SMTPException(e.getMessage());
		}

		return isTrue;
	}
}
