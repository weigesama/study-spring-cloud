package xyz.yuanwl.demo.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 前置过滤器-token 过滤器
 * @author yuanwl
 * @date 2019-03-01 11:16
 */
//@Component
public class TokenFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1; //放在这个过滤器之前执行
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 自定义过滤器校验逻辑代码
	 * @return java.lang.Object
	 * @author Yuanwl
	 * @date 2019-03-01 11:19:20
	 * @version v1.0.0
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		//从请求里获取 token 参数, 如果为空就终止过滤, 并返回错误码
		String token = request.getParameter("token");
		if (StringUtils.isBlank(token)){
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
		}
		return null;
	}
}
