package cose.seu.secondhand.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class MD5Util {

    /**
     * 保护用户隐私，密码明文采用MD5加密
     *
     * @param password
     * @return token
     */
    public String generateMD5(String password) {
        byte[] message = password.getBytes();
        return DigestUtils.md5DigestAsHex(message).toUpperCase();
    }

}
