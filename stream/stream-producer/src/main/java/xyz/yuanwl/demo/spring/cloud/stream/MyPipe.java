package xyz.yuanwl.demo.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author yuanwl
 * @date 2019-02-15 21:27
 */
public interface MyPipe {
	String INPUT = "output";
	@Input(INPUT)
	SubscribableChannel input();
}
