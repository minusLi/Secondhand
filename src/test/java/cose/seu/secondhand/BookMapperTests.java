package cose.seu.secondhand;

import cose.seu.secondhand.mapper.BookMapper;
import cose.seu.secondhand.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTests {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void BookTest() {
        Book book=new Book();
        book.setId("00000003");
        book.setUserId(213202838);
        book.setBookname("软件工程导论");
        book.setAuthor("Roger Pressman");
        book.setISBN("9787111555018");
        book.setUsed(0);
        book.setPrice("45.00");
        book.setComment("全新软件工程导论");
        bookMapper.insert(book);
    }



}
