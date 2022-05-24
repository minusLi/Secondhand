package cose.seu.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import cose.seu.secondhand.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/adminlogin")
    @ResponseBody
    public boolean login(@RequestBody JSONObject admininfo, HttpServletResponse response) {
        String password = admininfo.getString("password");
        return adminService.adminLogin(password, response);
    }
}
