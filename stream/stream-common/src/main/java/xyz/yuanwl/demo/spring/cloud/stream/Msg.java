package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息载体
 * @author yuanwl
 * @date 2019-02-14 15:02
 */
@Data
@Accessors(chain = true)
public class Msg {
	private String title;
	private String content;
}
