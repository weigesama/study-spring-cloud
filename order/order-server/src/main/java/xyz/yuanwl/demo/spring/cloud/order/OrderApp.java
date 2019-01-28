package xyz.yuanwl.demo.spring.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "xyz.yuanwl.demo.spring.cloud.product.client") /*启用feign客户端，扫描标记了 @FeignClient 注解的类。
	注意加上basePackages属性才可以扫描到product项目的client包*/
public class OrderApp {
	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}
}
