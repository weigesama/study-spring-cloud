package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 消息接收端
 * @author Yuanwl
 * @date 2019-02-15 10:46:50
 * @version v1.0.0
 */
//@EnableBinding(Sink.class) /* stream给我们提供了Sink,Sink源码里面是绑定input的，
// * 要跟我们配置文件的input关联的 */
@Slf4j
public class ReceiveService {

    @StreamListener(Sink.INPUT)
    public void receive(Msg msg){
        log.info("消费者收到消息：{}", msg);
    }

}