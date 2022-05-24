package cose.seu.secondhand;

import com.alibaba.fastjson.JSON;
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
public class SearchServiceTests {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void SearchBook() {
        String category = "author";
        String keyword = "grady";
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like(category, keyword);
        List<Book> bookList = bookMapper.selectList(wrapper);
        if(!bookList.isEmpty()) {
            System.out.println(JSON.toJSONString(bookList));
        }else System.out.println("NO RESULT!");
    }
}
