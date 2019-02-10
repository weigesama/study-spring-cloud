package xyz.yuanwl.demo.spring.cloud.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author yuanwl
 * @date 2019-02-09 21:13
 */
@Component
@Data
@ConfigurationProperties("girl")
@RefreshScope
public class GirlConfig {
	private String name;
	private Integer age;
}
