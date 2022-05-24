package cose.seu.secondhand;

import cose.seu.secondhand.model.Admin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTests {
    @Autowired
    private Admin admin;

    @Test
    public void adminLoginTest() {
        String password = "second@2022";
        System.out.println(admin.getPassword());
        if (!password.equals(admin.getPassword())) {
            System.err.println("false");
        } else System.err.println("true");
    }
}
