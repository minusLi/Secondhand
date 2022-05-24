package cose.seu.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import cose.seu.secondhand.model.User;
import cose.seu.secondhand.service.UserService;
import cose.seu.secondhand.util.MD5Util;
import cose.seu.secondhand.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MD5Util md5Util;

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody JSONObject param, HttpServletResponse response) {
        Integer id = param.getInteger("username");
        String password = param.getString("password");
        String encryption = md5Util.generateMD5(password);
        return userService.login(new User(id, encryption), response);
    }

    @PostMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody JSONObject param) {
        Integer id = param.getInteger("username");
        String password = param.getString("password");
        String encryption = md5Util.generateMD5(password);
        return userService.register(id, encryption);
    }

    @PostMapping("/methods/username")
    @ResponseBody
    public boolean changeUsername(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse response) {
        String username = param.getString("name");
        return userService.changeUsername(username, request, response);
    }

    @PostMapping("/methods/password")
    @ResponseBody
    public boolean changePassword(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse response) {
        String ordinary = param.getString("password");
        String password = param.getString("newpsw");
        String ordEn = md5Util.generateMD5(ordinary);
        String pasEn = md5Util.generateMD5(password);
        return userService.changePassword(ordEn, pasEn, request, response);
    }

    @GetMapping("/methods/getuser")
    @ResponseBody
    public String getUser(@RequestParam("userid") String userid) {
        return userService.getUser(userid);
    }

    @GetMapping("/methods/alluser")
    @ResponseBody
    public String getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/methods/adminchange")
    @ResponseBody
    public boolean adminChangePassword(@RequestBody JSONObject param) {
        String id = param.getString("id");
        String password = md5Util.generateMD5(param.getString("password"));
        return userService.adminChangePassword(id, password);
    }

}
