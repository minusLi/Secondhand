package cose.seu.secondhand.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cose.seu.secondhand.mapper.BookMapper;
import cose.seu.secondhand.mapper.MyOrderMapper;
import cose.seu.secondhand.mapper.SellMapper;
import cose.seu.secondhand.model.Book;
import cose.seu.secondhand.model.MyOrder;
import cose.seu.secondhand.model.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOrderService {

    @Autowired
    private MyOrderMapper myOrderMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private SellMapper sellMapper;
    private volatile static int oid = 1;

    public String getMyOrder(String userid) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        wrapper.in("buyer", userid);
        List<MyOrder> orderList = myOrderMapper.selectList(wrapper);
        return JSON.toJSONString(orderList);
    }

    private void book2Sell(Book book, Sell sell) {
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
    }

    private void sell2Book(Sell sell, Book book) {
        book.setId(sell.getId());
        book.setUserId(sell.getUserId());
        book.setPhotoPath(sell.getPhotoPath());
        book.setPhotonum(sell.getPhotonum());
        book.setBookname(sell.getBookname());
        book.setISBN(sell.getISBN());
        book.setAuthor(sell.getAuthor());
        book.setPublisher(sell.getPublisher());
        book.setDate(sell.getDate());
        book.setUsed(sell.getUsed());
        book.setPrice(sell.getPrice());
        book.setComment(sell.getComment());
        book.setTel(sell.getTel());
    }

    public synchronized boolean addOrder(JSONObject orderinfo) {
        String id = String.format("%08d", oid);
        String buyer = orderinfo.getString("buyer");
        String bookid = orderinfo.getString("bookid");
        Book book = bookMapper.selectById(bookid);
        if(book==null){
            return false;
        }
        String seller = String.valueOf(book.getUserId());
        String bookname = book.getBookname();
        String tel = book.getTel();
        if (seller.equals(buyer)) {
            return false;
        }
        Sell sell = new Sell();
        book2Sell(book, sell);
        sellMapper.insert(sell);
        bookMapper.deleteById(bookid);
        MyOrder myOrder = new MyOrder(id, buyer, seller, bookid, bookname, tel);
        myOrderMapper.insert(myOrder);
        oid++;
        return true;
    }

    public boolean deleteOrder(String id) {
        MyOrder myOrder = myOrderMapper.selectById(id);
        String bookid = myOrder.getBookid();
        Sell sell = sellMapper.selectById(bookid);
        Book book = new Book();
        sell2Book(sell, book);
        bookMapper.insert(book);
        sellMapper.deleteById(bookid);
        myOrderMapper.deleteById(id);
        return true;
    }

    public boolean completeOrder(String id) {
        MyOrder myOrder = myOrderMapper.selectById(id);
        String bookid = myOrder.getBookid();
        myOrderMapper.deleteById(myOrder);
        sellMapper.deleteById(id);
        return true;
    }
}
