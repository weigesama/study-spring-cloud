package xyz.yuanwl.demo.spring.cloud.stream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

	@Autowired
	private SendService sendService;

//	@Autowired
//	private MySendService sendService;

	@RequestMapping("/send/{msg}")
	public void send(@PathVariable("msg") String msg) {
		if (StringUtils.isBlank(msg)) {
			msg = "测试消息";
		}
		sendService.sendMsg(new Msg().setTitle("发送人：生产者").setContent(msg));
	}
}