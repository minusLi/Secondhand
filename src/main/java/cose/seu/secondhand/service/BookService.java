package cose.seu.secondhand.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cose.seu.secondhand.mapper.BookMapper;
import cose.seu.secondhand.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BookService {
    private volatile static int bookid = 1;

    @Value("${photopath.root}")
    private String root;
    @Value("${photopath.folder}")
    private String folder;
    @Autowired
    private BookMapper bookMapper;

    public boolean addBook(Book book) {
        return bookMapper.insert(book) > 0;
    }

    public String getBook(String id) {
        Book result = bookMapper.selectById(id);
        return JSON.toJSONString(result);
    }

    public String getAllBook() {
        List<Book> allbook = bookMapper.selectList(null);
        return JSON.toJSONString(allbook);
    }

    public String getMyBook(String userid) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.in("userId", userid);
        List<Book> bookList = bookMapper.selectList(wrapper);
        return JSON.toJSONString(bookList);
    }

    public Book setBookinfo(JSONObject bookinfo, String id) {
        Book book = new Book();
        book.setId(id);
        String date = bookinfo.getString("date");
        if (date != null) {
            book.setDate(date);
        }
        String ISBN = bookinfo.getString("ISBN");
        if (ISBN != null) {
            book.setISBN(ISBN);
        }
        String author = bookinfo.getString("author");
        if (author != null) {
            book.setAuthor(author);
        }
        String price = bookinfo.getString("price");
        if (price != null) {
            book.setPrice(price);
        }
        String publisher = bookinfo.getString("publisher");
        if (publisher != null) {
            book.setPublisher(publisher);
        }
        String used = bookinfo.getString("used");
        if (used != null) {
            int i = 0;
            switch (used) {
                case "全新":
                    break;
                case "较新":
                    i = 1;
                    break;
                case "普通":
                    i = 2;
                    break;
                case "可以接受":
                    i = 3;
                    break;
            }
            book.setUsed(i);
        }
        String bookname = bookinfo.getString("bookname");
        if (bookname != null) {
            book.setBookname(bookname);
        }
        Integer userid = bookinfo.getInteger("userid");
        if (userid != null) {
            book.setUserId(userid);
        }
        String comment = bookinfo.getString("introduction");
        if (comment != null) {
            book.setComment(comment);
        }
        String tel = bookinfo.getString("phone");
        if (tel != null) {
            book.setTel(tel);
        }
        return book;
    }

    public synchronized String addBook(JSONObject bookinfo) {
        String id = String.format("%08d", bookid);
        bookMapper.insert(setBookinfo(bookinfo, id));
        bookid++;
        return id;
    }

    @Async("asyncImportExecutor")
    public void upload(String url, Integer i, MultipartFile photo) {
        try {
            Path path = Paths.get(url + photo.getOriginalFilename());
            Path pathCreate = Files.createDirectories(path);

            photo.transferTo(pathCreate.toFile());

            BufferedImage image = ImageIO.read(pathCreate.toFile());
            ImageIO.write(image, "jpg", new File(url + i + ".jpg"));
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean addPhoto(String id, MultipartFile[] photos) {
        if (photos.length == 0) {
            return false;
        }
        for (int i = 0; i < photos.length; ++i) {
            upload(root + folder + id + "/", i + 1, photos[i]);
        }
        Book book = bookMapper.selectById(id);
        book.setPhotoPath(folder + id + "/");
        book.setPhotonum(photos.length);
        return bookMapper.updateById(book) > 0;
    }

    public boolean changePhoto(String id, MultipartFile[] photos) {
        deletePhoto(id);
        Book book = bookMapper.selectById(id);
        book.setPhotonum(photos.length);
        bookMapper.updateById(book);
        if (photos.length == 0) {
            return false;
        }
        for (int i = 0; i < photos.length; ++i) {
            upload(root + bookMapper.selectById(id).getPhotoPath(), i + 1, photos[i]);
        }
        return true;
    }

    public boolean deletePhoto(String id) {
        String photopath = bookMapper.selectById(id).getPhotoPath();
        try {
            Files.deleteIfExists(Paths.get(root + photopath + "1.jpg"));
            Files.deleteIfExists(Paths.get(root + photopath + "2.jpg"));
            Files.deleteIfExists(Paths.get(root + photopath + "3.jpg"));
            Files.delete(Paths.get(root + photopath));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean changeBook(JSONObject bookinfo) {
        String id = bookinfo.getString("id");
        return bookMapper.updateById(setBookinfo(bookinfo, id)) > 0;
    }

    public boolean deleteBook(String id) {
        deletePhoto(id);
        return bookMapper.deleteById(id) > 0;
    }

}
