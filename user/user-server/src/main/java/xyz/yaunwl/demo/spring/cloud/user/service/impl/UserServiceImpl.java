package xyz.yaunwl.demo.spring.cloud.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yaunwl.demo.spring.cloud.user.domain.UserInfo;
import xyz.yaunwl.demo.spring.cloud.user.repository.UserInfoRepository;
import xyz.yaunwl.demo.spring.cloud.user.service.UserService;

/**
 * Created by 廖师兄
 * 2018-03-04 21:45
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoRepository repository;

	/**
	 * 通过openid来查询用户信息
	 *
	 * @param openid
	 * @return
	 */
	@Override
	public UserInfo findByOpenid(String openid) {
		return repository.findByOpenid(openid);
	}
}
