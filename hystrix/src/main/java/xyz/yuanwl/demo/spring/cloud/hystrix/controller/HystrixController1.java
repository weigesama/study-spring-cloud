package xyz.yuanwl.demo.spring.cloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 降级演示
 * @author yuanwl
 * @date 2019-03-01 20:25
 */
@RestController
//加了下面这个注解, @HystrixCommand 不指定降级方法, 就调用这个默认降级方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController1 {

//	@HystrixCommand//(fallbackMethod = "fallback") //调用指定降级方法
//	@HystrixCommand(commandProperties =
//		@HystrixProperty( //调用目标服务在3秒内有响应就不会触发降级
//				name = "execution.isolation.thread.timeoutInMilliseconds",
//				value = "3000")
//	)
	@HystrixCommand
	@GetMapping("product/list/1")
	public String productList1() throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		//目标服务不可用或者超时, 或本方法内部产生异常, 都会调用降级方法返回提示信息
		return restTemplate.getForObject("http://localhost:8100/list",
				String.class);
//		throw new Exception();
	}

	/**
	 * 降级方法
	 * @return java.lang.String
	 * @author Yuanwl
	 * @date 2019-03-01 20:59:24
	 * @version v1.0.0
	 */
	public String fallback(){
		return "太拥挤了, 请稍后再试...";
	}

	/**
	 * 默认降级方法
	 * @return java.lang.String
	 * @author Yuanwl
	 * @date 2019-03-01 20:59:44
	 * @version v1.0.0
	 */
	public String defaultFallback(){
		return "默认: 太拥挤了, 请稍后再试...";
	}
}
