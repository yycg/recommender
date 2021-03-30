package com.bupt.recommender.mapper;

import com.bupt.recommender.entity.AdminUserPO;
import com.bupt.recommender.entity.UserPO;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    AdminUserPO getUserByUsername(@Param("username") String username) throws Exception;

    void save(AdminUserPO user) throws Exception;
}
