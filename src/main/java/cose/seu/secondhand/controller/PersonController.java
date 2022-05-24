package cose.seu.secondhand.controller;

import com.alibaba.fastjson.JSON;
import cose.seu.secondhand.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class PersonController {
    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("/methods/personinfo")
    @ResponseBody
    public String getPersonInfo(HttpServletRequest request){
        String token=tokenUtil.getToken(request);
        Map<String,String> map=new HashMap<>();
        map.put("userId",TokenUtil.getUserId(token));
        map.put("username",TokenUtil.getUserName(token));
        map.put("credit",TokenUtil.getCredit(token));
        return JSON.toJSONString(map);
    }
}
