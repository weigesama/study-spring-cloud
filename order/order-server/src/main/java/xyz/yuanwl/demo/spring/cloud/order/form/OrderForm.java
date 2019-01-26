package xyz.yuanwl.demo.spring.cloud.order.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by 廖师兄
 * 2017-12-10 17:21
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotBlank(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotBlank(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotBlank(message = "地址必填")
    private String address;

    /**
     * 买家微信openid
     */
    @NotBlank(message = "openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotBlank(message = "购物车不能为空")
    private String items;

}
