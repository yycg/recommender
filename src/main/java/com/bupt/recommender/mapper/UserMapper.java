package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.UserPO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    UserPO getUserById(@Param("id") int id) throws Exception;
}
