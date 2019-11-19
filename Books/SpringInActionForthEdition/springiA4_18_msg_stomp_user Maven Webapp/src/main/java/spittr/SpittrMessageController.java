package spittr;

import java.security.Principal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class SpittrMessageController {

//	private static final Logger logger = LoggerFactory.getLogger(SpittrMessageController.class);

	private SpittleRepository spittleRepo;
	private SpittleFeedService feedService;

	@Autowired
	public SpittrMessageController(SpittleRepository spittleRepo, SpittleFeedService feedService) {
		this.spittleRepo = spittleRepo;
		this.feedService = feedService;
	}

	//FIXME 为何接收不到消息
	@MessageMapping("/spittle")
	@SendToUser("/queue/notifications")
	public Notification handleSpittle(Principal principal, SpittleForm form) {
		System.out.println("访问spittle");
		Spittle spittle = new Spittle(principal.getName(), form.getText(), new Date());
		spittleRepo.save(spittle);
		feedService.broadcastSpittle(spittle);
		return new Notification("Saved Spittle for user: " + principal.getName());
	}

//	@MessageExceptionHandler
//	@SendToUser("/queue/errors")
//	public Exception handleExceptions(Exception e) {
//		logger.error("Error handling message:" + e.getMessage());
//		return e;
//	}

	/*
	 * 在使用Spring和STOMP消息功能的时候，我们有三种方式利用认证用户：
	 * 
	 * @MessageMapping和@SubscribeMapping标注的方法能够使用Principal来获取认证用户；
	 * 
	 * @MessageMapping、@SubscribeMapping和@MessageException方法返回的值能够以消息的形式发送给认证用户；
	 * SimpMessagingTemplate能够发送消息给特定用户
	 */
}
