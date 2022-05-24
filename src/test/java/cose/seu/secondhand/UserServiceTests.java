package cose.seu.secondhand;

import com.alibaba.fastjson.JSON;
import cose.seu.secondhand.mapper.UserMapper;
import cose.seu.secondhand.model.User;
import cose.seu.secondhand.service.UserService;
import cose.seu.secondhand.util.MD5Util;
import cose.seu.secondhand.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private MD5Util md5Util;

    @Autowired
    UserMapper userMapper;

    @Test
    public void registerTest() {
        Integer id = 213202836;
        String encryption = md5Util.generateMD5("A123456");
        userService.register(id, encryption);
    }

    @Test
    public void loginTest() {
        Integer id = 213202838;
        String encryption = md5Util.generateMD5("A123456");
        User user = new User(id, encryption);
        User user1 = userMapper.selectById(user.getId());
        if (user1 == null) {
            System.out.println("未注册");
        }
        if (!user1.getPassword().equals(user.getPassword())) {
            System.out.println("密码错误");
        }
        System.out.println("登陆成功");
    }

    @Test
    public void changePasswordTest(){
        Integer id=213202838;
        String username="minusLi";
        User temp=userMapper.selectById(id);
        temp.setUsername(username);
        userMapper.updateById(temp);
    }
    @Test
    public void getUser(){
        String userid="213202838";
        User temp=userMapper.selectById(userid);
        System.out.println(JSON.toJSONString(temp));
    }


}
