package xyz.yuanwl.demo.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>EurekaClient
 *
 * @author Yuanwl
 * @date 2019/1/19 20:16
 */
@SpringBootApplication
/*
 * SpringCLoud中的“Discovery Service”有多种实现，比如：eureka, consul, zookeeper。
 * 1、@EnableDiscoveryClient 注解是基于spring-cloud-commons依赖，并且在classpath中实现；
 * 2、@EnableEurekaClient 注解是基于spring-cloud-netflix依赖，只能为eureka作用；
 * 其实用更简单的话来说，就是如果选用的注册中心是eureka，那么就推荐 @EnableEurekaClient，
 * 如果是其他的注册中心，那么推荐使用 @EnableDiscoveryClient ——个人推荐用后者，因为更换注册中心时不用换注解
 */
//@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaClientApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApp.class, args);
	}
}
