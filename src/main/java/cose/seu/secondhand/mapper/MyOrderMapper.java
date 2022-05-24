package cose.seu.secondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cose.seu.secondhand.model.MyOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MyOrderMapper extends BaseMapper<MyOrder> {

}
