package xyz.yuanwl.demo.spring.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 消息接收端
 * @author Yuanwl
 * @date 2019-02-15 10:46:50
 * @version v1.0.0
 */
//@EnableBinding(Source.class)
@Slf4j
public class ReceiveService {

    @StreamListener(Source.OUTPUT)
    public void receive(Msg msg){
        log.info("生产者收到消息：【{}】", msg);
    }

}