package xyz.yuanwl.demo.spring.cloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring-cloud-stream 测试-消费者2
 * @author yuanwl
 * @date 2019-02-15 10:42
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamConsumer2App {
	public static void main(String[] args) {
		SpringApplication.run(StreamConsumer2App.class, args);
	}
}
