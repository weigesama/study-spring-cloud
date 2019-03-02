package xyz.yuanwl.demo.spring.cloud.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yuanwl.demo.spring.cloud.product.client.ProductClient;

/**
 * feign 结合 hystrix 演示
 * @author yuanwl
 * @date 2019-03-02 11:12
 */
@RestController
public class FeignHystrixController {

	@Autowired
	private ProductClient productClient;

	@GetMapping("product/msg")
	public String msg(){
		return productClient.productMsg();
	}
}
