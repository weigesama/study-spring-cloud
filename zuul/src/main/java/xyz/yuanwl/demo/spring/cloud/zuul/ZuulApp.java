package xyz.yuanwl.demo.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * @author yuanwl
 * @date 2019-02-28 20:40
 */
@SpringBootApplication
@EnableZuulProxy //启用 zuul 网关代理
public class ZuulApp {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
	}

	/**
	 * 动态从配置中心获取配置, 实现动态路由??
	 * @return org.springframework.cloud.netflix.zuul.filters.ZuulProperties
	 * @author Yuanwl
	 * @date 2019-02-28 22:03:14
	 * @version v1.0.0
	 */
	@ConfigurationProperties("zuul")
	@RefreshScope
	public ZuulProperties zuulProperties(){
		return new ZuulProperties();
	}
}
