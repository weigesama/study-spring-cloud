package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

//@EnableBinding(Processor.class)
@Slf4j
public class TransFormService {

	/**
	 * 消息中转，从 Processor.INPUT 通道接收，转发到 Processor.OUTPUT 通道
	 * @param msg 
	 * @return xyz.yuanwl.demo.spring.cloud.stream.Msg
	 * @author Yuanwl
	 * @date 2019-02-15 14:27:51
	 * @version v1.0.0
	 */
	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Msg transform(Msg msg) {
		log.info("消息中转站收到消息：【{}】，现在开始中转...", msg);
		msg.setTitle(msg.getTitle() + ", 转发人：消费者1");
		return msg;
	}

}