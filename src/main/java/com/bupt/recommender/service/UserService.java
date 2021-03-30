package com.bupt.recommender.service;

import com.bupt.recommender.entity.AdminUserPO;
import com.bupt.recommender.vo.AdminUserReqVO;

public interface UserService {
    AdminUserPO findByUsername(String username) throws Exception;

    int register(AdminUserReqVO user) throws Exception;
}
