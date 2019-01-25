package xyz.yuanwl.demo.spring.cloud.product.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.yuanwl.demo.spring.cloud.product.ProductAppTests;
import xyz.yuanwl.demo.spring.cloud.product.common.DecreaseStockInput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductInfoOutput;
import xyz.yuanwl.demo.spring.cloud.product.dataobject.ProductInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-09 22:03
 */
@Component
public class ProductTests extends ProductAppTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList() throws Exception {
        List<ProductInfoOutput> list = productService.findList(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875196366160022", 2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }

}