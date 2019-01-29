package xyz.yuanwl.demo.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <p>配置中心服务端
 *
 * @author Yuanwl
 * @date 2019/1/29 14:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer //启动配置中心服务端
public class ConfigServerApp {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApp.class, args);
	}
}
