package xyz.yuanwl.demo.spring.cloud.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>feign 客户端，注意这是一个接口
 *
 * @author Yuanwl
 * @date 2019/1/28 16:08
 */
@FeignClient(name = "product") //指定要调用哪个微服务的接口
public interface ProductClient {

	@GetMapping("/product/msg") //与微服务的接口路径一直
	String productMsg(); //方法名随意

}
