package xyz.yuanwl.demo.spring.cloud.product.service;

import xyz.yuanwl.demo.spring.cloud.product.entity.ProductCategory;

import java.util.List;

/**
 * 类目
 * Created by 廖师兄
 * 2017-12-09 22:06
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
