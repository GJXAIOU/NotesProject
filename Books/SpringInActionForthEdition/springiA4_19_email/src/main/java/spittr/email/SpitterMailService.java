package spittr.email;

import javax.mail.MessagingException;

import spittr.domain.Spittle;

public interface SpitterMailService {

  public abstract void sendSimpleSpittleEmail(String to, Spittle spittle);

  public abstract void sendSpittleEmailWithAttachment(String to, Spittle spittle)
      throws MessagingException;
  
  public abstract void sendRichEmail(String to, Spittle spittle)
		  throws MessagingException;
  
  public abstract void sendRichEmailWithVelocity(String to, Spittle spittle)
		  throws MessagingException;
  
  public abstract void sendRichEmailWithThymeleaf(String to, Spittle spittle)
		  throws MessagingException;

}