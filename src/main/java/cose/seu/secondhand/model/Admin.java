package cose.seu.secondhand.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Admin {

    @Value("${admin.password}")
    private String password;

    private static final Admin admin = new Admin();

    private Admin() {
    }

    public static Admin getInstance() {
        return admin;
    }

    public String getPassword() {
        return this.password;
    }
}
