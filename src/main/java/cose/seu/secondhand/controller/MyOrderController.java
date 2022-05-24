package cose.seu.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import cose.seu.secondhand.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyOrderController {
    @Autowired
    private MyOrderService myOrderService;

    @RequestMapping("/methods/myorder")
    @ResponseBody
    public String getMyOrder(@RequestParam("userid") String userId) {
        return myOrderService.getMyOrder(userId);
    }

    @RequestMapping("/methods/addorder")
    @ResponseBody
    public Boolean addOrder(@RequestBody JSONObject orderinfo) {
        return myOrderService.addOrder(orderinfo);
    }

    @RequestMapping("/methods/deleteorder")
    @ResponseBody
    public Boolean deleteOrder(@RequestParam("oid") String id) {
        return myOrderService.deleteOrder(id);
    }

    @RequestMapping("/methods/completeorder")
    @ResponseBody
    public Boolean completeOrder(@RequestParam("oid") String id) {
        return myOrderService.completeOrder(id);
    }
}
