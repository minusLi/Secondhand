package cose.seu.secondhand.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cose.seu.secondhand.model.Admin;
import cose.seu.secondhand.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TokenUtil {

    /**
     * 生成token
     *
     * @param user
     * @return token
     */
    public String generateToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 3600 * 1000;
        Date end = new Date(currentTime);
        String token = JWT.create()
                .withAudience(Integer.toString(user.getId()),user.getUsername(),Integer.toString(user.getCredit()),"user")
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    /**
     * 生成token
     *
     * @param admin
     * @return token
     */
    public String generateToken(Admin admin) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 3600 * 1000;
        Date end = new Date(currentTime);
        String token = JWT.create()
                .withAudience("000000000","admin","0","admin")
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }

    /**
     * 获取token中的userId
     *
     * @param token
     * @return userId
     */
    public static String getUserId(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    /**
     * 获取token中的userName
     *
     * @param token
     * @return userName
     */
    public static String getUserName(String token) {
        String userName = JWT.decode(token).getAudience().get(1);
        return userName;
    }

    /**
     * 获取token中的credit
     *
     * @param token
     * @return credit
     */
    public static String getCredit(String token) {
        String credit = JWT.decode(token).getAudience().get(2);
        return credit;
    }

    /**
     * 获取token中的用户类型
     *
     * @param token
     * @return credit
     */
    public static String getCategory(String token) {
        String category = JWT.decode(token).getAudience().get(3);
        return category;
    }


    /**
     * 获取request
     *
     * @return requestAttributes
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 获取token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                return c.getValue();
            }
        }
        return null;
    }

    /**
     * 修改token
     *
     * @param value
     * @return Boolean
     */
    public HttpServletResponse setToken(String value,HttpServletResponse response){
        Cookie cookie = new Cookie("token", value);
        //设置cookie的作用域
        cookie.setPath("/");
        response.addCookie(cookie);
        return response;
    }
}
