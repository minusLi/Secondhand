package cose.seu.secondhand;

import com.alibaba.fastjson.JSONObject;
import cose.seu.secondhand.mapper.BookMapper;
import cose.seu.secondhand.mapper.MyOrderMapper;
import cose.seu.secondhand.mapper.SellMapper;
import cose.seu.secondhand.model.Book;
import cose.seu.secondhand.model.MyOrder;
import cose.seu.secondhand.model.Sell;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyOrderServiceTests {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private SellMapper sellMapper;

    @Autowired
    private MyOrderMapper myOrderMapper;

    @Test
    public void addOrder() {
        String id = "00000001";
        String buyer = "213200001";
        String bookid = "00000002";
        Book book = bookMapper.selectById(bookid);
        String seller = String.valueOf(book.getUserId());
        String bookname = book.getBookname();
        String tel = book.getTel();
        if (seller.equals(buyer)) {
            System.out.println("FALSE");
        }
        Sell sell = new Sell();
        sell.setId(book.getId());
        sell.setUserId(book.getUserId());
        sell.setPhotoPath(book.getPhotoPath());
        sell.setPhotonum(book.getPhotonum());
        sell.setBookname(book.getBookname());
        sell.setISBN(book.getISBN());
        sell.setAuthor(book.getAuthor());
        sell.setPublisher(book.getPublisher());
        sell.setDate(book.getDate());
        sell.setUsed(book.getUsed());
        sell.setPrice(book.getPrice());
        sell.setComment(book.getComment());
        sell.setTel(book.getTel());
        sellMapper.insert(sell);
        bookMapper.deleteById(bookid);
        MyOrder myOrder = new MyOrder(id, buyer, seller, bookid, bookname, tel);
        myOrderMapper.insert(myOrder);
    }

}
