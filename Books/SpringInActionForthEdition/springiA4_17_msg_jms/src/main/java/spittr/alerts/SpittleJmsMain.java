package spittr.alerts;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spittr.domain.Spittle;

public class SpittleJmsMain {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/messaging.xml");
    AlertService alertService = context.getBean(AlertService.class);
    
    Spittle spittle = new Spittle(3L, null, "Hello333", new Date());
    alertService.sendSpittleAlert(spittle);
    
  }

}
