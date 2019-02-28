package xyz.yuanwl.demo.spring.cloud.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.yuanwl.demo.spring.cloud.product.common.DecreaseStockInput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductInfoOutput;

import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 21:04
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("msg") //与微服务的接口路径一直
    String productMsg(); //方法名随意

    @PostMapping("listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
