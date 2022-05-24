package cose.seu.secondhand.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

/**
 * id, userId, photopath, bookname, ISBN, used, price为必填项，不得为null
 */
@TableName("book")
public class Book {

    public enum Used {
        NEW,
        VERY_GOOD,
        GOOD,
        ACCEPTABLE
    }

    @TableField("photopath")
    private String photoPath; //必填项

    @TableField("photonum")
    private Integer photonum;

    @TableId
    private String id; //必填项

    @TableField("userId")
    private Integer userId; //必填项

    @TableField("bookname")
    private String bookname; //必填项

    @TableField("ISBN")
    private String ISBN; //必填项

    @TableField("author")
    private String author;

    @TableField("publisher")
    private String publisher;

    @TableField("date")
    private String date; // YYYY

    @TableField("price")
    @JSONField(format = "#0.00")
    private BigDecimal price; //必填项

    @TableField("used")
    private Used used; //必填项

    @TableField("comment")
    private String comment;


    @TableField("tel")
    private String tel;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getPhotonum() {
        return photonum;
    }

    public void setPhotonum(Integer photonum) {
        this.photonum = photonum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Redefine getter, enum -> int
     *
     * @return used.ordinal
     */
    public Integer getUsed() {
        return used.ordinal();
    }

    /**
     * Redefine setter, int -> enum
     *2
     * @param i i must in 0 to 3
     *          <p>0 - NEW</p>
     *          <p>1 - VERY_GOOD</p>
     *          <p>2 - GOOD</p>
     *          <p>3 - ACCEPTABLE</p>
     */
    public void setUsed(Integer i) {
        this.used = Used.values()[i];
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
