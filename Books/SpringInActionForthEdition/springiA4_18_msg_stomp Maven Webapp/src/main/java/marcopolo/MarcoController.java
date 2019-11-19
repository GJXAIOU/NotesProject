package marcopolo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MarcoController {

  private static final Logger logger = LoggerFactory
      .getLogger(MarcoController.class);

  /*
   * 处理发往“/app/marco”目的地的消息，其中“/app”是隐含的：在WebSocketStompConfig中配置了
   */
  @MessageMapping("/marco")
//  @SendTo("/topic/shout")//经过代理
  /*
   * 处理对“/app/marco”目的地的订阅，其中“/app”是隐含的：在WebSocketStompConfig中配置了；
   * 直接发送，不经过代理
   */
//  @SubscribeMapping("/marco")
  public Shout handleShout(Shout incoming) {
    logger.info("Received message: " + incoming.getMessage());

    try { Thread.sleep(2000); } catch (InterruptedException e) {}
    
    Shout outgoing = new Shout();
    outgoing.setMessage("Polo!");
    
    return outgoing;
  }
  
  /*
   * 	消息转换器 								描　　述
   * ByteArrayMessageConverter			实现MIME类型为“ application/octet-stream ”的消息
   * 									与 byte[] 之间的相互转换
   * MappingJackson2MessageConverter	实现MIME类型为“ application/json ”的消息
   * 									与Java对象之间的相互转换
   * StringMessageConverter				实现MIME类型为“ text/plain ”的消息
   * 									与 String之间的相互转换
   */

}
