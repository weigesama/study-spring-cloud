package xyz.yuanwl.demo.spring.cloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xyz.yuanwl.demo.spring.cloud.order.client.ProductClient;

/**
 * <p>测试 RestTemplate 调用微服务的三种方式
 *
 * @author Yuanwl
 * @date 2019/1/26 15:25
 */
@RestController
public class ClientController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;

//	@Autowired
//	private RestTemplate restTemplate;

	@GetMapping("orderGetProductMsg")
	public String orderGetProductMsg(){
		String ret = null;

		//方式1：直接新建 restTemplate 调用，url写死——没有用到负载均衡，问题很大
//		ret = new RestTemplate().getForObject("http://localhost:8100/product/msg", String.class);

		//方式2：使用注入的loadBalancerClient，通过服务名挑选服务实例（有负载均衡），后面的步骤和方式1一样——要写比较多代码
		ServiceInstance serviceInstance = loadBalancerClient.choose("product"); //服务名不区分大小写
		String url = String.format("http://%s:%s/product/msg", serviceInstance.getHost(), serviceInstance.getPort());
		ret = new RestTemplate().getForObject(url, String.class);

		//方式3：利用@LoadBalanced，可以在restTemplate里直接使用服务名——其实是方式2的简化，比较简洁
//		ret = restTemplate.getForObject("http://product/product/msg", String.class);

		return ret;
	}

	@Autowired
	private ProductClient productClient;

	@GetMapping("orderGetProductMsgByFeign")
	public String orderGetProductMsgByFeign(){
		String ret = productClient.productMsg();
		return ret;
	}
}
