package xyz.yaunwl.demo.spring.cloud.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yaunwl.demo.spring.cloud.user.domain.UserInfo;

/**
 * Created by 廖师兄
 * 2018-03-04 21:42
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

	UserInfo findByOpenid(String openid);
}
