package xyz.yuanwl.demo.spring.cloud.order.vo;

import lombok.Data;

/**
 * Created by 廖师兄
 * 2017-12-10 18:02
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
