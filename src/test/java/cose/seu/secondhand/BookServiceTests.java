package cose.seu.secondhand;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cose.seu.secondhand.mapper.BookMapper;
import cose.seu.secondhand.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void getMyBook(){
        String userid="213202838";
        QueryWrapper<Book> wrapper=new QueryWrapper<>();
        wrapper.in("userId",userid);
        List<Book> bookList=bookMapper.selectList(wrapper);
        bookList.forEach(System.out::println);
        System.out.println(JSON.toJSONString(bookList));
    }
}
