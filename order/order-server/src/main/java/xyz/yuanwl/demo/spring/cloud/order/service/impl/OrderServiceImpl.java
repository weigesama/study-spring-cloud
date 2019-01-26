package xyz.yuanwl.demo.spring.cloud.order.service.impl;

import xyz.yuanwl.demo.spring.cloud.order.entity.OrderDetail;
import xyz.yuanwl.demo.spring.cloud.order.entity.OrderMaster;
import xyz.yuanwl.demo.spring.cloud.order.dto.OrderDTO;
import xyz.yuanwl.demo.spring.cloud.order.enums.OrderStatusEnum;
import xyz.yuanwl.demo.spring.cloud.order.enums.PayStatusEnum;
import xyz.yuanwl.demo.spring.cloud.order.enums.ResultEnum;
import xyz.yuanwl.demo.spring.cloud.order.exception.OrderException;
import xyz.yuanwl.demo.spring.cloud.order.repository.OrderDetailRepository;
import xyz.yuanwl.demo.spring.cloud.order.repository.OrderMasterRepository;
import xyz.yuanwl.demo.spring.cloud.order.service.OrderService;
import xyz.yuanwl.demo.spring.cloud.order.utils.KeyUtil;
import xyz.yuanwl.demo.spring.cloud.product.client.ProductClient;
import xyz.yuanwl.demo.spring.cloud.product.common.DecreaseStockInput;
import xyz.yuanwl.demo.spring.cloud.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 廖师兄
 * 2017-12-10 16:44
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderMasterRepository orderMasterRepository;

//    @Autowired
//    private ProductClient productClient;

	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		String orderId = KeyUtil.genUniqueKey();

		//查询商品信息(调用商品服务)
//        List<String> productIdList = orderDTO.getOrderDetailList().stream()
//                .map(OrderDetail::getProductId)
//                .collect(Collectors.toList());
//        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

		//计算总价
//        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
//        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
//            for (ProductInfoOutput productInfo: productInfoList) {
//                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
//                    //单价*数量
//                    orderAmout = productInfo.getProductPrice()
//                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
//                            .add(orderAmout);
//                    BeanUtils.copyProperties(productInfo, orderDetail);
//                    orderDetail.setOrderId(orderId);
//                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
//                    //订单详情入库
//                    orderDetailRepository.save(orderDetail);
//                }
//            }
//        }

		//扣库存(调用商品服务)
//        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
//                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
//                .collect(Collectors.toList());
//        productClient.decreaseStock(decreaseStockInputList);

		//订单入库
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster); //源对象的null属性值也会复制给目标对象，所以要放在设置id后面执行
        orderMaster.setOrderAmount(BigDecimal.ZERO); //orderAmout
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderMasterRepository.save(orderMaster);
		return orderDTO;
	}

//    @Override
//    @Transactional
//    public OrderDTO finish(String orderId) {
//        //1. 先查询订单
//        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
//        if (!orderMasterOptional.isPresent()) {
//            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
//        //2. 判断订单状态
//        OrderMaster orderMaster = orderMasterOptional.get();
//        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
//            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//
//        //3. 修改订单状态为完结
//        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
//        orderMasterRepository.save(orderMaster);
//
//        //查询订单详情
//        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
//        if (CollectionUtils.isEmpty(orderDetailList)) {
//            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
//        }
//
//        OrderDTO orderDTO = new OrderDTO();
//        BeanUtils.copyProperties(orderMaster, orderDTO);
//        orderDTO.setOrderDetailList(orderDetailList);
//
//        return orderDTO;
//    }


}
