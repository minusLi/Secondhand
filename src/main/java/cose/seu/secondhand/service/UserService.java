package cose.seu.secondhand.service;

import com.alibaba.fastjson.JSON;
import cose.seu.secondhand.mapper.UserMapper;
import cose.seu.secondhand.model.User;
import cose.seu.secondhand.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 登录检查
     *
     * @param user
     * @parm response
     */
    public boolean login(User user, HttpServletResponse response) {
        User user1 = userMapper.selectById(user.getId());
        if (user1 == null) {
            return false;
        }
        if (!user1.getPassword().equals(user.getPassword())) {
            return false;
        }
        String token = tokenUtil.generateToken(user1);
        Cookie cookie = new Cookie("token", token);
        //设置cookie的作用域
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    /**
     * 注册新账户
     *
     * @param id
     * @param password
     */
    public boolean register(Integer id, String password) {
        if (userMapper.selectById(id) == null) {
            User user = new User(id, id.toString(), password);
            if (userMapper.insert(user) > 0) {
                return true;
            } else return false;
        } else return false;
    }

    /**
     * 修改用户名
     *
     * @param username
     * @param request
     */
    public boolean changeUsername(String username, HttpServletRequest request, HttpServletResponse response) {
        String token = tokenUtil.getToken(request);
        Integer id = Integer.valueOf(TokenUtil.getUserId(token));
        User temp = userMapper.selectById(id);
        if (temp == null)
            return false;
        temp.setUsername(username);
        userMapper.updateById(temp);
        String newToken = tokenUtil.generateToken(temp);
        tokenUtil.setToken(newToken, response);
        return true;
    }

    /**
     * 修改密码
     *
     * @param password
     * @param request
     */
    public boolean changePassword(String ordinary, String password, HttpServletRequest request, HttpServletResponse response) {
        String token = tokenUtil.getToken(request);
        Integer id = Integer.valueOf(TokenUtil.getUserId(token));
        User temp = userMapper.selectById(id);
        if (temp == null)
            return false;
        if (temp.getPassword().equals(ordinary)) {
            temp.setPassword(password);
            userMapper.updateById(temp);
            String newToken = tokenUtil.generateToken(temp);
            tokenUtil.setToken(newToken, response);
            return true;
        } else return false;
    }

    /**
     * 修改密码
     *
     * @param uid
     * @param password
     */
    public boolean adminChangePassword(String uid, String password) {
        Integer id= Integer.valueOf(uid);
        User temp = userMapper.selectById(id);
        if (temp == null) {
            return false;
        }
        temp.setPassword(password);
        userMapper.updateById(temp);
        return true;
    }


    /**
     * 获取用户
     *
     * @param userid
     */
    public String getUser(String userid) {
        User temp = userMapper.selectById(userid);
        return JSON.toJSONString(temp);
    }

    /**
     * 获取所有用户
     */
    public String getAllUser() {
        List<User> alluser = userMapper.selectList(null);
        return JSON.toJSONString(alluser);
    }


}

