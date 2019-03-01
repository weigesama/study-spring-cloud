package xyz.yuanwl.demo.spring.cloud.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 前置过滤器-限流
 * @author yuanwl
 * @date 2019-03-01 12:35
 */
//@Component
public class RateLimitFilter extends ZuulFilter {

	/** 用 Google 实现的令牌桶算法实现限流 */
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return SERVLET_DETECTION_FILTER_ORDER - 1; //限流过滤器要在所有过滤器之前
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		//如果拿不到令牌, 终止过滤
		if (!RATE_LIMITER.tryAcquire()){
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
		}

		return null;
	}
}
