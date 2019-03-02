package xyz.yuanwl.demo.spring.cloud.product.client;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.yuanwl.demo.spring.cloud.product.common.DecreaseStockInput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductInfoOutput;

import java.util.List;

/**
 * feign 客户端
 * <p>这里结合 hystrix 使用, 指定降级逻辑为 ProductClient 的内部类
 * Created by 廖师兄
 * 2017-12-10 21:04
 */
@FeignClient(name = "product", fallback = ProductClient.ProductClientFallBack.class)
public interface ProductClient {

    @GetMapping("msg") //与微服务的接口路径一直
    String productMsg(); //方法名随意

    @PostMapping("listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    class ProductClientFallBack implements ProductClient{

        @Override
        public String productMsg() {
            return "feign-降级实现";
        }

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }
}
