package xyz.yuanwl.demo.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yuanwl
 * @date 2019-03-01 20:21
 */
//这是组合注解, 其中 @EnableCircuitBreaker 是跟 hystrix 有关的--启用断路器
@SpringCloudApplication
//扫描本项目的类和 FeignClient 的降级实现类
@ComponentScan(basePackages = "xyz.yuanwl.demo.spring.cloud")
/*启用feign客户端，扫描标记了 @FeignClient 注解的类。
	注意加上basePackages属性才可以扫描到product项目的client包*/
@EnableFeignClients(basePackages = "xyz.yuanwl.demo.spring.cloud.product.client")
@EnableHystrixDashboard
public class HystrixApp {
	public static void main(String[] args) {
		SpringApplication.run(HystrixApp.class, args);
	}
}
