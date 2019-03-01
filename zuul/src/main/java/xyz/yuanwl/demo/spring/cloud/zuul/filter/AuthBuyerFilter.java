package xyz.yuanwl.demo.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import xyz.yuanwl.demo.spring.cloud.zuul.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 前置过滤器-买家认证过滤器
 * @author yuanwl
 * @date 2019-03-01 17:03
 */
@Component
public class AuthBuyerFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		//包含下订单请求才过滤
		if (request.getRequestURI().contains("/order/create"))
		return true;

		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		//用户必须包含买家的登录信息才能继续访问
		Cookie cookie = CookieUtil.get(request, "openid");
		if (cookie == null ||
				StringUtils.isBlank(cookie.getValue())){
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
		}

		return null;
	}
}
