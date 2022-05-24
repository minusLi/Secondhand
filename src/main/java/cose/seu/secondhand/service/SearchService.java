package cose.seu.secondhand.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cose.seu.secondhand.mapper.BookMapper;
import cose.seu.secondhand.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private BookMapper bookMapper;

    public String SearchBook(String category, String keyword){
        QueryWrapper<Book> wrapper=new QueryWrapper<>();
        wrapper.like(category,keyword);
        List<Book> bookList=bookMapper.selectList(wrapper);
        if(!bookList.isEmpty()) {
            return JSON.toJSONString(bookList);
        }else return null;
    }

}
