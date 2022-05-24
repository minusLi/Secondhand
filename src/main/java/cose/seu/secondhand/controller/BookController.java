package cose.seu.secondhand.controller;

import com.alibaba.fastjson.JSONObject;
import cose.seu.secondhand.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/methods/bookinfo")
    @ResponseBody
    public String getBook(@RequestParam("id") String id) {
        return bookService.getBook(id);
    }

    @RequestMapping("/methods/mybook")
    @ResponseBody
    public String getMyBook(@RequestParam("userid") String userId) {
        return bookService.getMyBook(userId);
    }

    @RequestMapping("/methods/allbook")
    @ResponseBody
    public String getAllBook() {
        return bookService.getAllBook();
    }

    @RequestMapping("/methods/addbook")
    @ResponseBody
    public String addBook(@RequestBody JSONObject bookinfo) {
        return bookService.addBook(bookinfo);
    }

    @RequestMapping("/methods/addphoto")
    @ResponseBody
    public Boolean addPhoto(@RequestParam("bookid") String bookid, @RequestParam("files[]") MultipartFile[] photos) {
        return bookService.addPhoto(bookid, photos);
    }

    @RequestMapping("/methods/changebook")
    @ResponseBody
    public Boolean changeBook(@RequestBody JSONObject bookinfo) {
        return bookService.changeBook(bookinfo);
    }

    @RequestMapping("/methods/changephoto")
    @ResponseBody
    public Boolean changePhoto(@RequestParam("bookid") String bookid, @RequestParam("files[]") MultipartFile[] photos) {
        return bookService.changePhoto(bookid, photos);
    }

    @RequestMapping("/methods/deletebook")
    @ResponseBody
    public Boolean deleteBook(@RequestParam("bookid") String id) {
        return bookService.deleteBook(id);
    }
}
