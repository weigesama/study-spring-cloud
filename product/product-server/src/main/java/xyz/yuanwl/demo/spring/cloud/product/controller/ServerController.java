package xyz.yuanwl.demo.spring.cloud.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 廖师兄
 * 2017-12-10 20:37
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(HttpServletRequest request) {
        System.err.println(request.getCookies());
        return "this is product0's msg";
    }
}
