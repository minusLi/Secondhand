package cose.seu.secondhand;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cose.seu.secondhand.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenTests {

    @Autowired
    private TokenUtil tokenUtil;

    @Test
    public void tokenTest() {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 3600 * 1000;
        Date end = new Date(currentTime);
        String token = JWT.create()
                .withAudience(Integer.toString(213202838), "213202838", Integer.toString(0))
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256("A123456"));
        System.out.println(token);
        List<String> audience = JWT.decode(token).getAudience();
        for (int i = 0; i < audience.size(); ++i)
            System.err.println(audience.get(i));
    }
}
