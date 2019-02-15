package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * 通过 myOutput 通道发送消息
 * @author yuanwl
 * @date 2019-02-14 21:25
 */
//@EnableBinding(MySource.class)
@Slf4j
public class MySendService {
	@Autowired
	private MySource source;

	public void sendMsg(Msg msg) {
		log.info("生产者发送消息：{}", msg);
		source.myOutput().send(MessageBuilder.withPayload(msg).build());
	}
}
