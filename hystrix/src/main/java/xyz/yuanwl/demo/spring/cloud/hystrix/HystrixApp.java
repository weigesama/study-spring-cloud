package xyz.yuanwl.demo.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author yuanwl
 * @date 2019-03-01 20:21
 */
//这是组合注解, 其中 @EnableCircuitBreaker 是跟 hystrix 有关的--启用断路器
@SpringCloudApplication
public class HystrixApp {
	public static void main(String[] args) {
		SpringApplication.run(HystrixApp.class, args);
	}
}
