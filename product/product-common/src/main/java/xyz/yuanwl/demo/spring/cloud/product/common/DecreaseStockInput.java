package xyz.yuanwl.demo.spring.cloud.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 减库存入参
 * Created by 廖师兄
 * 2018-01-20 22:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;
}