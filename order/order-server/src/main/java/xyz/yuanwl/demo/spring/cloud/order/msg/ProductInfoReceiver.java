package xyz.yuanwl.demo.spring.cloud.order.msg;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import xyz.yuanwl.demo.spring.cloud.order.utils.JsonUtil;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductInfoOutput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductMqConstant;

import java.util.List;

/**
 * @author yuanwl
 * @date 2019-02-27 22:43
 */
@Component
@Slf4j
public class ProductInfoReceiver {

	public final String PRODUCT_STOCK_TEMP = "product_stock_%s";

	@Autowired
	private StringRedisTemplate redisTemplate;

	@RabbitListener(queuesToDeclare = @Queue(ProductMqConstant.QUEUE_PRODUCT_INFO_LIST))
	public void process(String msg){
		log.info("从队列{}接收到消息: {}", ProductMqConstant.QUEUE_PRODUCT_INFO_LIST, msg);

		//msg => List<ProductInfoOutput>
		List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(msg,
				new TypeReference<List<ProductInfoOutput>>(){});
		log.info("json 转换得到: {}", productInfoOutputList);

		//保存到 redis
		for (ProductInfoOutput productInfoOutput: productInfoOutputList){
			redisTemplate.opsForValue().set(
					String.format(PRODUCT_STOCK_TEMP, productInfoOutput.getProductId()),
					productInfoOutput.getProductStock() + ""
			);
		}
	}

}
