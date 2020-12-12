package spittr.email;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icegreen.greenmail.spring.GreenMailBean;

import spittr.config.MailConfig;
import spittr.domain.Spitter;
import spittr.domain.Spittle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailConfig.class)
public class SpitterMailServiceImplTest {

	@Autowired
	private SpitterMailService mailService;

	@Autowired
	private GreenMailBean mailServer;
	
	private String toEmail = "craig@habuma.com";

	@Test
	public void sendSimpleSpittleEmail() throws Exception {
		Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "c@habuma.com", true);
		Spittle spittle = new Spittle(1L, spitter, "Hiya!", new Date());
		mailService.sendSimpleSpittleEmail("craig@habuma.com", spittle);

		MimeMessage[] receivedMessages = mailServer.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		assertEquals("New spittle from Craig Walls", receivedMessages[0].getSubject());
		assertEquals("Craig Walls says: Hiya!", ((String) receivedMessages[0].getContent()).trim());
		Address[] from = receivedMessages[0].getFrom();
		assertEquals(1, from.length);
		assertEquals("noreply@spitter.com", ((InternetAddress) from[0]).getAddress());
		assertEquals("craig@habuma.com",
				((InternetAddress) receivedMessages[0].getRecipients(RecipientType.TO)[0]).getAddress());
	}

	@Test
	public void sendSpittleEmailWithAttachment() throws Exception {
		mailService.sendSpittleEmailWithAttachment(toEmail, spittle());
		receiveTest();
	}
	
	@Test
	public void sendRichEmail() throws Exception {
		mailService.sendRichEmail(toEmail, spittle());
		receiveTest();
	}
	
	@Test
	public void sendRichEmailWithVelocity() throws Exception {
		mailService.sendRichEmailWithVelocity(toEmail, spittle());
		receiveTest();
	}
	
	//FIXME 解析错误
	@Test
	public void sendRichEmailWithThymeleaf() throws Exception {
		mailService.sendRichEmailWithThymeleaf(toEmail, spittle());
//		receiveTest();
	}
	
	public Spittle spittle() {
		Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "service@bookstore.com", true);
		return new Spittle(1L, spitter, "Hiya!", new Date());
	}

	public void receiveTest() throws Exception {
		MimeMessage[] receivedMessages = mailServer.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		assertEquals("New spittle from Craig Walls", receivedMessages[0].getSubject());
		Address[] from = receivedMessages[0].getFrom();
		assertEquals(1, from.length);
		assertEquals("noreply@spitter.com", ((InternetAddress) from[0]).getAddress());
		assertEquals(toEmail,
				((InternetAddress) receivedMessages[0].getRecipients(RecipientType.TO)[0]).getAddress());
	
		MimeMultipart multipart = (MimeMultipart) receivedMessages[0].getContent();
		Part part = null;
		for(int i=0;i<multipart.getCount();i++) {
			part = multipart.getBodyPart(i);
			System.out.println(part.getFileName());
			System.out.println(part.getSize());
		}
	}
}
