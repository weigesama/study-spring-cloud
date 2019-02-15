package xyz.yuanwl.demo.spring.cloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring-cloud-stream 测试-生产者
 *
 * @author yuanwl
 * @date 2019-02-11 21:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamProducerApp {
	public static void main(String[] args) {
		SpringApplication.run(StreamProducerApp.class, args);
	}
}
