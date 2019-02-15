package xyz.yuanwl.demo.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface SendToBinder {

    String INPUT = "input";
    @Input
    SubscribableChannel input();

    String OUTPUT = "output";
    @Output
    MessageChannel output();

}