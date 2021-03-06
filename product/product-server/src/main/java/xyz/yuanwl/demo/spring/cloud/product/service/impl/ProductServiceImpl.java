package xyz.yuanwl.demo.spring.cloud.product.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import xyz.yuanwl.demo.spring.cloud.product.common.DecreaseStockInput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductInfoOutput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductMqConstant;
import xyz.yuanwl.demo.spring.cloud.product.entity.ProductInfo;
import xyz.yuanwl.demo.spring.cloud.product.enums.ProductStatusEnum;
import xyz.yuanwl.demo.spring.cloud.product.enums.ResultEnum;
import xyz.yuanwl.demo.spring.cloud.product.exception.ProductException;
import xyz.yuanwl.demo.spring.cloud.product.repository.ProductInfoRepository;
import xyz.yuanwl.demo.spring.cloud.product.service.ProductService;
//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yuanwl.demo.spring.cloud.product.utils.JsonUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 廖师兄
 * 2017-12-09 21:59
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

	@Override
	public List<ProductInfo> findUpAll() {
		return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public List<ProductInfoOutput> findList(List<String> productIdList) {
		return productInfoRepository.findByProductIdIn(productIdList).stream()
				.map(e -> {
					ProductInfoOutput output = new ProductInfoOutput();
					BeanUtils.copyProperties(e, output);
					return output;
				})
				.collect(Collectors.toList());
	}

	@Override
	public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
		//如果减库存失败, 就不会发 mq 消息
		List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);

		//发送 mq 消息
		List<ProductInfoOutput> productInfoOutputList = productInfoList
				.stream().map(e -> {
					ProductInfoOutput output = new ProductInfoOutput();
					BeanUtils.copyProperties(e, output);
					return output;
				}).collect(Collectors.toList());

        amqpTemplate.convertAndSend(ProductMqConstant.QUEUE_PRODUCT_INFO_LIST,
		        JsonUtil.toJson(productInfoOutputList));

	}

	/**
	 * 减库存操作
	 * <p>要等购物车所有商品都减完库存, 才能发 mq 消息, 所以把这些代码都集中到一起, 加事务
	 * @param decreaseStockInputList
	 * @return java.util.List<xyz.yuanwl.demo.spring.cloud.product.entity.ProductInfo>
	 * @author Yuanwl
	 * @date 2019-02-27 20:50:00
	 * @version v1.0.0
	 */
	@Transactional
	public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
		List<ProductInfo> productInfoList = new ArrayList<>();
		for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
			Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
			//判断商品是否存在
			if (!productInfoOptional.isPresent()) {
				throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
			}

			ProductInfo productInfo = productInfoOptional.get();
			//库存是否足够
			Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
			if (result < 0) {
				throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
			}

			productInfo.setProductStock(result);
			productInfoRepository.save(productInfo);
			productInfoList.add(productInfo);
		}
		return productInfoList;
	}
}
