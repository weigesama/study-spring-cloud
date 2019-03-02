package xyz.yuanwl.demo.spring.cloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 熔断演示
 *
 * @author yuanwl
 * @date 2019-03-01 20:25
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController2 {

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", 
					value = "true"), //启用熔断机制
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", 
					value = "10"), //请求数达到后才计算是否应该熔断
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",
					value = "60"), //错误率超过就打开断路器
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",
					value = "5000"), //打开断路器后进入休眠时间窗, 结束后半开断路器
	})
	@GetMapping("product/list/2")
	public String productList(@RequestParam("num") Integer num) throws Exception {
		if (num % 2 == 0) {
			return "success";
		}
		
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject("http://localhost:8100/list",
				String.class);
	}

	/**
	 * 默认降级方法
	 *
	 * @return java.lang.String
	 * @author Yuanwl
	 * @date 2019-03-01 20:59:44
	 * @version v1.0.0
	 */
	public String defaultFallback() {
		return "默认: 太拥挤了, 请稍后再试...";
	}
}
