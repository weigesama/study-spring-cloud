package xyz.yuanwl.demo.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author yuanwl
 * @date 2019-02-15 14:07
 */
public interface MySource {
	@Output
	MessageChannel myOutput();
}
