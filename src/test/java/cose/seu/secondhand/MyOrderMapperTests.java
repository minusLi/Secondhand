package cose.seu.secondhand;

import cose.seu.secondhand.mapper.MyOrderMapper;
import cose.seu.secondhand.model.MyOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyOrderMapperTests {
    @Autowired
    private MyOrderMapper myOrderMapper;

    @Test
    public void MyOrderTest() {
        MyOrder myOrder =new MyOrder("00000002","213202838","213202836","00000111", "UML用户指南","18251813117");
        myOrderMapper.insert(myOrder);
    }



}
