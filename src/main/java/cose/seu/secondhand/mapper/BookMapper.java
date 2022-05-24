package cose.seu.secondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cose.seu.secondhand.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
