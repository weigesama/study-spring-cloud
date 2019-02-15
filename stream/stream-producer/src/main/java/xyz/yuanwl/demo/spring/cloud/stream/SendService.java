package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * 通过 output 通道发送消息
 * @author yuanwl
 * @date 2019-02-14 21:25
 */
@EnableBinding(Source.class) /* 这个注解给我们绑定消息通道的，Source是Stream给我们提供的，
 * 可以点进去看源码，可以看到output和input和配置文件中的output、input是对应的 */
@Slf4j
public class SendService {
	@Autowired
	private Source source;

	public void sendMsg(Msg msg) {
		log.info("生产者发送消息：【{}】", msg);
		source.output().send(MessageBuilder.withPayload(msg).build());
	}
}
