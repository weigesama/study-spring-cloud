package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * 消息接收、自动确认回复端
 * @author Yuanwl
 * @date 2019-02-15 10:46:50
 * @version v1.0.0
 */
@EnableBinding(SendToBinder.class)
@Slf4j
public class SendToService {

    @StreamListener("input")
    @SendTo("output")
    public Msg receive(Msg msg){
        log.info("消费者收到消息：{}", msg);
        return new Msg().setTitle("发送人：消费者2").setContent("消息确认回复");
    }

}