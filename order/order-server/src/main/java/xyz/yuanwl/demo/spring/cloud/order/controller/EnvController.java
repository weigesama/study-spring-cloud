package xyz.yuanwl.demo.spring.cloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yuanwl.demo.spring.cloud.order.config.GirlConfig;

/**
 * @author yuanwl
 * @date 2019-02-09 20:31
 */
@RestController
@RequestMapping("order/env")
@RefreshScope //刷新范围：这个注解修饰的类里面的属性，将会被 bus 刷新
public class EnvController {
	@Value("${env}")
	private String env;

	@GetMapping("printEnv")
	public String printEnv() {
		return env;
	}

	@Autowired
	private GirlConfig girlConfig;

	@GetMapping("printGirl")
	public String printGirl() {
		return "girl: {name: " + girlConfig.getName() + ", age: " + girlConfig.getAge() + "}";
	}
}
