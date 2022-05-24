package cose.seu.secondhand.interceptor;

import cose.seu.secondhand.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看请求时是否存在token，不存在直接重定向到登陆界面
        String token = tokenUtil.getToken(request);
        if (StringUtils.hasText(token)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
