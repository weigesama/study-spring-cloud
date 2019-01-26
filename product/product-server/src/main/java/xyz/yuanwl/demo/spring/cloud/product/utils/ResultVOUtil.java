package xyz.yuanwl.demo.spring.cloud.product.utils;

import xyz.yuanwl.demo.spring.cloud.product.vo.ResultVO;

/**
 * TODO
 * Created by 廖师兄
 * 2017-12-09 22:53
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
