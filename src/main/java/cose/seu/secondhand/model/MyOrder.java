package cose.seu.secondhand.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("myorder")
public class MyOrder {
    @TableId
    private String oid;

    @TableField("buyer")
    private String buyer;
    @TableField("seller")
    private String seller;
    @TableField("bookid")
    private String bookid;

    @TableField("bookname")
    private String bookname;

    @TableField("tel")
    private String tel;

    public MyOrder(String oid, String buyer, String seller, String bookid, String bookname, String tel) {
        this.oid = oid;
        this.buyer = buyer;
        this.seller = seller;
        this.bookid = bookid;
        this.bookname = bookname;
        this.tel = tel;
    }

    public String getOid() {
        return oid;
    }

    public void setOrderid(String oid) {
        this.oid = oid;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
