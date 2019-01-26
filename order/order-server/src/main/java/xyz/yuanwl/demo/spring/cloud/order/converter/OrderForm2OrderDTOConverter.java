package xyz.yuanwl.demo.spring.cloud.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import xyz.yuanwl.demo.spring.cloud.order.entity.OrderDetail;
import xyz.yuanwl.demo.spring.cloud.order.dto.OrderDTO;
import xyz.yuanwl.demo.spring.cloud.order.enums.ResultEnum;
import xyz.yuanwl.demo.spring.cloud.order.exception.OrderException;
import xyz.yuanwl.demo.spring.cloud.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 17:38
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
