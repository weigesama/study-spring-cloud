package xyz.yuanwl.demo.spring.cloud.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * Created by 廖师兄
 * 2018-03-11 23:04
 * C - Cross  O - Origin  R - Resource  S - Sharing
 */
@Configuration
public class CorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true); //是否支持 cookie 跨越
		config.setAllowedOrigins(Arrays.asList("*")); //设置允许访问的原始域名(允许这些域名访问我们的服务), 可以配置多个, * 代表所有域名
		config.setAllowedHeaders(Arrays.asList("*")); //设置允许的请求头
		config.setAllowedMethods(Arrays.asList("*")); //设置允许的请求方法
		config.setMaxAge(300l); //在这个时间内(秒), 相同的访问不再作跨域检查

		source.registerCorsConfiguration("/**", config); // /** 表示将要访问的(我们的服务)应用以上跨域配置
		return new CorsFilter(source);
	}
}
