package xyz.yaunwl.demo.spring.cloud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yaunwl.demo.spring.cloud.user.constant.CookieConstant;
import xyz.yaunwl.demo.spring.cloud.user.constant.RedisConstant;
import xyz.yaunwl.demo.spring.cloud.user.domain.UserInfo;
import xyz.yaunwl.demo.spring.cloud.user.enums.ResultEnum;
import xyz.yaunwl.demo.spring.cloud.user.enums.RoleEnum;
import xyz.yaunwl.demo.spring.cloud.user.service.UserService;
import xyz.yaunwl.demo.spring.cloud.user.utils.CookieUtil;
import xyz.yaunwl.demo.spring.cloud.user.utils.ResultVOUtil;
import xyz.yaunwl.demo.spring.cloud.user.vo.ResultVO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 廖师兄
 * 2018-03-04 23:12
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 买家登录
	 * @param openid
	 * @param response
	 * @return
	 */
	@GetMapping("/buyer")
	public ResultVO buyer(@RequestParam("openid") String openid,
	                      HttpServletResponse response) {
		//1. openid和数据库里的数据是否匹配
		UserInfo userInfo = userService.findByOpenid(openid);
		if (userInfo == null) {
			return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
		}

		//2. 判断角色
		if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
			return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
		}

		//3. cookie里设置openid=abc
		CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

		return ResultVOUtil.success();
	}

	/**
	 * 卖家登录
	 * @param openid
	 * @param request
	 * @param response
	 * @return xyz.yaunwl.demo.spring.cloud.user.vo.ResultVO
	 * @author Yuanwl
	 * @date 2019-03-01 14:33:42
	 * @version v1.0.0
	 */
	@GetMapping("/seller")
	public ResultVO seller(@RequestParam("openid") String openid,
						  HttpServletRequest request,
						  HttpServletResponse response) {
		//0. 判断是否已登录
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie != null &&
				!StringUtils.isEmpty(stringRedisTemplate.opsForValue()
						.get(String.format(RedisConstant.TOKEN_TEMPLATE,
								cookie.getValue())
						)
				)
		) {
			return ResultVOUtil.success();
		}

		//1. openid和数据库里的数据是否匹配
		UserInfo userInfo = userService.findByOpenid(openid);
		if (userInfo == null) {
			return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
		}

		//2. 判断角色
		if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
			return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
		}

		//3. redis设置key=UUID, value=xyz
		String token = UUID.randomUUID().toString();
		Integer expire = CookieConstant.expire;
		stringRedisTemplate.opsForValue().set(
				String.format(RedisConstant.TOKEN_TEMPLATE, token),
				openid,
				expire,
				TimeUnit.SECONDS
		);

		//4. cookie里设置token=UUID
		CookieUtil.set(response, CookieConstant.TOKEN, token,
				CookieConstant.expire);

		return ResultVOUtil.success();
	}


}
