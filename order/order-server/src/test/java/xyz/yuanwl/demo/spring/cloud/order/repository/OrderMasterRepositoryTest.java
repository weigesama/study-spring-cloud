package xyz.yuanwl.demo.spring.cloud.order.repository;

import xyz.yuanwl.demo.spring.cloud.order.OrderAppTests;
import xyz.yuanwl.demo.spring.cloud.order.entity.OrderMaster;
import xyz.yuanwl.demo.spring.cloud.order.enums.OrderStatusEnum;
import xyz.yuanwl.demo.spring.cloud.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by 廖师兄
 * 2017-12-10 16:13
 */
@Component
public class OrderMasterRepositoryTest extends OrderAppTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}