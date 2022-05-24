package cose.seu.secondhand.service;

import cose.seu.secondhand.model.Admin;
import cose.seu.secondhand.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class AdminService {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private Admin admin;

    public boolean adminLogin(String password, HttpServletResponse response) {
        if (!password.equals(admin.getPassword())){
            return false;
        }
        String token = tokenUtil.generateToken(admin);
        Cookie cookie = new Cookie("token", token);
        //设置cookie的作用域
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

}
