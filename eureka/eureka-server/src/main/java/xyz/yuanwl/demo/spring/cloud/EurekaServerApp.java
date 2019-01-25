package xyz.yuanwl.demo.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>EurekaServer 注册中心
 *
 * @author Yuanwl
 * @date 2019/1/19 20:16
 */
@SpringBootApplication
/*
 * 加了这个注解，什么都不配置，就可以启动一个注册中心，访问 http://localhost:服务端口/ 就可以看到eureka监控面板。
 * 测试发现 Finchley.RELEASE 版本，@EnableEurekaServer 注解不再包含客户端注解，
 * 但是默认情况下还是会自动尝试把自己也注册到注册中心（心跳机制，一段时间尝试一次，直到成功为止），
 * 不过默认是注册到端口是8761的 eureka-server，如果注册中心不是配置为这个端口，每次尝试都会失败，报错
 */
@EnableEurekaServer
public class EurekaServerApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApp.class, args);
	}
}
