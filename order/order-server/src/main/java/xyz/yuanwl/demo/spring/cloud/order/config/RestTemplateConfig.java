package xyz.yuanwl.demo.spring.cloud.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2019/1/26 15:52
 */
@Component
public class RestTemplateConfig {
	@Bean
	/*
	 * 加一个 @LoadBalanced 注解，就能让这个RestTemplate在请求时拥有客户端负载均衡的能力，
	 * 详情：https://blog.csdn.net/xiao_jun_0820/article/details/78917215
	 */
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
