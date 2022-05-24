package cose.seu.secondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cose.seu.secondhand.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
