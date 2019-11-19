package spittr.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import spittr.domain.Spittle;

@Component
public class SpitterMailServiceImpl implements SpitterMailService {

	private JavaMailSender mailSender;

	@Autowired
	public SpitterMailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private SpringTemplateEngine thymeleaf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see spittr.email.SpitterMailService#sendSimpleSpittleEmail(java.lang.String,
	 * spittr.domain.Spittle)
	 */
	@Override
	public void sendSimpleSpittleEmail(String to, Spittle spittle) {
		SimpleMailMessage message = new SimpleMailMessage();
		String spitterName = spittle.getSpitter().getFullName();
		message.setFrom("noreply@spitter.com");
		message.setTo(to);
		message.setSubject("New spittle from " + spitterName);
		message.setText(spitterName + " says: " + spittle.getText());
		mailSender.send(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * spittr.email.SpitterMailService#sendSpittleEmailWithAttachment(java.lang.
	 * String, spittr.domain.Spittle)
	 */
	@Override
	public void sendSpittleEmailWithAttachment(String to, Spittle spittle) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String spitterName = spittle.getSpitter().getFullName();
		helper.setFrom("noreply@spitter.com");
		helper.setTo(to);
		helper.setSubject("New spittle from " + spitterName);
		helper.setText(spitterName + " says: " + spittle.getText());
		ClassPathResource couponImage = new ClassPathResource("/collateral/coupon.jpg");
		helper.addAttachment("Coupon.jgp", couponImage);
		mailSender.send(message);
	}
	
	/**
	* <p>描述：富文本email</p>
	* @param to
	* @param spittle
	* @throws MessagingException
	 */
	public void sendRichEmail(String to, Spittle spittle) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String spitterName = spittle.getSpitter().getFullName();
		helper.setFrom("noreply@spitter.com");
		helper.setTo(to);
		helper.setSubject("New spittle from " + spitterName);
		helper.setText("<html><body><img src='cid:spitterLogo'>"
				+ "<h4>"+spitterName+" says:</h4>"
				+ "<i>"+spittle.getText()+"</i>"
				+ "</body></html>", true);
		ClassPathResource couponImage = new ClassPathResource("/collateral/coupon.jpg");
		helper.addInline("spitterLogo", couponImage);
		mailSender.send(message);
	}
	
	/**
	* <p>描述：采用Velocity模板的富文本email</p>
	* @param to
	* @param spittle
	* @throws MessagingException
	 */
	public void sendRichEmailWithVelocity(String to, Spittle spittle) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String spitterName = spittle.getSpitter().getFullName();
		helper.setFrom("noreply@spitter.com");
		helper.setTo(to);
		helper.setSubject("New spittle from " + spitterName);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("spitterName", spitterName);
		model.put("spittleText", spittle.getText());
		String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"emailTemplate.vm", "UTF-8", model);
		
		helper.setText(emailText, true);
		ClassPathResource couponImage = new ClassPathResource("/collateral/coupon.jpg");
		helper.addInline("spitterLogo", couponImage);
		mailSender.send(message);
	}
	
	/**
	* <p>描述：采用Thymeleaf模板的富文本email</p>
	* @param to
	* @param spittle
	* @throws MessagingException
	* @author lzc
	 */
	public void sendRichEmailWithThymeleaf(String to, Spittle spittle) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String spitterName = spittle.getSpitter().getFullName();
		helper.setFrom("noreply@spitter.com");
		helper.setTo(to);
		helper.setSubject("New spittle from " + spitterName);
		
		Context context = new Context();
		context.setVariable("spitterName", spitterName);
		context.setVariable("spittleText", spittle.getText());
		String emailText = thymeleaf.process("emailTemplate.html", context);
		
		helper.setText(emailText, true);
		ClassPathResource couponImage = new ClassPathResource("/collateral/coupon.jpg");
		helper.addInline("spitterLogo", couponImage);
		mailSender.send(message);
	}

}
