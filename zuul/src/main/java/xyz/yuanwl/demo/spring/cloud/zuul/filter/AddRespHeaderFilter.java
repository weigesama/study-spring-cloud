package xyz.yuanwl.demo.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 后置过滤器-往响应头加东西
 * @author yuanwl
 * @date 2019-03-01 12:20
 */
//@Component
public class AddRespHeaderFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return SEND_RESPONSE_FILTER_ORDER - 1; //在发送响应前执行
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse response = context.getResponse();
		//往请求头加东西
		response.addHeader("x", "xxxxx");

		return null;
	}
}
