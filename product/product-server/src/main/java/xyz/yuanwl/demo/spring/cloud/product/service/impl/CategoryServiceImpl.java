package xyz.yuanwl.demo.spring.cloud.product.service.impl;

import xyz.yuanwl.demo.spring.cloud.product.dataobject.ProductCategory;
import xyz.yuanwl.demo.spring.cloud.product.repository.ProductCategoryRepository;
import xyz.yuanwl.demo.spring.cloud.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-09 22:06
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
